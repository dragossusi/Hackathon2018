package dragos.rachieru.crimepitesti.main.view

import dragos.rachieru.crimepitesti.api.request.EventRequest
import dragos.rachieru.crimepitesti.base.ViewDelegate

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
interface IHeatMapViewDelegate : ViewDelegate {
    fun onEvents(events: List<EventRequest>)

}