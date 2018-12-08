package dragos.rachieru.crimepitesti.add.view

import dragos.rachieru.crimepitesti.api.response.CrimeCategory
import dragos.rachieru.crimepitesti.base.ViewDelegate

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
interface IAddViewDelegate : ViewDelegate {
    fun onCategories(it: List<CrimeCategory>)
    fun onSent()
}