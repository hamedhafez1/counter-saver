package ir.roela.countersaver.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.material.snackbar.Snackbar
import ir.roela.countersaver.G
import ir.roela.countersaver.R


class HomeFragment : Fragment(), View.OnClickListener, UserCounter.View {

    private var textView: TextView? = null
    private var btnCounterSave: ImageButton? = null
    private var counterPresenter = CounterPresenter(this)

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
                G.counter = 0L
                txtCountSetText()
            }
            R.id.btnSaveCounter -> showDialogSave()
        }
    }

    private fun showDialogSave() {
        if (G.counter != 0L) {
            val saveDialog = AlertDialog.Builder(requireActivity()).create()
            val dlgView = LayoutInflater.from(context).inflate(R.layout.dlg_save_counter, null)
            val txtCounterToSave = dlgView.findViewById<TextView>(R.id.txtCounterToSave)
            val edtCountName = dlgView.findViewById<EditText>(R.id.edtCountName)
            val btnCancelDlg = dlgView.findViewById<Button>(R.id.btnCancelDlg)
            val btnDlgSaveCount = dlgView.findViewById<Button>(R.id.btnDlgSaveCount)
            txtCounterToSave.text = G.counter.toString()
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
        textView?.text = G.counter.toString()
    }

    override fun enableSaveCountButton(isEnable: Boolean) {
//        btnCounterSave?.isEnabled = isEnable
    }

}