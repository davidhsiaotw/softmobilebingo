package com.example.bingo

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var bingoViewModel: BingoViewModel
    private lateinit var bingoRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton = findViewById<Button>(R.id.start_button)
        startButton.setOnClickListener {
            bingoViewModel.startBingo()
        }

        val disableScrollGridLayoutManager = object : GridLayoutManager(this, 3) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }

        bingoRecyclerView.layoutManager = disableScrollGridLayoutManager

        // create bingo adapter
        bingoRecyclerView.adapter = BingoAdapter(GridListener {
            if (bingoViewModel.checkBingo()) {
                // show bingo dialog
                val dialog = MaterialAlertDialogBuilder(this)
            }
        })
        (bingoRecyclerView.adapter as BingoAdapter).submitList(bingoViewModel.bingo.value)

    }
}