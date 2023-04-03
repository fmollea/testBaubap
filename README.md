# Test Baubap

In this project I solved the Baubap test for Android developer.
I created a fake login with: 

  - Clean architecture
  - Multi modules
  - Hilt
  - Unit Testing (View model and Use case)
  - Navigation component
  
### Simple explain

I use five modules.
  
  1. **app:** with initials configurations. I created configurations for hilt and host the navigation graph. The ***nav_graph*** include the ***login_graph*** and navigate there.
  2. **buildSrc:** I manage all dependencies of project in this module.
  3. **data:** I created the data layer but I didn't use because I didn't connect the login with backend given that in this challenge not was necessary. In this module I would put models, remote and local datasources. I would use retrofit and room for this cases.
  4. **domain:** I created usecases in this layer. In the domain layer I would manage the bussines logic of project.

In the login usecase the method **loginUser** return always true because I don't use backend and I implement others methods for check the email and password.

``` kotlin
fun loginUser(email: String?, password:  String?): Boolean
```

I check regex email that correct and email and password values aren't empty or null.

``` kotlin
fun checkRegexEmail(email: String?): Boolean
fun areFieldsEmptyOrNulls(email: String?, password: String?): Boolean
```

  5. **feature:** In this layer I would create all the features of project as submodules. For this challenges I created login feature and inside I create the UI, Viewmodels, DI folder with hilt and navigation graph.

<p align="center"> 
<img width="255" alt="image" src="https://user-images.githubusercontent.com/7406241/229518727-b3089c4f-fed1-4d8f-bd84-15cd1c230bb8.png"></br>
<i>Modules screenshot.<i/>
</p> 


### Screenshots and video 

<img width="280"  heigth="622" alt="image" src="https://user-images.githubusercontent.com/7406241/229527351-6a393eab-b870-4aa8-acf3-5fa00e433ad4.png"> 
<img width="280"  heigth="622" alt="image" src="https://user-images.githubusercontent.com/7406241/229527431-a84be5c1-7d87-4a08-89e5-01d492c9c930.png">
</br>
<video src="https://user-images.githubusercontent.com/7406241/229535938-bac64c6e-3a8b-446e-aa1e-e50e6f7495b2.webm" width="400" height="889">
