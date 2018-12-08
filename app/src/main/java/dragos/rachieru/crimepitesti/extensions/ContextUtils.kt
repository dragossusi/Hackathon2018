package dragos.rachieru.crimepitesti.extensions

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */

fun Context.alert(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.alert(@StringRes messageId: Int) {
    Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
}