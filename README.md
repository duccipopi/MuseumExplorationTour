# Museum Exploration Tour

Last project for [Android Kotlin Developer](https://www.udacity.com/course/android-kotlin-developer-nanodegree--nd940)


## OVERVIEW

Want to know what could be found in The Metropolitan Museum of Art? Are their collections so big to view that you want to know where you should start your visit?
This application tries to lessen this burden, given random samples of each department can present to you on your next visit. Who knows which surprises can be seen in this application that can instigate you to physically go there?

## GOALS
```
* List all current departments in The MeT
* Show random artworks of each department, cycling each access
* Inform the user’s distance to The MeT
* Launch Google Maps to show the directions to The MeT
```

## SPECIFICATIONS
This application will use the [The Metropolitan Museum of Art Collection API](https://metmuseum.github.io/) to fetch departments and random artworks from the museum gallery, presenting information and images.
Each day, it will cycle the presented artworks of each department, keeping an ever-growing gallery for the user, but which can be used offline.
Users will be able to check how far they are from the museum and will be able to get prompt directions for Google Maps from their place to museum location.

## MILESTONES
### Basic application UI 
A mock interface to show the basic appearance and navigation flow of each screen of the application
### Rest API integration
Fetch and process the information of the [The Metropolitan Museum of Art Collection API](https://metmuseum.github.io/), creating the model architecture using [Retrofit](https://square.github.io/retrofit/).
### Local Repository and Images caching
Store the processed data from Rest API with [Room](https://developer.android.com/jetpack/androidx/releases/room?gclid=CjwKCAjw2vOLBhBPEiwAjEeK9ju3PNGqiN1WxIYHa1QNo5pSD_lt7QsZui5_OfRKPfA4_OlfzVqY-xoCBvUQAvD_BwE&gclsrc=aw.ds) and caches the images on presentation using [Glide](https://github.com/bumptech/glide).
### Integrate data with UI 
Connect the fetched data and images with the basic UI, using MVVM architecture.
### Improve the data presentation
Improve the current UI using RecyclerViews to show multiple items, change to MotionLayout to a better experience to simple items.
### Determine the user’s distance
Integrate Google Location Services to retrieve user’s current location and determine how far she/he is from The MeT. Also, provide a shortcut to Google Maps with the directions to The MeT.

## Built with

* [The Metropolitan Museum of Art Collection API](https://github.com/metmuseum/openaccess) - Provides select datasets of information on more than 470,000 artworks in its Collection for unrestricted commercial and noncommercial use.
* [Koin](https://github.com/InsertKoinIO/koin) - A pragmatic lightweight dependency injection framework for Kotlin.
* [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java
* [Glide](https://github.com/bumptech/glide) - A fast and efficient open source media management and image loading framework
* [Room](https://developer.android.com/jetpack/androidx/releases/room?gclid=CjwKCAjw2vOLBhBPEiwAjEeK9ju3PNGqiN1WxIYHa1QNo5pSD_lt7QsZui5_OfRKPfA4_OlfzVqY-xoCBvUQAvD_BwE&gclsrc=aw.ds)

## Licence

* [The Metropolitan Museum of Art Collection API](https://www.metmuseum.org/information/terms-and-conditions)