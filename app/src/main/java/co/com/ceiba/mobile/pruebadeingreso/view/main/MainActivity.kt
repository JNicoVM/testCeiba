package co.com.ceiba.mobile.pruebadeingreso.view.main

import android.os.Bundle
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import android.app.ProgressDialog
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.adapters.UserAdapter


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //Binding variable
    private lateinit var binding : ActivityMainBinding
    //@Deprecated progress bar
    private lateinit var loadingDialog : ProgressDialog
    //ViewModel variable
    private val viewModel : MainViewModel by viewModels()
    //User Adapter
    private lateinit var userAdapter : UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        /**
         * @Deprecated To use a progressbar||progressDialog is recommended to use a view component in a constraintLayout
         */
        loadingDialog = ProgressDialog.show(
            this, "",
            "Loading...", true
        )
        initRecyclerView()
        initEditTextView()
        getUsers()
        setContentView(binding.root)
    }

    /**
     * Init Recycler view
     */
    private fun initRecyclerView(){
        userAdapter = UserAdapter()
        binding.recyclerViewSearchResults.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewSearchResults.adapter = userAdapter
    }

    /**
     * Init EditText view
     */
    private fun initEditTextView(){
        binding.editTextSearch.doAfterTextChanged {
            lifecycleScope.launchWhenCreated {
                viewModel.getUserBySearchInput(it.toString())
            }
        }
    }

    /**
     * Call to get the users from @APIService
     */
    private fun getUsers(){
        viewModel.getUserList()
        lifecycleScope.launchWhenCreated {
            viewModel.userCall.observe(this@MainActivity, Observer { mainEvent ->
               when(mainEvent){
                   is MainViewModel.MainEvent.Success ->{
                       userAdapter.differ.submitList(mainEvent.users)
                       loadingDialog.hide()
                   }
                   is MainViewModel.MainEvent.Failure ->{
                       loadingDialog.hide()
                   }
                   is MainViewModel.MainEvent.Loading ->{
                       loadingDialog.show()
                   }
                   else -> Unit
               }
            })
        }
    }

    /**
     * @Deprecated the ProgressLoading is not managed by the lifecycle of the activity
     */
    override fun onDestroy() {
        loadingDialog.cancel()
        super.onDestroy()
    }

}