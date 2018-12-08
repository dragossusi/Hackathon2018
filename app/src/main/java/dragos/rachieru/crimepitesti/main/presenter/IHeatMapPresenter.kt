package dragos.rachieru.crimepitesti.main.presenter

import com.google.android.gms.maps.model.LatLng
import dragos.rachieru.crimepitesti.base.IPresenter
import java.util.*

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
interface IHeatMapPresenter : IPresenter {

    fun getEvents(
        startPlaceLat: Double,
        startPlaceLon: Double,
        endPlaceLat: Double,
        endPlaceLon: Double,
        startDate: Date = Calendar.getInstance().run {
            set(Calendar.YEAR, get(Calendar.YEAR) - 1)
            time
        },
        endDate: Date = Date()
    )
}