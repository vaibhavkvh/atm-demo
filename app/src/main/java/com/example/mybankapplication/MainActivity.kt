package com.example.mybankapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import androidx.databinding.DataBindingUtil
import com.example.mybankapplication.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var totalAvailableBalance = 0
    private var total2kNoteAvailable = 0
    private var total500NoteAvailable = 0
    private var total100NoteAvailable = 0
    private var total50NoteAvailable = 0
    private var total20NoteAvailable = 0
    private var total10NoteAvailable = 0
    private var withdrawalAMount = 0
    private lateinit var activityMainBinding: ActivityMainBinding

    private var mStringBuilder = StringBuilder()

    //private lateinit var mActivityViewModel: ActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(activityMainBinding.root)
        //  mActivityViewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)

        initViewObject()

    }

    private fun initViewObject() {
        activityMainBinding.apply {
            btnSave.setOnClickListener(this@MainActivity)
            btnWithdrawal.setOnClickListener(this@MainActivity)
        }
    }


    private fun saveInitialBalance() {

        if (activityMainBinding.editText.readText().isEmpty()) {
            showMessage("Please enter balance")
        } else if (!activityMainBinding.editText.readText().isDigitsOnly()) {
            showMessage("Please enter valid balance")
        } else {
            totalAvailableBalance = activityMainBinding.editText.readText().toInt()
            activityMainBinding.editText.viewGone()
            activityMainBinding.btnSave.viewGone()
            activityMainBinding.textViewAddBalance.text =
                getString(R.string.total_balance_is, totalAvailableBalance)
            createAvailableNotes()
        }
    }

    private fun createAvailableNotes() {

        when (totalAvailableBalance) {
            in 0..10 -> {
                total10NoteAvailable = totalAvailableBalance / 10
            }
            in 11..20 -> {
                total10NoteAvailable = totalAvailableBalance / 10
                total20NoteAvailable = totalAvailableBalance / 20
            }
            in 21..50 -> {
                total10NoteAvailable = totalAvailableBalance / 10
                total20NoteAvailable = totalAvailableBalance / 20
                total50NoteAvailable = totalAvailableBalance / 50

            }
            in 51..100 -> {
                total10NoteAvailable = totalAvailableBalance / 10
                total20NoteAvailable = totalAvailableBalance / 20
                total50NoteAvailable = totalAvailableBalance / 50
                total100NoteAvailable = totalAvailableBalance / 100
            }
            in 101..500 -> {
                total10NoteAvailable = totalAvailableBalance / 10
                total20NoteAvailable = totalAvailableBalance / 20
                total50NoteAvailable = totalAvailableBalance / 50
                total100NoteAvailable = totalAvailableBalance / 100
                total500NoteAvailable = totalAvailableBalance / 500

            }
            else -> {
                total10NoteAvailable = totalAvailableBalance / 10
                total20NoteAvailable = totalAvailableBalance / 20
                total50NoteAvailable = totalAvailableBalance / 50
                total100NoteAvailable = totalAvailableBalance / 100
                total500NoteAvailable = totalAvailableBalance / 500
                total2kNoteAvailable = totalAvailableBalance / 2000
            }
        }


        activityMainBinding.apply {
            noteGroup.viewVisible()
            editTextWidthdrawal.viewVisible()
            btnWithdrawal.viewVisible()
            text2KNoteCount.text = getString(R.string._2k_note_count, total2kNoteAvailable)
            text500NoteCount.text = getString(R.string._500_note_count, total500NoteAvailable)
            text100NoteCount.text = getString(R.string._100_note_count, total100NoteAvailable)
            text50NoteCount.text = getString(R.string._50_note_count, total50NoteAvailable)
            text20NoteCount.text = getString(R.string._20_note_count, total20NoteAvailable)
            text10NoteCount.text = getString(R.string._10_note_count, total10NoteAvailable)
        }
    }

    private fun showMessage(msg: String) {
        Snackbar.make(
            activityMainBinding.editText,
            msg,
            Snackbar.LENGTH_LONG
        ).show()
    }

    override fun onClick(view: View?) {
        activityMainBinding.apply {
            when (view) {
                btnSave -> {
                    saveInitialBalance()
                }
                btnWithdrawal -> {
                    deductBalance()
                }
            }
        }

    }

    private fun deductBalance() {
        if (activityMainBinding.editTextWidthdrawal.readText().isEmpty()) {
            showMessage("Please enter amount")
        } else if (activityMainBinding.editTextWidthdrawal.readText().toInt() < 10) {
            showMessage("Please enter minimum 10")
        } else {
            withdrawalAMount = activityMainBinding.editTextWidthdrawal.readText().toInt()
            activityMainBinding.editTextWidthdrawal.text?.clear()
            activityMainBinding.textViewAddBalance.text =
                getString(R.string.total_balance_is, totalAvailableBalance.minus(withdrawalAMount))
            mStringBuilder.append("--------------------------------")
            calculateNotes(withdrawalAMount)
        }
    }

    private fun calculateNotes(totalAvailableBalance: Int) {
        Log.e("withdrawalAMount is ", totalAvailableBalance.toString())
        if (totalAvailableBalance < 10) {
            return
        }
        when (totalAvailableBalance) {
            in 10..19 -> {
                total10NoteAvailable -= (totalAvailableBalance / 10)
                calculateNotes(totalAvailableBalance - ((totalAvailableBalance / 10).times(10)))
                mStringBuilder.append("10 Notes *" + totalAvailableBalance / 10)
            }
            in 21..49 -> {
                total20NoteAvailable -= totalAvailableBalance / 20
                calculateNotes(totalAvailableBalance - ((totalAvailableBalance / 20).times(20)))

                mStringBuilder.append("\n20 Notes *" + totalAvailableBalance / 20)
            }
            in 50..99 -> {

                total50NoteAvailable -= totalAvailableBalance / 50
                calculateNotes(totalAvailableBalance - ((totalAvailableBalance / 50).times(50)))

                mStringBuilder.append("\n50 Notes *" + totalAvailableBalance / 50)

            }
            in 100..499 -> {

                total100NoteAvailable -= totalAvailableBalance / 100
                calculateNotes(totalAvailableBalance - ((totalAvailableBalance / 100).times(100)))

                mStringBuilder.append("\n100 Notes *" + totalAvailableBalance / 100)
            }
            in 500..1999 -> {

                total500NoteAvailable -= totalAvailableBalance / 500
                calculateNotes(totalAvailableBalance - ((totalAvailableBalance / 500).times(500)))

                mStringBuilder.append("\n500 Notes *" + totalAvailableBalance / 500)

            }
            else -> {

                total2kNoteAvailable -= totalAvailableBalance / 2000
                calculateNotes(totalAvailableBalance - ((totalAvailableBalance / 2000).times(2000)))

                mStringBuilder.append("\n2000 Notes *" + totalAvailableBalance / 2000)


            }
        }

        activityMainBinding.apply {

            textWidthrwalNotes.text = mStringBuilder.toString()
            text2KNoteCount.text = getString(R.string.remain_2k_note_count, total2kNoteAvailable)
            text500NoteCount.text = getString(R.string.remain_500_note_count, total500NoteAvailable)
            text100NoteCount.text = getString(R.string.remain_100_note_count, total100NoteAvailable)
            text50NoteCount.text = getString(R.string.remain_50_note_count, total50NoteAvailable)
            text20NoteCount.text = getString(R.string.remain_20_note_count, total20NoteAvailable)
            text10NoteCount.text = getString(R.string.remain_10_note_count, total10NoteAvailable)
        }
    }
}
