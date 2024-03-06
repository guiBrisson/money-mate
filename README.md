This is a Kotlin Multiplatform project targeting Android, iOS, Desktop. Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

### CommonMain folder structure (WIP)
* `/commonMain`
  * `/data`
    * `/dataSource` are responsible for providing data the app needs to function. (e.g. network, local database, files, memory). Data sources classes are the bridge between the application and the system for data operations.
    * `/di` (dependency injection) data module.
    * `/repository` are used by other layers on the app to interact with the data layer, responsible to exposing the data to the rest of the app, centralizing data changes, resolving conflicts between multiple data sources and containing business logic.
  * `/domain`
    * `/model` are objects that represent the core data of the application. They can be simple values or complex objects, usually in a form of a `data class`.
    * `/repository` interfaces contracts that are implemented by the `/data/repository` classes.
  * `/presentation`
    * `/designsystem` contains basic components that are used across all screens.
    * `/di` (dependency injection) presentation module.
    * `/navigation` utilities for screen navigation.
    * `/screen` contains it's state and a viewModel for business logic.
    * `/theme` values such as `colors`, `typography` and `shapes`.
  * `app.kt` composable entry point for all platforms.
