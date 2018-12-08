package dragos.rachieru.crimepitesti.add.view

import android.view.View
import android.view.ViewGroup
import android.widget.*
import dragos.rachieru.crimepitesti.R
import dragos.rachieru.crimepitesti.api.response.CrimeCategory
import dragos.rachieru.crimepitesti.extensions.inflate

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
class CategoriesAdapter : BaseAdapter(), Filterable {

    fun add(element: CrimeCategory) {
        allItems.add(element)
        notifyDataSetChanged()
    }

    fun addAll(elements: Collection<CrimeCategory>) {
        allItems.addAll(elements)
        notifyDataSetChanged()
    }

    fun clear() {
        allItems.clear()
        notifyDataSetChanged()
    }

    val allItems = ArrayList<CrimeCategory>()
    var filteredItems = ArrayList<CrimeCategory>()

    override fun getView(pos: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: parent.inflate(R.layout.item_category)
        (view as TextView).text = filteredItems[pos].name
        return view
    }

    override fun getItem(p0: Int): CrimeCategory? {
        if (p0 == ListView.INVALID_POSITION) return null
        return filteredItems[p0]
    }

    override fun getItemId(p0: Int): Long {
        return filteredItems[p0].id.toLong()
    }

    override fun getCount() = filteredItems.size

    val _filter = object : Filter() {
        override fun performFiltering(p0: CharSequence?): FilterResults {
            val results = FilterResults()
            if (p0.isNullOrEmpty()) {
                results.values = allItems
                results.count = allItems.size
            } else {
                val text = p0.toString().toLowerCase()
                val list = ArrayList<CrimeCategory>()
                allItems.forEach {
                    if (it.name.toLowerCase().contains(text))
                        list.add(it)
                }
                results.values = list
                results.count = list.size
            }
            return results
        }

        override fun publishResults(p0: CharSequence?, p1: FilterResults) {
            @Suppress("UNCHECKED_CAST")
            filteredItems = p1.values as ArrayList<CrimeCategory>
            notifyDataSetChanged()
        }

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as CrimeCategory).name
//            return super.convertResultToString(resultValue)
        }
    }

    override fun getFilter() = _filter

}