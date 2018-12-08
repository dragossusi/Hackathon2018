package dragos.rachieru.crimepitesti.add.view

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.DatePicker
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlacePicker
import dragos.rachieru.crimepitesti.R
import dragos.rachieru.crimepitesti.add.presenter.IMainPresenter
import dragos.rachieru.crimepitesti.api.response.CrimeCategory
import dragos.rachieru.crimepitesti.base.BaseActivity
import dragos.rachieru.crimepitesti.extensions.alert
import dragos.rachieru.crimepitesti.extensions.toApiString
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject


/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
class AddActivity : BaseActivity(), IAddViewDelegate, TextWatcher, View.OnClickListener,
    AdapterView.OnItemClickListener {

    private var place: Place? = null
    private var _calendar: Calendar? = null

    private var selectedCategory: CrimeCategory? = null

    @Inject
    lateinit var presenter: IMainPresenter//: CrimePitestiApi

    lateinit var adapter: CategoriesAdapter

    val compositeDelegate = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = CategoriesAdapter()
        auto_category.setAdapter(adapter)
        auto_category.addTextChangedListener(this)
        auto_category.setOnItemClickListener(this)
        text_location.setOnClickListener(this)
        text_date_time.setOnClickListener(this)
        btn_send.setOnClickListener(this)
    }

    override fun afterTextChanged(s: Editable?) = Unit

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        presenter.getCategories(s.toString())
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectedCategory = adapter.getItem(position)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.text_location -> {
                val builder = PlacePicker.IntentBuilder()
                startActivityForResult(builder.build(this), REQUEST_PLACE)
            }
            R.id.text_date_time -> {
                val calendar = _calendar ?: Calendar.getInstance()
                DatePickerDialog(
                    this,
                    { datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                        TimePickerDialog(
                            this,
                            { view, hourOfDay, minute ->
                                if (_calendar == null) _calendar = Calendar.getInstance()
                                _calendar!!.set(year, month, dayOfMonth, hourOfDay, minute, 0)
                                text_date_time.text = _calendar!!.time.toApiString()
                            },
                            calendar.get(Calendar.HOUR_OF_DAY),
                            calendar.get(Calendar.MINUTE),
                            true
                        ).show()
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            R.id.btn_send -> {
                presenter.sendEvent(
                    place,
                    selectedCategory,
                    _calendar,
                    check_reported.isChecked,
                    text_details.text.toString()
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQUEST_PLACE -> if (resultCode == Activity.RESULT_OK) {
                place = PlacePicker.getPlace(this, data)
                text_location.text = place!!.name
            }
        }
    }

    override fun onSent() {
        alert(R.string.event_sent)
    }

    override fun onPause() {
        super.onPause()
        presenter.clear()
    }

    override fun onCategories(it: List<CrimeCategory>) {
        adapter.clear()
        adapter.addAll(it)
    }

    companion object {
        const val REQUEST_PLACE = 1
    }
}
