package sg.toru.cleanarchitecture.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import sg.toru.cleanarchitecture.data.entity.Comment
import sg.toru.cleanarchitecture.data.entity.Post
import sg.toru.cleanarchitecture.data.util.NetworkUtil

interface PostApi {
    @GET(NetworkUtil.post)
    suspend fun getPosts(): List<Post>

    @GET(NetworkUtil.comment)
    suspend fun getComments(@Query(NetworkUtil.postQuery) number:String): Response<List<Comment>>
}