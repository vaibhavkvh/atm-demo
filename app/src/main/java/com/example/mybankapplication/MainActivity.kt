package com.example.mybankapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.mybankapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

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

    }
}