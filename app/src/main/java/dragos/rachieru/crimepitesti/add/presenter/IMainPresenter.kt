package dragos.rachieru.crimepitesti.add.presenter

import com.google.android.gms.location.places.Place
import dragos.rachieru.crimepitesti.api.response.CrimeCategory
import dragos.rachieru.crimepitesti.base.IPresenter
import java.util.*

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
interface IMainPresenter : IPresenter {
    fun getCategories(filter: String?)
    abstract fun sendEvent(
        place: Place?, item: CrimeCategory?, date: Calendar?, checked: Boolean,
        description:String)
}