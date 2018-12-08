package dragos.rachieru.crimepitesti.base

import android.support.annotation.StringRes

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
class StringResThrowable(@StringRes val stringRes: Int) : Throwable() {
}