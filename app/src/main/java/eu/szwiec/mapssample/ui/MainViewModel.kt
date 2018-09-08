package eu.szwiec.mapssample.ui

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.ViewModel
import eu.szwiec.mapssample.BR
import eu.szwiec.mapssample.R
import me.tatarka.bindingcollectionadapter2.ItemBinding


class MainViewModel : ViewModel() {
    val items: ObservableList<String> = ObservableArrayList()
    val itemBinding: ItemBinding<String> = ItemBinding.of(BR.item, R.layout.item)

    init {
        items.add("Jakub")
        items.add("Szwiec")
    }
}
