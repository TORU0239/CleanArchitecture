package sg.toru.cleanarchitecture.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sg.toru.cleanarchitecture.data.entity.Post
import sg.toru.cleanarchitecture.data.respository.PostRepository

class PostUseCase (private val postRepo: PostRepository) {
    private val scope:CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun readPostData(callback:(List<Post>)->Unit) {
        val service = postRepo.getPostService()
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

    fun readCommentData(number:String) {
        try {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    postRepo.getPostService().getComments(number)
                } catch (e:Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }
}