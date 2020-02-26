package sg.toru.cleanarchitecture.presentation.feature

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sg.toru.cleanarchitecture.R
import sg.toru.cleanarchitecture.data.entity.Post

class MainRecyclerAdapter(private val clickListener:(Post)->Unit) : RecyclerView.Adapter<MainVH>() {

    var lists:List<Post> = listOf()
    set(value) {
        field = value
        Log.e("Toru", "custom setter")
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return MainVH(view, clickListener)
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    override fun onBindViewHolder(holder: MainVH, position: Int) {
        holder.bind(lists[position])
    }
}

class MainVH(
    view: View,
    private val clickListener:(Post)->Unit
): RecyclerView.ViewHolder(view) {
    private var title:TextView = view.findViewById(R.id.txtTitle)

    fun bind(post:Post) {
        title.text = post.title
        itemView.setOnClickListener {
            clickListener.invoke(post)
        }
    }
}