package sg.toru.cleanarchitecture.presentation.feature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import sg.toru.cleanarchitecture.R
import sg.toru.cleanarchitecture.data.respository.PostRepository
import sg.toru.cleanarchitecture.domain.usecase.PostUseCase

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var useCase:PostUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        useCase = PostUseCase(PostRepository())
        callNetwork()
    }

    private fun callNetwork() {
        useCase.readPostData()
    }
}
