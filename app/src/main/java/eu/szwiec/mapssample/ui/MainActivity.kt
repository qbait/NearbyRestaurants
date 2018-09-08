package eu.szwiec.mapssample.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import eu.szwiec.mapssample.R
import eu.szwiec.mapssample.databinding.ActivityMainBinding
import eu.szwiec.mapssample.util.animateCamera
import eu.szwiec.mapssample.util.display
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.let {
            it.mainViewModel = mainViewModel
            it.setLifecycleOwner(this)
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        mainViewModel.places.observe(this, Observer { places ->
            mainViewModel.setupList(places)
            mainViewModel.markers = map.display(places)
        })

        mainViewModel.clickedMarker.observe(this, Observer { marker ->
            map.animateCamera(marker)
        })
    }
}
