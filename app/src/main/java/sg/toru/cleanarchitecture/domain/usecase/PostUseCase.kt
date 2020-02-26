package sg.toru.cleanarchitecture.domain.usecase

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sg.toru.cleanarchitecture.data.api.PostApi
import sg.toru.cleanarchitecture.data.entity.Comment
import sg.toru.cleanarchitecture.data.entity.Post
import sg.toru.cleanarchitecture.data.respository.NetworkClient
import sg.toru.cleanarchitecture.data.respository.PostRepository

class PostUseCase (private val postRepo: PostRepository) {
    fun readPostData(callback:(List<Post>)->Unit) {
        val service = NetworkClient.retrofit.create(PostApi::class.java)
        try {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val list = service.getPosts()
                    withContext(Dispatchers.Main) {
                        callback.invoke(list)
                    }
                } catch (e:Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }

    fun readCommentData(number:String, callback: (List<Comment>) -> Unit) {
        val service = NetworkClient.retrofit.create(PostApi::class.java)
        try {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = service.getComments(number)
                    if(response.isSuccessful) {
                        withContext(Dispatchers.Main){
                            callback.invoke(response.body()!!)
                        }
                    } else {
                        Log.e("Toru", "response number:: ${response.code()}")
                    }
                } catch (e:Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }
}