package dragos.rachieru.crimepitesti.app.di

import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import dragos.rachieru.crimepitesti.app.CrimeApp
import javax.inject.Singleton


/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ActivityBuilder::class,
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<CrimeApp> {

//    fun inject(app: CrimeApp)
}