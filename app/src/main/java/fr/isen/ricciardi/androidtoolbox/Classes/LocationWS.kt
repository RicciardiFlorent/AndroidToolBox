package fr.isen.ricciardi.androidtoolbox.Classes

class LocationWS{
    var street : StreetWS ? = null
    var city: String ?=null
    var state: String ?= null
    var country: String ?= null
    override fun toString(): String {
        return "${street} ${city} ${state} ${country}"
    }


}