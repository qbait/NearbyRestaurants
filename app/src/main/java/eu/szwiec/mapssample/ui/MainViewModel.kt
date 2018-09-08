package eu.szwiec.mapssample.ui

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.ViewModel
import eu.szwiec.mapssample.BR
import eu.szwiec.mapssample.R
import eu.szwiec.mapssample.data.Place
import eu.szwiec.mapssample.repository.Repository
import me.tatarka.bindingcollectionadapter2.ItemBinding


class MainViewModel(context: Context, repository: Repository) : ViewModel() {

    val items: ObservableList<Place> = ObservableArrayList()
    val itemBinding: ItemBinding<Place> = ItemBinding.of(BR.item, R.layout.item)
    val places = repository.getNearbyRestaurants(context.getString(R.string.zomato_key), -33.8670522, 151.1957362)

    fun showPlaces(places: List<Place>) {
        items.addAll(places)
    }
}
