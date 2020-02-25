package sg.toru.cleanarchitecture.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import sg.toru.cleanarchitecture.data.respository.PostRepository

class PostUseCase (private val postRepo: PostRepository) {
    private val scope:CoroutineScope = CoroutineScope(Dispatchers.IO)

    fun readPostData() {
        val service = postRepo.getPostService()
        try {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    service.getPosts()
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