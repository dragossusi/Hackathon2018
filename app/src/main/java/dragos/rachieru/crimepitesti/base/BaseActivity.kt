package dragos.rachieru.crimepitesti.base

import dagger.android.support.DaggerAppCompatActivity
import dragos.rachieru.crimepitesti.extensions.alert

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
abstract class BaseActivity : DaggerAppCompatActivity(),ViewDelegate {

    override fun onError(t: Throwable) {
        if (t is StringResThrowable) alert(t.stringRes)
        else alert(t.message ?: t.localizedMessage)
    }
}