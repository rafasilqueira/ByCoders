package com.example.bycoders.presentation.ui.implementation

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat.checkSelfPermission
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.example.bycoders.R
import com.example.bycoders.databinding.HomeFragmentBinding
import com.example.bycoders.domain.model.HistoryModel
import com.example.bycoders.presentation.ui.abstraction.HomeFragment
import com.example.bycoders.presentation.vm.implementation.HomeVMImpl
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.GoogleMap.OnMapLoadedCallback
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.Task
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

private const val HOME_FRAGMENT = "HomeFragment"

@AndroidEntryPoint
class HomeFragmentImpl : HomeFragment(), OnMapReadyCallback, OnMapLoadedCallback {

    private val mHomeVm by viewModels<HomeVMImpl>()
    private lateinit var mMap: GoogleMap
    private lateinit var binding: HomeFragmentBinding
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(requireContext())
    }

    private val crashlytics by lazy { Firebase.crashlytics }
    private val analytics by lazy { Firebase.analytics }

    private var cancellationTokenSource = CancellationTokenSource()

    private val requestPermissionLocationLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            when {
                isGranted -> requestCurrentLocation()
                else -> onPermissionNotGranted()
            }
        }

    private fun onPermissionNotGranted() {
        crashlytics.setCustomKey(HOME_FRAGMENT, "Permission Location not granted")
        requireActivity().finish()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.run {
            lifecycleOwner = viewLifecycleOwner
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupMap()
        requestCurrentLocation()
    }

    private fun setupMap() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun requestLocationPermission() {
        requestPermissionLocationLauncher.launch(ACCESS_FINE_LOCATION)
    }

    private fun requestCurrentLocation() {
        if (checkSelfPermission(requireActivity(), ACCESS_FINE_LOCATION) == PERMISSION_GRANTED) {

            val currentLocationTask: Task<Location> = fusedLocationClient.getCurrentLocation(
                PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            )

            currentLocationTask.addOnCompleteListener { task: Task<Location> ->
                when {
                    task.isSuccessful -> handleWithTaskLocationSuccess(task)
                    else -> crashlytics.setCustomKey(HOME_FRAGMENT, "${task.exception?.message}")
                }

            }
        } else {
            requestLocationPermission()
        }
    }

    private fun handleWithTaskLocationSuccess(task: Task<Location>) {
        with(task.result) {
            insertHistory(latitude, longitude)
            setMapLatLngPosition(latitude, longitude)
        }
    }

    private fun insertHistory(lat: Double, lng: Double) {
        val email = mHomeVm.getCurrentUser()?.email ?: ""
        mHomeVm.insertHistory(
            HistoryModel(
                email = email,
                latitude = lat,
                longitude = lng
            )
        )
    }

    private fun setMapLatLngPosition(lat: Double, lng: Double) {
        val location = LatLng(lat, lng)
        mMap.addMarker(
            MarkerOptions()
                .position(location)
                .title("Current location")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }

    override fun onMapLoaded() {
        analytics.logEvent(HOME_FRAGMENT, bundleOf("render_map_event" to "Success"))
    }
}