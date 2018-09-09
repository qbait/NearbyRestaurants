package eu.szwiec.mapssample.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.Marker
import eu.szwiec.mapssample.BR
import eu.szwiec.mapssample.R
import eu.szwiec.mapssample.data.Place
import eu.szwiec.mapssample.data.Status
import eu.szwiec.mapssample.repository.Repository
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList


class MainViewModel(repository: Repository) : ViewModel() {

    val items: DiffObservableList<Place> = DiffObservableList(object : DiffObservableList.Callback<Place> {
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean {
            return areContentsTheSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean {
            return oldItem.name == newItem.name
        }
    })
    var itemBinding = ItemBinding.of<Place>(BR.place, R.layout.item).bindExtra(BR.mainViewModel, this)
    val loadPlaces = Transformations.map(repository.getNearbyRestaurants()) { resource ->
        status.value =  resource.status
        resource
    }
    var markers: List<Marker> = emptyList()
    val clickedMarker = MutableLiveData<Marker>()
    val status = MutableLiveData<Status>()

    fun setupList(places: List<Place>) {
        items.update(places)
    }

    fun onClickListItem(name: String) {
        val marker = markers.find { it.title == name }
        marker?.showInfoWindow()
        clickedMarker.value = marker
    }
}
