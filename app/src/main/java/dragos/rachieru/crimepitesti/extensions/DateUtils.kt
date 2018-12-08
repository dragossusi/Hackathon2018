package dragos.rachieru.crimepitesti.extensions

import java.text.SimpleDateFormat
import java.util.*

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */

val apiDateFormat = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH)
val apiDateOnlyFormat = SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH)

fun Date.toApiString(): String {
    return apiDateFormat.format(this)
}
fun Date.toDateString(): String {
    return apiDateOnlyFormat.format(this)
}