package sg.toru.cleanarchitecture.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import sg.toru.cleanarchitecture.data.respository.PostRepository

class PostUseCase (private val postRepo: PostRepository) {
    private val job = Job()
    private val scope:CoroutineScope = CoroutineScope(Dispatchers.IO + job)

    fun readPostData() {
        try {
            scope.launch {
                try {
                    postRepo.getPostService().getPosts()
                } catch (e:Exception) {
                    e.printStackTrace()
                }
            }
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }
}