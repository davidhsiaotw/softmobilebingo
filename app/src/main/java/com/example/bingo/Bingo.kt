package com.example.bingo

import android.graphics.Color

class Bingo(
    val id: Int,
    var number: String = "",
    var isSelected: Boolean = false,
    var color: Int = Color.GRAY
)