# Image Browser
An app that allows you to search for images from [Pixabay.com](https://pixabay.com/).<br> 
It displays the results from Pixabay API in a [RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview). <br>
App uses Retrofit and [MVVM pattern](https://developer.android.com/jetpack/docs/guide) along with Room which is used to cache the data. Implemented in a [Kotlin](https://kotlinlang.org/docs/reference/) language.
<br>
# Dependencies
* [Retrofit](https://square.github.io/retrofit/)
* [Room](https://developer.android.com/training/data-storage/room)
* [Gson](https://github.com/google/gson)
* [Glide](https://github.com/bumptech/glide)
* [Toasty](https://github.com/GrenderG/Toasty)
* [PhotoView](https://github.com/chrisbanes/PhotoView)
# Getting started
Clone the repository and import the project in Android Studio.
You need to provide a [key](https://pixabay.com/api/docs/) to access the Pixabay API.<br>
After getting the key, put it in the gradle.properties file like this
```
API_KEY="YOUR_API_KEY_HERE"
```
If you don't have a gradle.properties file, just create it in the main directory of the project and paste your api key.<br>
Then build and run the project.
# Preview
![App preview](https://github.com/pinky169/ImageBrowser/blob/master/app-demo.gif)
