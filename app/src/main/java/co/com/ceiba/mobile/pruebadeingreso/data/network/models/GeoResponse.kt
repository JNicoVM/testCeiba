package co.com.ceiba.mobile.pruebadeingreso.data.network.models

/**
 * Response model of a user's geo position json file sent by @getUsers
 */

data class GeoResponse(
    val lat: String,
    val lng: String
)