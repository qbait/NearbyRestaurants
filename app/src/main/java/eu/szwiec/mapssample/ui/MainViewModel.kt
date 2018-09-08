package eu.szwiec.mapssample.ui

import android.content.Context
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.ViewModel
import eu.szwiec.mapssample.BR
import eu.szwiec.mapssample.R
import eu.szwiec.mapssample.api.ApiResponse
import eu.szwiec.mapssample.api.ApiSuccessResponse
import eu.szwiec.mapssample.api.ZomatoService
import eu.szwiec.mapssample.data.Place
import me.tatarka.bindingcollectionadapter2.ItemBinding


class MainViewModel(context: Context, zomatoService: ZomatoService) : ViewModel() {

    val items: ObservableList<Place> = ObservableArrayList()
    val itemBinding: ItemBinding<Place> = ItemBinding.of(BR.item, R.layout.item)
    val places = zomatoService.nearbyRestaurants(context.getString(R.string.zomato_key), -33.8670522, 151.1957362)

    fun showPlaces(response: ApiResponse<List<Place>>) {
        when (response) {
            is ApiSuccessResponse -> {
                val places = response.body
                items.addAll(places)
            }
        }
    }
}
