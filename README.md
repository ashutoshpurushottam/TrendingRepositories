# Trending Repositories
----
This is a simple Android application written to teach various concepts in Android Development for my OpenClassrooms students. The application demonstrates the following concepts. 
- MVVM + P architecture for developing large applications.
- Kotlin 
- Dependency Injection using Dagger (Application Scope, Activity Scope, and Screen Scope). The application demonstrates how to override default Dagger classes to ensure that the components are not recreated on configuration change.
- Reactive Programming using RxJava 
- Network requests with Retrofit 
- Unit tests with jUnit/Mockito 
- Espresso tests with test doubles provided by Dagger.

***Note: The application would be updated continuously to teach and demonstrate new concepts as required by students.*** 

## Architecture overview
----

![Image of Architecture](https://github.com/ashutoshpurushottam/Images/blob/master/architecture.png?raw=true)

The ViewModel has no reference to View. Data is passed to the View through Observables in the ViewModel that the View subscribes to. In this application, the Presenter is responsible for updating the Model, however it can be modified so that only the ViewModel updates the Model (as in classical MVVM architecture). 

## Screenshots
----

![Image of Screens](https://github.com/ashutoshpurushottam/Images/blob/master/trending2.png?raw=true) ![Image of Screens](https://github.com/ashutoshpurushottam/Images/blob/master/trending1.png?raw=true) 

The application fetches the most popular(trending) repositories of Java, Swift, and JavaScript in a ViewPager. The application uses a Repository pattern and does not hit Github API on configuration change. 

## License
Freely provided under the MIT License.
