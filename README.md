
## Tecnologias utilizadas

IDE : Android Studio.
Linguagem de programação : Kotlin.
Arquitetura: MVVM , Clean code, Solid.
Controle de versão : Github.
Outras tecnologias : Firebase ,Android Jetpack , Flow, Extension Functions, Coroutines, Room, Data Binding, Navigation Component, Dagger 2 ,Testes unitários.

##  Tela de login usando (email e senha);
## Tela home com mapa renderizando um ponto na localização atual do device;

Para a correta exibição do mapa é necessário que este aplicativo seja instalado em um device físico ou um emulador que tenha acesso ao google play services.

## Realizar o login utilizando Firebase Auth;

Foi criado um usuário para realizar os testes de login

Usuário: rafasilqueira@gmail.com
senha : 123456

O nome de usuário deve ser um email válido e a senha conter mais de 06 caracteres. 

## Armazenar os dados do usuário na store global;

Dados estão sendo armazenados no firebase

## Rastrear login com sucesso e renderização com sucesso com Analytics.

Dados de eventos do firebase estão sendo enviados nos seguintes arquivos.

AuthenticationFragmentImpl.kt linha 63 (Sucesso Login)
HomeFragmentImpl.kt linha 146 (Sucesso renderização do mapa)

## Rastrear os erros e envia-los ao Crashlytics;

Dados de erros foram enviados.

AuthenticationFragmentImpl linha 98 (Login error)
HomeFragmentImpl linha 61 (On permission location error)

## Armazenar na base de dados local o usuário logado e sua última posição no mapa;

Para persistencia de dados foi criado a tabela "history" contendo as informações solicitadas, foi utilizado a tecnologia Room
nativo do Android que utiliza a como base o sqlite.

É possivel acessar os dados salvos a partir da ferramenta nativa do Android studio "App Inpection"

## Testar fluxo de login (unit e e2e);

Em desenvolvimento

## Testar fluxo da home (unit e e2e).

Em desenvolvimento
