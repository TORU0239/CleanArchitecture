package sg.toru.cleanarchitecture.domain.usecase

import retrofit2.Retrofit
import sg.toru.cleanarchitecture.data.respository.PostRepository

class PostUseCase (private val postRepo: PostRepository) {

    fun readPostData() {
        postRepo.getPostService()
    }
}