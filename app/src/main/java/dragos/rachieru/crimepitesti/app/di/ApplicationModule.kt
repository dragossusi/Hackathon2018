package dragos.rachieru.crimepitesti.app.di

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dragos.rachieru.crimepitesti.api.CrimePitestiApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun api(): CrimePitestiApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://hcpit2018.sanctuarul.ro/api/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .setDateFormat("yyyy/MM/dd HH:mm:ss")
                        .create()
                )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create()
    }
}