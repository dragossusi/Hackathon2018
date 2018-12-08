package dragos.rachieru.crimepitesti.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
open class Presenter : IPresenter {

    val compositeDisposable = CompositeDisposable()

    fun addDisposable(d: Disposable) = compositeDisposable.add(d)

    override fun clear() = compositeDisposable.clear()

}