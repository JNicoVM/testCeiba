package co.com.ceiba.mobile.pruebadeingreso.view.main

import android.os.Bundle
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import android.app.ProgressDialog
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import co.com.ceiba.mobile.pruebadeingreso.adapters.UserAdapter
import co.com.ceiba.mobile.pruebadeingreso.rest.Const
import co.com.ceiba.mobile.pruebadeingreso.view.post.PostActivity


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
        setContentView(binding.root)
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

    }

    /**
     * Init Recycler view
     */
    private fun initRecyclerView(){
        userAdapter = UserAdapter()
        binding.recyclerViewSearchResults.layoutManager = LinearLayoutManager(this)
        userAdapter.setOnItemClickListener { user->
            val intent =  Intent(this@MainActivity, PostActivity::class.java)
            intent.putExtra(Const.ID, user.id)
            intent.putExtra(Const.NAME, user.name)
            intent.putExtra(Const.EMAIL, user.email)
            intent.putExtra(Const.PHONE, user.phone)
            startActivity(intent)
        }
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
                       loadingDialog.hide()
                       binding.textError.visibility = View.GONE
                       binding.recyclerViewSearchResults.visibility =  View.VISIBLE
                       if(mainEvent.users.isNotEmpty()){
                           userAdapter.differ.submitList(mainEvent.users)
                       }else{
                           binding.textError.visibility = View.VISIBLE
                           binding.recyclerViewSearchResults.visibility =  View.GONE
                           Toast.makeText(this@MainActivity, "No data found", Toast.LENGTH_SHORT).show()
                       }
                   }
                   is MainViewModel.MainEvent.Failure ->{
                       loadingDialog.hide()
                       binding.textError.visibility = View.VISIBLE
                       binding.recyclerViewSearchResults.visibility =  View.GONE
                       Toast.makeText(this@MainActivity, "No data found", Toast.LENGTH_SHORT).show()
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