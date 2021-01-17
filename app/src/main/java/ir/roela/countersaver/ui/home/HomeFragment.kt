package ir.roela.countersaver.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.material.snackbar.Snackbar
import ir.hamsaa.persiandatepicker.Listener
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.util.PersianCalendar
import ir.roela.countersaver.R
import ir.roela.countersaver.model.Count


class HomeFragment : Fragment(), View.OnClickListener, UserCounter.View {

    private var textView: TextView? = null
    private var btnCounterSave: ImageButton? = null

    private var count = Count.instance
    private var counterPresenter = CounterPresenter(this, count!!)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        textView = root.findViewById(R.id.text_home)
        val btnCounterPlus: ImageButton = root.findViewById(R.id.btnInCreaseCounter)
        val btnCounterMinus: ImageButton = root.findViewById(R.id.btnDeCreaseCounter)
        val btnResetCounter: ImageButton = root.findViewById(R.id.btnResetCounter)
        btnCounterSave = root.findViewById(R.id.btnSaveCounter)
        btnCounterPlus.setOnClickListener(this)
        btnCounterMinus.setOnClickListener(this)
        btnResetCounter.setOnClickListener(this)
        btnCounterSave?.setOnClickListener(this)
        counterPresenter.initCounterText()
        return root
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnInCreaseCounter -> {
                counterPresenter.increaseCount()
                startTextAnimation()
            }
            R.id.btnDeCreaseCounter -> {
                counterPresenter.decreaseCount()
                startTextAnimation()
            }
            R.id.btnResetCounter -> {
                count?.resetCount()
                txtCountSetText()
            }
            R.id.btnSaveCounter -> showDialogSave()
        }
    }

    private fun showDialogSave() {
        if (count?.countNumber != 0L) {
            val saveDialog = AlertDialog.Builder(requireActivity()).create()
            val dlgView = LayoutInflater.from(context).inflate(R.layout.dlg_save_counter, null)
            val txtCounterToSave = dlgView.findViewById<TextView>(R.id.txtCounterToSave)
            val txtSelectedDate = dlgView.findViewById<TextView>(R.id.txtSelectedDate)
            val edtCountName = dlgView.findViewById<EditText>(R.id.edtCountName)
            val btnSelectDate = dlgView.findViewById<ImageButton>(R.id.btnSelectDate)
            val btnCancelDlg = dlgView.findViewById<Button>(R.id.btnCancelDlg)
            val btnDlgSaveCount = dlgView.findViewById<Button>(R.id.btnDlgSaveCount)
            txtCounterToSave.text = count?.countNumberStr
            txtSelectedDate.text = PersianCalendar().persianLongDate
            btnSelectDate.setOnClickListener {
                openDatePicker(object : SelectDate {
                    override fun onSelectDate(selectedDate: String) {
                        txtSelectedDate.text = selectedDate
                    }
                })
            }
            btnCancelDlg.setOnClickListener {
                saveDialog.dismiss()
            }
            btnDlgSaveCount.setOnClickListener {
                val name = edtCountName.text.toString().trim()
                counterPresenter.saveCount(requireActivity().application,
                    name,
                    object : UserCounter.SaveDialogListener {
                        override fun saveSuccess() {
                            showSnackBar(R.string.saved)
                        }

                        override fun showError(message: String) {
                            edtCountName.error = message
                        }

                        override fun dismissDialog() {
                            saveDialog.dismiss()
                        }
                    })
            }
            saveDialog.setView(dlgView)
            saveDialog.show()
        } else showSnackBar(R.string.please_increase_counter)
    }

    private interface SelectDate {
        fun onSelectDate(selectedDate: String)
    }

    private fun openDatePicker(selectDate: SelectDate) {
        val txtColor = ContextCompat.getColor(requireContext(), R.color.colorPrimary)
        val initDate = PersianCalendar()
        initDate.setPersianDate(
            initDate.persianYear,
            initDate.persianMonth,
            initDate.persianDay
        )
        val picker = PersianDatePickerDialog(requireActivity())
            .setPositiveButtonString("انتخاب")
            .setNegativeButton("بیخیال")
            .setTodayButton("امروز")
            .setTodayButtonVisible(true)
            .setMinYear(initDate.persianYear - 1)
            .setMaxYear(PersianDatePickerDialog.THIS_YEAR)
            .setInitDate(initDate)
            .setTitleColor(txtColor)
            .setActionTextColor(txtColor)
            .setTitleType(PersianDatePickerDialog.WEEKDAY_DAY_MONTH_YEAR)
            .setShowInBottomSheet(true)
            .setListener(object : Listener {
                override fun onDateSelected(persianCalendar: PersianCalendar) {
                    selectDate.onSelectDate(persianCalendar.persianLongDate)
                }

                override fun onDismissed() {
                    Toast.makeText(
                        requireContext(),
                        R.string.no_select_date,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        picker.show()
    }

    private fun showSnackBar(resString: Int) {
        try {
            Snackbar
                .make(
                    activity?.window!!.findViewById(android.R.id.content),
                    resString,
                    Snackbar.LENGTH_SHORT
                ).setBackgroundTint(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
                .show()
        } catch (error: Exception) {
            Log.e("Counter", error.message.toString())
        }
    }

    private fun startTextAnimation() {
        YoYo.with(Techniques.Landing)
            .duration(500)
            .playOn(textView)
    }

    override fun txtCountSetText() {
        textView?.text = count?.countNumberStr
    }

    override fun enableSaveCountButton(isEnable: Boolean) {
//        btnCounterSave?.isEnabled = isEnable
    }

}