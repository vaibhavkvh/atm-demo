package com.example.mybankapplication

import android.view.View
import android.widget.TextView


 fun View.viewVisible() {
    visibility = View.VISIBLE
}

 fun View.viewGone() {
    visibility = View.GONE
}

 fun TextView.readText(): String {
    return this.text.toString()
}
