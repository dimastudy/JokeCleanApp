package com.justadroiddev.wordscleanarchapp.presentation.ui.joke

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import com.google.android.material.snackbar.Snackbar
import com.justadroiddev.wordscleanarchapp.core.BaseFragment
import com.justadroiddev.wordscleanarchapp.databinding.FragmentFavoriteJokesBinding
import com.justadroiddev.wordscleanarchapp.presentation.adapters.FavoriteJokesAdapter
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiFailure
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiProgress
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteJokesFragment :
    BaseFragment<FragmentFavoriteJokesBinding>(FragmentFavoriteJokesBinding::inflate) {

    private val viewModel by viewModels<FavoriteJokesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FavoriteJokesAdapter()

        val touchHelperCallback = FavoriteJokesAdapter.FavoriteJokeItemTouchHelper(adapter){ jokeUi ->
            viewModel.removeJokeAndUpdateList(jokeUi)
            Snackbar.make(requireView(), "Joke deleted", Snackbar.LENGTH_SHORT).setAction("Undo"){
                viewModel.saveJokeAndUpdateList(jokeUi)
            }.show()
        }
        val itemTouchHelper = ItemTouchHelper(touchHelperCallback)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.favoriteJokes.collect{ jokeUi ->
                    when(jokeUi){
                        is JokeUiSuccess -> {
                            adapter.submitList(jokeUi.provideJokes())
                            binding.apply {
                                listFavoriteJokes.visibility = View.VISIBLE
                                retryButton.visibility = View.GONE
                                textError.visibility = View.GONE
                                progressJokes.visibility = View.GONE
                            }
                        }
                        is JokeUiFailure -> {
                            binding.apply {
                                textError.text = jokeUi.showError()
                                listFavoriteJokes.visibility = View.GONE
                                retryButton.visibility = View.VISIBLE
                                textError.visibility = View.VISIBLE
                                progressJokes.visibility = View.GONE
                            }
                        }
                        is JokeUiProgress -> {
                            binding.apply {
                                listFavoriteJokes.visibility = View.GONE
                                retryButton.visibility = View.GONE
                                textError.visibility = View.GONE
                                progressJokes.visibility = View.VISIBLE
                            }
                        }
                    }
                }
            }
        }

        binding.apply {
            listFavoriteJokes.setHasFixedSize(true)
            listFavoriteJokes.adapter = adapter
            itemTouchHelper.attachToRecyclerView(listFavoriteJokes)
            retryButton.setOnClickListener {
                viewModel.fetchFavoriteJokes()
            }

            swipeRefreshLayout.setOnRefreshListener {
                viewModel.fetchFavoriteJokes()
                swipeRefreshLayout.isRefreshing = false
            }
        }


    }


}