package co.com.ceiba.mobile.pruebadeingreso.data.mappers

import co.com.ceiba.mobile.pruebadeingreso.data.network.models.PostResponse
import co.com.ceiba.mobile.pruebadeingreso.domain.models.Post

class PostNetworkDomainMapper : ModelMapper<PostResponse, Post> {

    /**
     * Model origin in this case is coming from a network request
     */

    override fun mapFromModelOrigin(model: PostResponse): Post {
        return Post(
            id = model.id,
            userId = model.userId,
            title = model.title,
            body = model.body
        )
    }

    override fun mapToModelOrigin(model: Post): PostResponse {
        return PostResponse(
            id = model.id,
            userId = model.userId,
            title = model.title,
            body = model.body
        )
    }

    fun fromNetworkList(initial: List<PostResponse>): List<Post>{
        return initial.map { mapFromModelOrigin(it) }
    }

    fun toNetworkList( initial: List<Post>): List<PostResponse>{
        return initial.map { mapToModelOrigin(it) }
    }
}