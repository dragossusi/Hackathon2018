package dragos.rachieru.crimepitesti.main.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.TileOverlay
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.android.gms.maps.model.TileProvider
import com.google.maps.android.heatmaps.HeatmapTileProvider
import dragos.rachieru.crimepitesti.R
import dragos.rachieru.crimepitesti.add.view.AddActivity
import dragos.rachieru.crimepitesti.api.request.EventRequest
import dragos.rachieru.crimepitesti.base.BaseActivity
import dragos.rachieru.crimepitesti.filters.FiltersDialogFragment
import dragos.rachieru.crimepitesti.main.presenter.IHeatMapPresenter
import java.util.*
import javax.inject.Inject


/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
class HeatMapActivity : BaseActivity(), OnMapReadyCallback, IHeatMapViewDelegate, GoogleMap.OnCameraIdleListener {


    private var _provider: TileProvider? = null
    private var _overlay: TileOverlay? = null

    @Inject
    lateinit var presenter: IHeatMapPresenter

    lateinit var _map: GoogleMap

    var startDate: Calendar = Calendar.getInstance().apply {
        set(Calendar.YEAR, get(Calendar.YEAR) - 1)
    }

    var endDate: Calendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heatmap)
        (supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment)
            .getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_filter -> {
                val dialog = FiltersDialogFragment()
                dialog.listener = { start, end ->
                    startDate = start ?: Calendar.getInstance().apply {
                        set(Calendar.YEAR, get(Calendar.YEAR) - 1)
                    }
                    endDate = end ?: Calendar.getInstance()
                    _map.projection.visibleRegion.latLngBounds.run {
                        presenter.getEvents(
                            southwest.latitude,
                            northeast.longitude,
                            northeast.latitude,
                            southwest.longitude,
                            startDate.time,
                            endDate.time
                        )
                    }
                }
                dialog.show(supportFragmentManager, "asdfg")

                true
            }
            R.id.action_add -> {
                startActivity(Intent(this, AddActivity::class.java))
                true
            }
            else -> false
        }
    }

    override fun onMapReady(map: GoogleMap) {
        _map = map
        map.animateCamera(CameraUpdateFactory.newLatLng(LatLng(44.8511517, 24.8634237)))
        map.setOnCameraIdleListener(this)
        val bounds = map.projection.visibleRegion.latLngBounds

        bounds.run {
            presenter.getEvents(
                southwest.latitude,
                northeast.longitude,
                northeast.latitude,
                southwest.longitude,
                startDate.time,
                endDate.time
            )
        }
    }

    override fun onEvents(events: List<EventRequest>) {
        if (events.isNotEmpty()) {
            val latlngs = ArrayList<LatLng>()
            events.forEach {
                latlngs.add(LatLng(it.latitude, it.longitude))
            }
            _provider = HeatmapTileProvider.Builder()
                .data(latlngs)
                .build()
            // Add a tile overlay to the map, using the heat map tile provider.
            _overlay?.remove()
            _overlay = _map.addTileOverlay(TileOverlayOptions().tileProvider(_provider))
        }
    }

    override fun onCameraIdle() {
        val bounds = _map.projection.visibleRegion.latLngBounds

        bounds.run {
            presenter.getEvents(
                southwest.latitude,
                northeast.longitude,
                northeast.latitude,
                southwest.longitude,
                startDate.time,
                endDate.time
            )
        }
    }

}