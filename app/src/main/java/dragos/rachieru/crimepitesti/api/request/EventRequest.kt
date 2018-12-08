package dragos.rachieru.crimepitesti.api.request

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
class EventRequest(
    @SerializedName("gps_lat")
    val latitude: Double,
    @SerializedName("gps_long")
    val longitude: Double,
    @SerializedName("id_category")
    val categoryId: Int,
    @SerializedName("date_text")
    val date: Date,
    @SerializedName("called_112")
    val calledPolice: Int,
    @SerializedName("description")
    val description: String
) {
//    @SerializedName("id_device")
//    val idDevice = 2
}