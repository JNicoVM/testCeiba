package co.com.ceiba.mobile.pruebadeingreso.data.network.models

/**
 * Response model of a user's address json file sent by @getUsers
 */
data class AddressResponse(
    val city: String,
    val geoResponse: GeoResponse,
    val street: String,
    val suite: String,
    val zipcode: String
)