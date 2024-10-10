package com.weatherapp.repo


import com.google.android.gms.maps.model.LatLng
import com.weatherapp.MainViewModel
import com.weatherapp.api.WeatherService
import com.weatherapp.db.fb.FBDatabase
import com.weatherapp.model.City
import com.weatherapp.model.User

class Repository(private var listener: MainViewModel) : FBDatabase.Listener {
    private var fbDb = FBDatabase(this)
    private var weatherService = WeatherService()

    interface Listener {
        fun onUserLoaded(user: User)
        fun onCityAdded(city: City)
        fun onCityRemoved(city: City)
    }

    fun remove(city: City) {
        fbDb.remove(city)
    }

    fun register(userName: String, email: String) {
        fbDb.register(User(userName, email))
    }

    fun addCity(name: String) {
        weatherService.getLocation(name) { lat, lng ->
            fbDb.add(
                City(
                    name = name,
                    weather = "loading...",
                    location = LatLng(lat ?: 0.0, lng ?: 0.0)
                )
            )
        }
    }

    fun addCity(lat: Double, lng: Double) {
        weatherService.getName(lat, lng) { name ->
            fbDb.add(
                City(
                    name = name ?: "NOT_FOUND",
                    location = LatLng(lat, lng)
                )
            )
        }
    }

    override fun onUserLoaded(user: User) {
        listener.onUserLoaded(user)
    }

    override fun onCityAdded(city: City) {
        listener.onCityAdded(city)
    }

    override fun onCityRemoved(city: City) {
        listener.onCityRemoved(city)
    }
}