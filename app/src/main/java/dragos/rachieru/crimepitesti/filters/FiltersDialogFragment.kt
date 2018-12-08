package dragos.rachieru.crimepitesti.filters

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import dragos.rachieru.crimepitesti.R
import dragos.rachieru.crimepitesti.extensions.toDateString
import kotlinx.android.synthetic.main.fragment_filters.*
import java.util.*

/**
 * CrimePitesti by Imbecile Games
 *
 * @since 08.12.2018
 * @author Dragos
 */
class FiltersDialogFragment : DialogFragment(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.Dialog)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.text_start_date -> {
                val calendar = startDate ?: Calendar.getInstance()
                DatePickerDialog(
                    requireContext(),
                    { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                        calendar.set(year, month, dayOfMonth)
                        startDate = calendar
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            R.id.text_end_date -> {
                val calendar = endDate ?: Calendar.getInstance()
                DatePickerDialog(
                    requireContext(),
                    { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                        calendar.set(year, month, dayOfMonth)
                        endDate = calendar
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
            R.id.image_start_close -> startDate = null
            R.id.image_end_close -> endDate = null
            R.id.btn_filter -> {
                listener?.invoke(startDate, endDate)
                dismiss()
            }
        }
    }

    var listener: ((Calendar?, Calendar?) -> Unit)? = null

    var startDate: Calendar? = null
        set(value) {
            field = value
            value?.run {
                text_start_date.text = time.toDateString()
            } ?: text_start_date.setText(R.string.pick_a_date)
        }

    var endDate: Calendar? = null
        set(value) {
            field = value
            value?.run {
                text_end_date.text = time.toDateString()
            } ?: text_end_date.setText(R.string.pick_a_date)
        }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        text_start_date.setOnClickListener(this)
        text_end_date.setOnClickListener(this)
        image_start_close.setOnClickListener(this)
        image_end_close.setOnClickListener(this)
        btn_filter.setOnClickListener(this)
    }

}