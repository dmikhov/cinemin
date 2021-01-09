
# Cinemin
Cinemin is a free app that shows various information about movies and calculates their budget & revenue in 2021 dollars according to the inflation and purchasing power. This allows you to see the change in budget or box office of a film and compare multiple films made in different years by their real price.

<a href='https://play.google.com/store/apps/details?id=com.dmikhov.cinemin'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' height="70"/></a>

## Demo

![App Demo](readme_res/demo.gif)

## Tech-Stack

* Language: Kotlin
* Architecture: Clean Architecture + MVVM + Single Activity Design
* DI: Koin
* Thread Management:  Coroutines
* Network: Retrofit + OkHttp
* Json Converter: GSON
* Image Caching: Glide
* Data-UI Connection: ViewModel + LiveData
* Analytics: Firebase Analytics + Crashlytics
* Tests: junit, Mockito
* CI: CircleCI

## Architecture
1. Relations between modules:

![Modules Relations](readme_res/clean_arch_modules_relations.png)

2. Short example of relations between classes:

![Classes Relations Example](readme_res/clean_arch_classes_example.png)

## Attribution

1. This product uses the TMDb API but is not endorsed or certified by TMDb. For more information visit <a href="https://https://www.themoviedb.org/">https://www.themoviedb.org</a>

2. Icons made by <a href="https://www.flaticon.com/authors/fjstudio" title="fjstudio">fjstudio</a> from <a href="https://www.flaticon.com/" title="Flaticon">www.flaticon.com</a>

3. Background image from <a href="https://www.pixabay.com/" title="Pixabay">www.pixabay.com</a>