package sg.toru.cleanarchitecture.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import sg.toru.cleanarchitecture.R
import sg.toru.cleanarchitecture.data.entity.Post
import sg.toru.cleanarchitecture.data.respository.PostRepository
import sg.toru.cleanarchitecture.domain.usecase.PostUseCase

class DetailActivity : AppCompatActivity(R.layout.activity_detail) {

    private lateinit var useCase: PostUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        useCase = PostUseCase(PostRepository())
        val post = intent.getParcelableExtra<Post>("POST")
        init(post)
    }

    private fun init(post:Post?) {
        post?.let {
            val title = findViewById<TextView>(R.id.txtTitle)
            val body = findViewById<TextView>(R.id.txtBody)

            title.text = post.title
            body.text = post.body

            callNetwork(post.id)
        }
    }

    private fun callNetwork(number:String) {
        useCase.readCommentData(number) {
            Log.e("Toru", "comment size::: ${it.size}")
        }
    }
}
