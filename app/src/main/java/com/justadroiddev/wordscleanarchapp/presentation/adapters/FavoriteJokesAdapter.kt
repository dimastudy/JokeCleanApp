package com.justadroiddev.wordscleanarchapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.justadroiddev.wordscleanarchapp.R
import com.justadroiddev.wordscleanarchapp.databinding.ItemFavoriteJokeBinding
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiModel

class FavoriteJokesAdapter(

) : ListAdapter<JokeUiModel, FavoriteJokesAdapter.FavoriteJokesViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteJokesViewHolder {
        val binding = ItemFavoriteJokeBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_joke, parent, false)
        )
        return FavoriteJokesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteJokesViewHolder, position: Int) {
        val joke = getItem(position)
        holder.bind(joke)
    }


    class FavoriteJokesViewHolder(private val binding: ItemFavoriteJokeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(jokeUi: JokeUiModel) {
            binding.apply {
                textJoke.text = jokeUi.showTextJoke()
            }
        }

    }


    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<JokeUiModel>() {
            override fun areItemsTheSame(oldItem: JokeUiModel, newItem: JokeUiModel): Boolean =
                oldItem.provideId() == newItem.provideId()

            override fun areContentsTheSame(oldItem: JokeUiModel, newItem: JokeUiModel): Boolean =
                oldItem == newItem

        }
    }

    class FavoriteJokeItemTouchHelper(
        private val adapter: FavoriteJokesAdapter,
        private val action: (jokeUi: JokeUiModel) -> Unit
    ) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when(direction){
                ItemTouchHelper.RIGHT -> {
                    val joke = adapter.getItem(viewHolder.adapterPosition)
                    action.invoke(joke)
                }
            }
        }

    }

}