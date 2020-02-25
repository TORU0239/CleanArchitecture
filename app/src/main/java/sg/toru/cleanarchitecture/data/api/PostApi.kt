package sg.toru.cleanarchitecture.data.api

import retrofit2.http.GET
import sg.toru.cleanarchitecture.data.entity.Post

interface PostApi {
    @GET
    suspend fun getPosts(): List<Post>
}