package fr.isen.ricciardi.androidtoolbox.Classes

class User {

    var firstName : String? = null
    var lastName : String? = null
    var birthDate: String? = null

    constructor() : super (){}

    constructor(FirstName : String, LastName: String, BirthDate: String) : super(){
        this.firstName = FirstName
        this.lastName = LastName
        this.birthDate = BirthDate
    }


}