package co.com.ceiba.mobile.pruebadeingreso.domain.usescases

import co.com.ceiba.mobile.pruebadeingreso.domain.AppRepository
import co.com.ceiba.mobile.pruebadeingreso.domain.models.User
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import javax.inject.Inject

class GetUsersFromDbUseCase  @Inject constructor(private val repository: AppRepository) {

    suspend operator fun invoke(): Resource<List<User>> =
        repository.getUsersFromDb()
}