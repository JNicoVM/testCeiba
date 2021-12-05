package co.com.ceiba.mobile.pruebadeingreso.view.post

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import co.com.ceiba.mobile.pruebadeingreso.databinding.ActivityPostBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostActivity : AppCompatActivity() {

    //Binding variable
    private lateinit var binding: ActivityPostBinding

    //ViewModel variable
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadInitialData()
    }

    /**
     * Load data from intent extras
     */
    private fun loadInitialData(){
        binding.name.text =viewModel.name
        binding.phone.text = viewModel.phone
        binding.email.text = viewModel.email
    }

}