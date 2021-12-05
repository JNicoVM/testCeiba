package co.com.ceiba.mobile.pruebadeingreso.data.db

import co.com.ceiba.mobile.pruebadeingreso.data.db.dao.UserDao
import co.com.ceiba.mobile.pruebadeingreso.data.db.entities.UserEntity
import co.com.ceiba.mobile.pruebadeingreso.data.mappers.UserDbDomainMapper
import co.com.ceiba.mobile.pruebadeingreso.domain.models.User
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class DbService @Inject constructor(private val dao: UserDao){

    suspend fun getUsers(): Resource<List<User>> {
        return try {
            val result = dao.getAll()
            Resource.Success(UserDbDomainMapper().fromDbList(result))   // Success handler
        } catch (e: Exception){
            Resource.Error(e.message ?: "An error occurred" ) // Error handler
        }
    }

    suspend fun insertUsers(users: List<User>): Resource<Boolean> {
        return try {
            val userEntities = UserDbDomainMapper().toDbList(users)
             dao.insert(userEntities)
            Resource.Success(true)   // Success handler
        } catch (e: Exception){
            Resource.Error(e.message ?: "An error occurred" ) // Error handler
        }
    }

}