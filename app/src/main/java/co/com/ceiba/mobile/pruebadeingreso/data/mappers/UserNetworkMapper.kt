package co.com.ceiba.mobile.pruebadeingreso.data.mappers

import co.com.ceiba.mobile.pruebadeingreso.data.network.models.UserResponse
import co.com.ceiba.mobile.pruebadeingreso.domain.models.User

class UserNetworkMapper: ModelMapper<UserResponse, User> {

    /**
     * Model origin in this case is coming from a network request
     */

    override fun mapFromModelOrigin(model: UserResponse): User {
        return User(
            id = model.id,
            name = model.name,
            username = model.username,
            phone = model.phone,
            email = model.email,
        )
    }

    override fun mapToModelOrigin(model: User): UserResponse {
       return UserResponse(
           id = model.id,
           name = model.name,
           username = model.username,
           phone = model.phone,
           email = model.email,
       )
    }

    fun fromNetworkList(initial: List<UserResponse>): List<User>{
        return initial.map { mapFromModelOrigin(it) }
    }

    fun toNetworkList( initial: List<User>): List<UserResponse>{
        return initial.map { mapToModelOrigin(it) }
    }
}