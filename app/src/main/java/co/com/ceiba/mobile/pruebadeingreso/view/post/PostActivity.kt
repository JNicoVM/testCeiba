package co.com.ceiba.mobile.pruebadeingreso.view.post

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.adapters.PostAdapter
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity : AppCompatActivity() {

    //Binding variable
    private lateinit var binding: ActivityPostBinding
    //@Deprecated progress bar
    private lateinit var loadingDialog : ProgressDialog
    //ViewModel variable
    private val viewModel: PostViewModel by viewModels()
    //Post Adapter
    private lateinit var postAdapter : PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)

        /**
         * @Deprecated To use a progressbar||progressDialog is recommended to use a view component in a constraintLayout
         */
        loadingDialog = ProgressDialog.show(
            this, "",
            "Loading...", true
        )
        loadInitialData()
        initRecyclerView()
        getPosts()
        setContentView(binding.root)

    }

    /**
     * Load data from intent extras
     */
    private fun loadInitialData(){
        binding.name.text =viewModel.name
        binding.phone.text = viewModel.phone
        binding.email.text = viewModel.email
    }


    /**
     * Init Recycler view
     */
    private fun initRecyclerView(){
        postAdapter = PostAdapter()
        binding.recyclerViewPostsResults.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPostsResults.adapter = postAdapter
    }

    /**
     * Call to get the posts from @APIService
     */
    private fun getPosts(){
        viewModel.getPostList()
        lifecycleScope.launchWhenCreated {
            viewModel.postCall.observe(this@PostActivity, Observer { postEvent ->
                when(postEvent){
                    is PostViewModel.PostEvent.Success ->{
                        loadingDialog.hide()
                        binding.textError.visibility = View.GONE
                        binding.recyclerViewPostsResults.visibility =  View.VISIBLE
                        postAdapter.differ.submitList(postEvent.posts)
                    }
                    is PostViewModel.PostEvent.Failure ->{
                        loadingDialog.hide()
                        binding.textError.visibility = View.VISIBLE
                        binding.recyclerViewPostsResults.visibility =  View.GONE
                        Toast.makeText(this@PostActivity, "No data found", Toast.LENGTH_SHORT).show()
                    }
                    is PostViewModel.PostEvent.Loading ->{
                        loadingDialog.show()
                    }
                    else -> Unit
                }
            })
        }
    }

}