package dragos.rachieru.crimepitesti.add.presenter

import com.google.android.gms.location.places.Place
import dragos.rachieru.crimepitesti.R
import dragos.rachieru.crimepitesti.add.view.IAddViewDelegate
import dragos.rachieru.crimepitesti.api.CrimePitestiApi
import dragos.rachieru.crimepitesti.api.request.EventRequest
import dragos.rachieru.crimepitesti.api.response.CrimeCategory
import dragos.rachieru.crimepitesti.base.Presenter
import dragos.rachieru.crimepitesti.base.StringResThrowable
import dragos.rachieru.crimepitesti.extensions.toApiString
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import java.util.*

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
class MainPresenter(
    val api: CrimePitestiApi,
    val viewDelegate: IAddViewDelegate
) : Presenter(), IMainPresenter {

    override fun getCategories(filter: String?) {
        addDisposable(
            api.getCategories(filter)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(onSuccess = {
                    viewDelegate.onCategories(it)
                }, onError = Throwable::printStackTrace)
        )
    }

    override fun sendEvent(
        place: Place?,
        item: CrimeCategory?,
        date: Calendar?,
        checked: Boolean,
        description: String
    ) {
        addDisposable(
            checkData(place, item, date, checked, description)
                .flatMap {
                    val mediaType = MediaType.parse("text/plain")
                    api.sendEvent(
                        RequestBody.create(mediaType, it.latitude.toString()),
                        RequestBody.create(mediaType, it.longitude.toString()),
                        RequestBody.create(mediaType, it.categoryId.toString()),
                        RequestBody.create(mediaType, it.date.toApiString()),
                        RequestBody.create(mediaType, it.calledPolice.toString()),
                        RequestBody.create(mediaType, it.description)
                    )
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onSuccess = {
                    if (it.success) viewDelegate.onSent()
                    else viewDelegate.onError(Throwable(it.errorMessage))
                }, onError = viewDelegate::onError)
        )
    }

    private fun checkData(
        place: Place?,
        item: CrimeCategory?,
        date: Calendar?,
        checked: Boolean,
        description: String
    ) = Single.fromCallable {
        if (place == null)
            throw StringResThrowable(R.string.error_place_not_set)
        if (item == null)
            throw StringResThrowable(R.string.error_category_not_set)
        if (date == null)
            throw StringResThrowable(R.string.error_date_not_set)
        if (description.isEmpty())
            throw StringResThrowable(R.string.error_empty_description)
        return@fromCallable EventRequest(
            place.latLng.latitude,
            place.latLng.longitude,
            item.id,
            date.time,
            if (checked) 1 else 0,
            description
        )

    }
}