package sg.toru.cleanarchitecture.presentation.feature

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import sg.toru.cleanarchitecture.R
import sg.toru.cleanarchitecture.data.entity.Post
import sg.toru.cleanarchitecture.data.respository.PostRepository
import sg.toru.cleanarchitecture.domain.usecase.PostUseCase
import sg.toru.cleanarchitecture.presentation.detail.DetailActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var useCase: PostUseCase
    private lateinit var recyclerView: RecyclerView
    private lateinit var mainAdapter: MainRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        useCase = PostUseCase(PostRepository())
        recyclerView = findViewById(R.id.rcvMain)
        mainAdapter = MainRecyclerAdapter(::goOnNext)
        recyclerView.adapter = mainAdapter
        callNetwork()
    }

    private fun goOnNext(post: Post) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java)
        intent.putExtra("POST", post)
        startActivity(intent)
    }

    private fun callNetwork() {
        useCase.readPostData {
            mainAdapter.lists = it
        }
    }
}
