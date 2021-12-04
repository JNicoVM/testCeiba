package co.com.ceiba.mobile.pruebadeingreso.data.network.models

/**
 * Response model of a user's company json file sent by @getUsers
 */

data class CompanyResponse(
    val bs: String,
    val catchPhrase: String,
    val name: String
)