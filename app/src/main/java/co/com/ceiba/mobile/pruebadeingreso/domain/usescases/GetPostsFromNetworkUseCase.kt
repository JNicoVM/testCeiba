package co.com.ceiba.mobile.pruebadeingreso.domain.usescases

import co.com.ceiba.mobile.pruebadeingreso.domain.AppRepository
import co.com.ceiba.mobile.pruebadeingreso.domain.models.Post
import co.com.ceiba.mobile.pruebadeingreso.utils.Resource
import javax.inject.Inject

class GetPostsFromNetworkUseCase @Inject constructor(private val repository: AppRepository) {

    suspend operator fun invoke(userId:Int): Resource<List<Post>> =
        repository.getPostsFromNetwork(userId)
}