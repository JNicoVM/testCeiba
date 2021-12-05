package co.com.ceiba.mobile.pruebadeingreso.domain.usescases

import co.com.ceiba.mobile.pruebadeingreso.domain.AppRepository
import co.com.ceiba.mobile.pruebadeingreso.domain.models.User
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import javax.inject.Inject

class InsertUsersIntoDbUseCase @Inject constructor(private val repository: AppRepository) {

    suspend operator fun invoke(user: List<User>): Resource<Boolean> =
        repository.insertUsersIntoDb(user)
}