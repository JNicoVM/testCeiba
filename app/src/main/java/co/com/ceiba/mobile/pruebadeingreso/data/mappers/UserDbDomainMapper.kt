package co.com.ceiba.mobile.pruebadeingreso.data.mappers

import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.UserEntity
import co.com.ceiba.mobile.pruebadeingreso.domain.models.User

class UserDbDomainMapper :  ModelMapper<UserEntity, User>{

    /**
     * Model origin in this case is coming from a local db request
     */

    override fun mapFromModelOrigin(model: UserEntity): User {
        return User(
            id = model.id,
            name = model.name,
            username = model.username,
            phone = model.phone,
            email = model.email,
        )
    }

    override fun mapToModelOrigin(model: User): UserEntity {
        return UserEntity(
            id = model.id,
            name = model.name,
            username = model.username,
            phone = model.phone,
            email = model.email,
        )
    }

    fun fromDbList(initial: List<UserEntity>): List<User>{
        return initial.map { mapFromModelOrigin(it) }
    }

    fun toDbList( initial: List<User>): List<UserEntity>{
        return initial.map { mapToModelOrigin(it) }
    }
}