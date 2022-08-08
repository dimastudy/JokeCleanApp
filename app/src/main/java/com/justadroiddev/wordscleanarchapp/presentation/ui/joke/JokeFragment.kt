package com.justadroiddev.wordscleanarchapp.presentation.ui.joke

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.justadroiddev.wordscleanarchapp.core.BaseFragment
import com.justadroiddev.wordscleanarchapp.databinding.FragmentJokeBinding
import com.justadroiddev.wordscleanarchapp.presentation.adapters.JokeSwipeAdapter
import com.justadroiddev.wordscleanarchapp.presentation.customview.UnscrollableLinearLayoutManager
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiFailure
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiProgress
import com.justadroiddev.wordscleanarchapp.presentation.models.JokeUiSuccess
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class JokeFragment : BaseFragment<FragmentJokeBinding>(FragmentJokeBinding::inflate) {


    @Inject
    lateinit var factory: JokeViewModel.AssistedFactory

    private val viewModel: JokeViewModel by viewModels {
        val category = JokeFragmentArgs.fromBundle(requireArguments()).jokeCategory
        JokeViewModel.provideFactory(factory, category)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = JokeSwipeAdapter(JokeSwipeAdapter.DoubleClickJokeListener { jokeUi ->
            viewModel.saveJoke(jokeUi)
        })

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.jokeFlow.collect { jokeUi ->
                    when (jokeUi) {
                        is JokeUiProgress -> {
                            binding.apply {
                                rvListJoke.visibility = View.GONE
                                textError.visibility = View.GONE
                                retryButton.visibility = View.GONE
                                progressJoke.visibility = View.VISIBLE
                            }
                        }
                        is JokeUiSuccess -> {
                            adapter.submitList(jokeUi.provideJokes())
                            binding.apply {
                                rvListJoke.visibility = View.VISIBLE
                                textError.visibility = View.GONE
                                retryButton.visibility = View.GONE
                                progressJoke.visibility = View.GONE
                            }
                        }
                        is JokeUiFailure -> {
                            binding.apply {
                                textError.text = jokeUi.showError()
                                rvListJoke.visibility = View.GONE
                                textError.visibility = View.VISIBLE
                                retryButton.visibility = View.VISIBLE
                                progressJoke.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        }

        val itemCallback = JokeSwipeAdapter.JokeSwipeTouchCallback{
            viewModel.refreshJokeList()
        }
        val touchHelper = ItemTouchHelper(itemCallback)

        binding.apply {
            rvListJoke.setHasFixedSize(true)
            rvListJoke.layoutManager = UnscrollableLinearLayoutManager(requireContext())
            rvListJoke.adapter = adapter
            touchHelper.attachToRecyclerView(rvListJoke)
        }

    }


}