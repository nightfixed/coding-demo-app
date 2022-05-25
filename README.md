<!--docs:
title: "Getting Started"
layout: landing
section: docs
path: /docs/getting-started/
-->

# SoccerFan app, for Soccer Fans (and not only)

## 1. Tools used

The idea of building this app is to make full use of the following tools, provided to us. We want to write as less as possible boiler plate code.
For this particular purpose, we will be using:

-   [Jetpack, and everything that comes with it](https://developer.android.com/jetpack)
This means, navigation, layouting, lifecycle components.

- [Material design components](https://material.io/develop/android)
Because it has neat, pre-built widgets that can be extended and styled. It also comes with a wide range of pre-defined colors.

(TBD)

## 2. Architectural decisions

The app is set to target the latest platform/version available. 

From a design perspective, it will follow the MVVM pattern, loosely based on the [guide provided here] (https://developer.android.com/topic/architecture)
The main reasonings would be: available tools, life-cycle aware components, ease of debug and ease of testing.

For this purpose, the structure will be based on 1 `Main Activity` and everything else will be built using `Fragments`

The `Repository` layer will communicate with the `Presentation` layer through `Flows`. 

The `Presentation` layer will communicate with the `UI` layer through `LiveData`.

`Coil` is the library of choice for image loading.

