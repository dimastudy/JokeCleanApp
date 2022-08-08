package com.justadroiddev.wordscleanarchapp.presentation.ui.categories

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.justadroiddev.wordscleanarchapp.core.BaseFragment
import com.justadroiddev.wordscleanarchapp.databinding.FragmentCategoriesBinding
import com.justadroiddev.wordscleanarchapp.presentation.adapters.CategoriesListAdapter
import com.justadroiddev.wordscleanarchapp.presentation.models.CategoriesFailureUiModel
import com.justadroiddev.wordscleanarchapp.presentation.models.CategoriesProgress
import com.justadroiddev.wordscleanarchapp.presentation.models.CategoriesSuccessUiModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>(FragmentCategoriesBinding::inflate) {

    private val viewModel by viewModels<CategoriesViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CategoriesListAdapter(CategoriesListAdapter.CategoryClickListener { category ->
            this.findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToJokeFragment(category))
        })

        viewLifecycleOwner.lifecycleScope.launch{
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categoriesFlow.collect{ categoriesUi ->
                    when(categoriesUi){
                        is CategoriesSuccessUiModel -> {
                            adapter.updateList(categoriesUi.showCategories())
                            binding.apply {
                                recyclerCategories.visibility = View.VISIBLE
                                progressCategory.visibility = View.GONE
                                textError.visibility = View.GONE
                                retryButton.visibility = View.GONE
                            }
                        }
                        is CategoriesFailureUiModel -> {
                            binding.apply {
                                recyclerCategories.visibility = View.GONE
                                progressCategory.visibility = View.GONE
                                textError.visibility = View.VISIBLE
                                retryButton.visibility = View.VISIBLE
                                textError.text = categoriesUi.showError()
                            }
                        }
                        is CategoriesProgress -> {
                            binding.apply {
                                recyclerCategories.visibility = View.GONE
                                progressCategory.visibility = View.VISIBLE
                                textError.visibility = View.GONE
                                retryButton.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        }

        binding.apply {
            recyclerCategories.setHasFixedSize(true)
            recyclerCategories.adapter = adapter
            retryButton.setOnClickListener {
                viewModel.getCategories()
            }
        }



    }


    fun showDefaultSnackBar(text: String){
        Snackbar.make(requireView(), text, Snackbar.LENGTH_SHORT).show()
    }


}