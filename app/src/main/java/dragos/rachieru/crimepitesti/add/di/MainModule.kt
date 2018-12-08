package dragos.rachieru.crimepitesti.add.di

import dagger.Module
import dagger.Provides
import dragos.rachieru.crimepitesti.add.presenter.IMainPresenter
import dragos.rachieru.crimepitesti.add.presenter.MainPresenter
import dragos.rachieru.crimepitesti.add.view.IAddViewDelegate
import dragos.rachieru.crimepitesti.add.view.AddActivity
import dragos.rachieru.crimepitesti.api.CrimePitestiApi

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
@Module
class MainModule {

    @Provides
    fun viewDelegate(mainActivity: AddActivity)
            : IAddViewDelegate = mainActivity

    @Provides
    fun presenter(api: CrimePitestiApi, viewDelegate: IAddViewDelegate)
            : IMainPresenter = MainPresenter(api, viewDelegate)

}