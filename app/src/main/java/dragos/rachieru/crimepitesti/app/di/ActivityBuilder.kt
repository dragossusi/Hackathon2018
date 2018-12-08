package dragos.rachieru.crimepitesti.app.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dragos.rachieru.crimepitesti.add.di.MainModule
import dragos.rachieru.crimepitesti.add.view.AddActivity
import dragos.rachieru.crimepitesti.main.di.HeatMapModule
import dragos.rachieru.crimepitesti.main.view.HeatMapActivity

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [MainModule::class])
    abstract fun add(): AddActivity

    @ContributesAndroidInjector(modules = [HeatMapModule::class])
    abstract fun heatMap(): HeatMapActivity
}