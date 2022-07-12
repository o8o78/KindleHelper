package com.byteroll.kindlehelper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.byteroll.kindlehelper.R
import com.byteroll.kindlehelper.databinding.HomeListItemBinding
import com.byteroll.kindlehelper.dialog.ArticleDialog
import com.byteroll.kindlehelper.entity.Article

class HomeListAdapter(private val context: Context, private val articleList: List<Article>) : RecyclerView.Adapter<HomeListAdapter.ViewHolder>() {

    private lateinit var binding: HomeListItemBinding

    inner class ViewHolder(binding: HomeListItemBinding): RecyclerView.ViewHolder(binding.root){
        val title = binding.title
        val content = binding.content
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = HomeListItemBinding.bind(LayoutInflater.from(context).inflate(R.layout.home_list_item, parent, false))
        val holder = ViewHolder(binding)
        holder.itemView.setOnClickListener{
            val position = holder.adapterPosition
            val article = articleList[position]
            ArticleDialog(context, article.content).show()
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = articleList[position]
        holder.title.text = article.title
        holder.content.text = article.content
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

}