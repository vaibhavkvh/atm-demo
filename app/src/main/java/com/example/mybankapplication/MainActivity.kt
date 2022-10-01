package com.example.mybankapplication

import android.os.Bundle
import android.view.View
import android.widget.TextView
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
    private lateinit var activityMainBinding: ActivityMainBinding

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
                getString(R.string.total_balance_is,totalAvailableBalance)
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
            }
        }

    }
}

private fun View.viewGone() {
    visibility = View.GONE
}

private fun TextView.readText(): String {
    return this.text.toString()
}
