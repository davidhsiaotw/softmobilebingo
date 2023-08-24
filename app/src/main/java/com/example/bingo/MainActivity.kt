package com.example.bingo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bingoViewModel = BingoViewModel()
        val bingoRecyclerView = findViewById<RecyclerView>(R.id.grids_recylerview)

//        Log.d("MainActivity", "${bingoViewModel.bingo.value?.size}")
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
            it.isSelected = !it.isSelected
            if (bingoViewModel.checkBingo()) {
                Toast.makeText(this, "BINGO!!", Toast.LENGTH_SHORT).show()
                // show bingo dialog
                val dialog = MaterialAlertDialogBuilder(this)
            }
        })
        (bingoRecyclerView.adapter as BingoAdapter).submitList(bingoViewModel.bingo.value)

    }
}