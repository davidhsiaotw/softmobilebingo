package com.example.bingo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class BingoViewModel : ViewModel() {
    private val answer = listOf(
        listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9), listOf(1, 4, 7),
        listOf(2, 5, 8), listOf(3, 6, 9), listOf(1, 5, 9), listOf(3, 5, 7)
    )

    private val _bingo = MutableLiveData<ArrayList<Bingo>>()
    val bingo: LiveData<ArrayList<Bingo>> = _bingo

    init {
        startBingo()
    }

    fun startBingo() {
        // randomize nine numbers with 1~25
        _bingo.value = ArrayList()
        val numbers = (1..9).map { Random.nextInt(1, 26).toString() }
        for (i in numbers.indices) {
            _bingo.value?.add(Bingo(i, numbers[i]))
        }
    }

    fun checkBingo(): Boolean {
        var lines = 0

        // use answer to brute check
        answer.forEach {
            var isLine = true
            it.forEach { index ->
                isLine = isLine && _bingo.value?.get(index-1)?.isSelected == true
            }
            if (isLine) lines++
            if (lines == 2) return true
        }

        return lines == 2
    }
}