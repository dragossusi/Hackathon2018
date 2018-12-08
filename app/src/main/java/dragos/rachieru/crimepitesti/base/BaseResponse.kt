package dragos.rachieru.crimepitesti.base

import com.google.gson.annotations.SerializedName

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
class BaseResponse<T>(
    val success: Boolean,
    val data: T?,
    @SerializedName("error_msg")
    val errorMessage: String
)