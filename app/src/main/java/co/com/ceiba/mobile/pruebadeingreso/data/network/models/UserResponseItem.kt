package co.com.ceiba.mobile.pruebadeingreso.data.network.models

/**
 * Response model of a user's main data json file sent by @getUsers
 */

data class UserResponseItem(
    val addressResponse: AddressResponse,
    val companyResponse: CompanyResponse,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)