package eu.szwiec.mapssample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import eu.szwiec.mapssample.R
import eu.szwiec.mapssample.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.let {
            it.mainViewModel = mainViewModel
            it.setLifecycleOwner(this)
        }

        mainViewModel.places.observe(this, Observer { response ->
            mainViewModel.showPlaces(response)
        })
    }
}
