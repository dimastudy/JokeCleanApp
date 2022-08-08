package com.justadroiddev.wordscleanarchapp.presentation.customview

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

class UnscrollableLinearLayoutManager(
    private val context: Context
) : LinearLayoutManager(context) {

    override fun canScrollVertically(): Boolean {
        return false
    }
}