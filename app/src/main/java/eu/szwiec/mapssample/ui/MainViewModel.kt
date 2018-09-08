package eu.szwiec.mapssample.ui

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.Marker
import eu.szwiec.mapssample.BR
import eu.szwiec.mapssample.R
import eu.szwiec.mapssample.data.Place
import eu.szwiec.mapssample.repository.Repository
import me.tatarka.bindingcollectionadapter2.ItemBinding


class MainViewModel(context: Context, repository: Repository) : ViewModel() {

    val items: ObservableList<Place> = ObservableArrayList()
    var itemBinding = ItemBinding.of<Place>(BR.place, R.layout.item).bindExtra(BR.mainViewModel, this)
    val places = repository.getNearbyRestaurants(context.getString(R.string.zomato_key), -33.8670522, 151.1957362)
    var markers: List<Marker> = emptyList()
    val clickedMarker = MutableLiveData<Marker>()

    fun setupList(places: List<Place>) {
        items.addAll(places)
    }

    fun onClickListItem(name: String) {
        val marker = markers.find { it.title == name }
        marker?.showInfoWindow()
        clickedMarker.value = marker
    }
}
