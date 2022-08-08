package com.justadroiddev.wordscleanarchapp.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.core.view.children
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.justadroiddev.wordscleanarchapp.R
import com.justadroiddev.wordscleanarchapp.databinding.ItemCardJokeBinding
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiModel

class JokeSwipeAdapter(
    private val doubleClickJokeListener: DoubleClickJokeListener
) : ListAdapter<JokeUiModel, JokeSwipeAdapter.JokeSwipeViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeSwipeViewHolder {
        val binding = ItemCardJokeBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_card_joke, parent, false)
        )
        return JokeSwipeViewHolder(binding, doubleClickJokeListener)
    }

    override fun onBindViewHolder(holder: JokeSwipeViewHolder, position: Int) {
        val joke = getItem(position)
        holder.bind(joke)
    }

    class JokeSwipeViewHolder(
        private val itemCardJokeBinding: ItemCardJokeBinding,
        private val listener: DoubleClickJokeListener
    ) : RecyclerView.ViewHolder(itemCardJokeBinding.root) {

        fun bind(jokeUi: JokeUiModel) {
            itemCardJokeBinding.apply {
                cardFrontSideItem.textJoke.text = jokeUi.showTextJoke()
                cardFrontSideItem.infoButton.setOnClickListener {
                    flipView.flipTheView()
                }
                cardFrontSideItem.cardFront.setDoubleClickListener {
                    listener.onClick(jokeUi)
                }
                cardBackSideItem.categoryJokeText.text = "Category: ${jokeUi.provideCategory()}"
                cardBackSideItem.jokeDateCreationText.text =
                    "Created at: ${jokeUi.provideDateCreation()}"
                cardBackSideItem.backButton.setOnClickListener {
                    flipView.flipTheView()
                }
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

    class DoubleClickJokeListener(private val listener: (jokeUi: JokeUiModel) -> Unit) {
        fun onClick(jokeUi: JokeUiModel) = listener.invoke(jokeUi)
    }

    class JokeSwipeTouchCallback(
        private val action: () -> Unit
    ) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            when (direction) {
                ItemTouchHelper.RIGHT -> {
                    action.invoke()
                }
                ItemTouchHelper.LEFT -> {
                    action.invoke()
                }
            }
        }

    }
}