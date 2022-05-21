package com.anypli.pixabayapp.ui.images

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.anypli.pixabayapp.R
import com.anypli.pixabayapp.base.BaseActivity
import com.anypli.pixabayapp.data.retrofit.EndPointInterceptor
import com.anypli.pixabayapp.databinding.ActivityImagesBinding
import com.anypli.pixabayapp.ui.images.paging.ImagePaginationAdapter
import com.anypli.pixabayapp.ui.images.paging.LoaderStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ImagesActivity : BaseActivity() {

    private lateinit var binding: ActivityImagesBinding

    private val viewModel: ImagesViewModel by viewModels()

    @Inject
    lateinit var  imagePaginationAdapter: ImagePaginationAdapter

    @Inject
    lateinit var loaderStateAdapter: LoaderStateAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        registerBaseObservers(viewModel)
        showImagePagination()


    }

    private fun showImagePagination() {


        initPagination()
        searchImage()
        registerImageAdapter()
        loadStateImageAdapter()
        retryPaginationImage()

    }

    private fun initPagination(){
        lifecycleScope.launch {
            //view Pagination
            viewModel.searchImages().collectLatest {
                imagePaginationAdapter.submitData(it)
            }
        }

    }
    private fun searchImage(){
        viewModel.criteria.observe(this, Observer {  text->
            lifecycleScope.launch {

                viewModel.searchImagesByCriteria(text).collectLatest { pagingData->
                    imagePaginationAdapter.submitData(pagingData)
                }
            }
        })
    }

    private fun registerImageAdapter(){
        binding.recyclerPaging.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerPaging.adapter = imagePaginationAdapter.withLoadStateFooter(loaderStateAdapter)
    }

    private fun loadStateImageAdapter(){
        imagePaginationAdapter.addLoadStateListener { loadState->

            loadStateVisibility(loadState.refresh)

            //show Empty Message
            if(loadState.refresh is LoadState.NotLoading ){

                if (imagePaginationAdapter.itemCount <1){
                    binding.emptyText.visibility = View.VISIBLE
                }
                else{
                    binding.emptyText.visibility = View.GONE

                }
            }

        }
    }

    private fun loadStateVisibility(loadState: LoadState) {
        when (loadState) {
            is LoadState.Loading -> {
                binding.progressSearch.visibility = View.VISIBLE
                binding.errorText.visibility = View.GONE
                binding.btnRetry.visibility = View.GONE
                binding.emptyText.visibility = View.GONE
            }
            is LoadState.Error -> {

                if(loadState.error is EndPointInterceptor.NetworkNotFoundException){
                    binding.errorText.text = applicationContext.getString(R.string.global_error_internet)
                }
                else{
                    binding.errorText.text = loadState.error.message

                }
                binding.progressSearch.visibility = View.GONE
                binding.errorText.visibility = View.VISIBLE
                binding.btnRetry.visibility = View.VISIBLE
                binding.swipeLayout.visibility = View.GONE
                binding.emptyText.visibility = View.GONE

            }
            is LoadState.NotLoading -> {

                binding.progressSearch.visibility = View.GONE
                binding.errorText.visibility = View.GONE
                binding.btnRetry.visibility = View.GONE
                binding.swipeLayout.visibility = View.VISIBLE
                binding.swipeLayout.isRefreshing = false

            }
        }
    }

    private fun retryPaginationImage(){
        binding.btnRetry.setOnClickListener {
            imagePaginationAdapter.retry()
        }
        binding.swipeLayout.setOnRefreshListener {
            imagePaginationAdapter.refresh()

        }
    }






}