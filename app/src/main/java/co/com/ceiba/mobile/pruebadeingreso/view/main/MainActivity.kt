package co.com.ceiba.mobile.pruebadeingreso.view.main

import android.app.Activity
import android.os.Bundle
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import android.app.ProgressDialog
import android.widget.ProgressBar


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //Binding variable
    private lateinit var binding : ActivityMainBinding
    //@Deprecated progress bar
    private lateinit var loadingDialog : ProgressDialog

    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        /**
         * @Deprecated To use a progressbar is recommended to use a view component in a constraintLayout
         */
        loadingDialog = ProgressDialog.show(
            this, "",
            "Loading...", true
        )

        getUsers()
        setContentView(binding.root)
    }

    /**
     * Call to get the users from @APIService
     */
    private fun getUsers(){
        viewModel.getUserList()
        lifecycleScope.launchWhenCreated {
            viewModel.userList.observe(this@MainActivity, Observer { mainEvent ->
               when(mainEvent){
                   is MainViewModel.MainEvent.Success ->{
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

    override fun onDestroy() {
        loadingDialog.cancel()
        super.onDestroy()
    }

}