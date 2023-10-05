# BusStationApp

### Clean Architecture
Project structure created following [Clean Architecture Principles](https://gist.github.com/ygrenzinger/14812a56b9221c9feca0b3621518635b)

The architecture of the application consists of 3 layers

- Domain layer : This layer contains only pure (java/kotlin) business logic does not contain any third party library.UseCase's responsible handling business logic and they written in the manner of single responsibility principle

- Data layer : This layer is used as data access layer. All data connected operations will be controlled in this layer.  [Repository Pattern](https://developer.android.com/jetpack/guide#recommended-app-arch)is used as structure

- Presenter layer : This layer is UI layer all UI connected operations will be handled in this layer

- ### Libraries

- Jetpack Compose
- Google Map
- Retrofit
- Dagger-Hilt
