package sg.toru.cleanarchitecture.presentation.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import sg.toru.cleanarchitecture.R
import sg.toru.cleanarchitecture.data.entity.Post

class DetailActivity : AppCompatActivity(R.layout.activity_detail) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val post = intent.getParcelableExtra<Post>("POST")
        init(post)
    }

    private fun init(post:Post?) {
        post?.let {
            val title = findViewById<TextView>(R.id.txtTitle)
            val body = findViewById<TextView>(R.id.txtBody)

            title.text = post.title
            body.text = post.body
        }
    }
}
