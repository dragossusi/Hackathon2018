package dragos.rachieru.crimepitesti.main.di

import dagger.Module
import dagger.Provides
import dragos.rachieru.crimepitesti.api.CrimePitestiApi
import dragos.rachieru.crimepitesti.main.presenter.HeatMapPresenter
import dragos.rachieru.crimepitesti.main.presenter.IHeatMapPresenter
import dragos.rachieru.crimepitesti.main.view.HeatMapActivity
import dragos.rachieru.crimepitesti.main.view.IHeatMapViewDelegate

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
@Module
class HeatMapModule {

    @Provides
    fun viewDelegate(activity: HeatMapActivity): IHeatMapViewDelegate = activity

    @Provides
    fun presenter(api: CrimePitestiApi, viewDelegate: IHeatMapViewDelegate)
            : IHeatMapPresenter = HeatMapPresenter(api, viewDelegate)

}