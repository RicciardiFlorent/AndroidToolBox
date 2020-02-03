package fr.isen.ricciardi.androidtoolbox.Classes

class UserWS{
    var gender: String ?=null
    var name : NameWS ?= null
    var location : LocationWS ?= null
    var email : String ?= null
    var picture : PictureModelWS ?= null

    constructor(){}

    constructor(Gender: String ,Name: NameWS, Location: LocationWS, Email: String, Picture: PictureModelWS){
        this.gender = gender
        this.name =Name
        this.location = Location
        this.email = Email
        this.picture = Picture
    }

}