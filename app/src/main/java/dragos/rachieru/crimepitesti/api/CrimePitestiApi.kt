package dragos.rachieru.crimepitesti.api

import dragos.rachieru.crimepitesti.api.request.EventRequest
import dragos.rachieru.crimepitesti.api.response.CompleteResponse
import dragos.rachieru.crimepitesti.api.response.CrimeCategory
import dragos.rachieru.crimepitesti.base.BaseResponse
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.http.*

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
interface CrimePitestiApi {

    @GET("static/categories")
    fun getCategories(@Query("filter") filter: String? = null)
            : Single<List<CrimeCategory>>

//    @POST("events/register")
//    fun sendEvent(@Body event: EventRequest): Single<CompleteResponse>

    @Multipart
    @POST("events/register")
    fun sendEvent(
        @Part("gps_lat") latitude: RequestBody,
        @Part("gps_long") longitude: RequestBody,
        @Part("id_category") categoryId: RequestBody,
        @Part("date_text") date: RequestBody,
        @Part("called_112") calledPolice: RequestBody,
        @Part("description") description: RequestBody
    ): Single<CompleteResponse>

    @GET("events/boundary-filtered")
    fun getEvents(
        @Query("gps_lat_p1") latStart: Double,
        @Query("gps_long_p1") longStart: Double,
        @Query("gps_lat_p2") latLong: Double,
        @Query("gps_long_p2") longEnd: Double,
        @Query("date_start") startDate: String,
        @Query("date_end") dateEnd: String,
        @Query("id_category") categoryId: Int = 0
    ): Single<BaseResponse<List<EventRequest>>>

}