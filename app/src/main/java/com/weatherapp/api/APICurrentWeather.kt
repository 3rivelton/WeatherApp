package com.weatherapp.api

data class APICurrentWeather (
    var apiLocation : APILocation? = null,
    var current : APIWeather? = null
)