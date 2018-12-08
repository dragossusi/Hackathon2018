package dragos.rachieru.crimepitesti.api.response

import com.google.gson.annotations.SerializedName

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
class CompleteResponse(
    val success: Boolean,
    @SerializedName("error_msg")
    val errorMessage: String
)