package dragos.rachieru.crimepitesti.main.presenter

import dragos.rachieru.crimepitesti.api.CrimePitestiApi
import dragos.rachieru.crimepitesti.base.Presenter
import dragos.rachieru.crimepitesti.extensions.toApiString
import dragos.rachieru.crimepitesti.main.view.IHeatMapViewDelegate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
class HeatMapPresenter(
    val api: CrimePitestiApi,
    val viewDelegate: IHeatMapViewDelegate
) : Presenter(), IHeatMapPresenter {

    override fun getEvents(
        startPlaceLat: Double,
        startPlaceLon: Double,
        endPlaceLat: Double,
        endPlaceLon: Double,
        startDate: Date,
        endDate: Date
    ) {
        addDisposable(
            api.getEvents(
                startPlaceLat,
                startPlaceLon,
                endPlaceLat,
                endPlaceLon,
                startDate.toApiString(),
                endDate.toApiString()
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onSuccess = {
                    if (it.success) viewDelegate.onEvents(it.data!!)
                    else viewDelegate.onError(Throwable(it.errorMessage))
                }, onError = viewDelegate::onError)
        )
    }
}