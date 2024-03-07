This is a Kotlin Multiplatform project targeting Android, iOS, Desktop. Learn more
about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

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
        * `/dataSource` are responsible for providing data the app needs to function. (e.g. network, local database,
          files, memory). Data sources classes are the bridge between the application and the system for data
          operations.
        * `/di` (dependency injection) data module.
        * `/repository` are used by other layers on the app to interact with the data layer, responsible to exposing the
          data to the rest of the app, centralizing data changes, resolving conflicts between multiple data sources and
          containing business logic.
    * `/domain`
        * `/model` are objects that represent the core data of the application. They can be simple values or complex
          objects, usually in a form of a `data class`.
        * `/repository` interfaces contracts that are implemented by the `/data/repository` classes.
    * `/presentation`
        * `/designsystem` contains basic components that are used across all screens.
        * `/di` (dependency injection) presentation module.
        * `/navigation` utilities for screen navigation.
        * `/screen` contains it's state and a viewModel for business logic.
        * `/theme` values such as `colors`, `typography` and `shapes`.
    * `app.kt` composable entry point for all platforms.

Contributing to the Project
-------------------------
We welcome contributions to our project! If you want to contribute to our codebase, please follow these steps:

### Using GitFlow

Our development workflow is based on GitFlow. The `main` branch is the release branch, where we merge features from
the `develop` branch.

1. **Clone the repository**: First, clone the repository from GitHub by running the
   command `git clone https://github.com/guiBrisson/money-mate.git` in your terminal.
2. **Create a new branch**: Once you have cloned the repository, create a new branch for your contribution by running
   the command `git checkout -b prefix/my-new-branch develop` in your terminal. This will create a new branch
   called `prefix/my-new-branch` based on the `develop` branch.
3. **Make changes**: Make the necessary changes to your codebase in your new branch. Be sure to follow our coding
   standards and test your changes thoroughly to ensure they work correctly.
4. **Push your changes**: Once you've made changes to your branch, push them to your own repository by running the
   command `git push origin prefix/my-new-branch` in your terminal.
5. **Create a pull request**: Create a new pull request by clicking the "New pull request" button. Fill out the pull
   request form with a clear and descriptive title, and include a detailed explanation of your changes and why they are
   important to the project.
6. **Review and merge**: Once we've reviewed your pull request, we will merge your changes into the `develop` branch if
   they meet our coding standards. If there are any issues with your changes, we will provide feedback on how to improve
   them.

### Branch Prefixes

To help us organize and manage our branches more efficiently, we use a system of branch prefixes. Here are some common
branch prefixes and what they mean:

| Prefix      | Description                                                                                                            |
|-------------|------------------------------------------------------------------------------------------------------------------------|
| `feature/`  | For feature-driven changes. Examples include new UI components, improved error handling, or additional features.       |
| `fix/`      | For bug fixes and other maintenance-related changes.                                                                   |
| `docs/`     | These branches are used to write, update, or fix documentation.                                                        |
| `refactor/` | For significant improvements to the structure or organization of the codebase.                                         |
