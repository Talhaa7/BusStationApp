# BusStationApp

### Clean Architecture
Project structure created following [Clean Architecture Principles](https://gist.github.com/ygrenzinger/14812a56b9221c9feca0b3621518635b)

The architecture of the application consists of 3 layers

- Domain layer : The domain layer is responsible for encapsulating complex business logic, or simple business logic that is reused by multiple ViewModels

- Data layer : This layer is used as data access layer. All data connected operations will be controlled in this layer.  [Repository Pattern](https://developer.android.com/jetpack/guide#recommended-app-arch)is used as structure

- Presenter layer : This layer is UI layer all UI connected operations will be handled in this layer

- ### Libraries

- Jetpack Compose
- Google Map
- Retrofit
- Dagger-Hilt
