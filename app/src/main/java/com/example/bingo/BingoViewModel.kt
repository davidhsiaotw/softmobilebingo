package com.example.bingo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class BingoViewModel : ViewModel() {
    private val _bingo = MutableLiveData<ArrayList<Bingo>>()
    val bingo: LiveData<ArrayList<Bingo>> = _bingo

    init {
        startBingo()
    }


    fun startBingo() {
        // randomize nine numbers with 1~25
        val numbers = (1..9).map { Random.nextInt(1, 26).toString() }
        for (i in numbers.indices) {
            _bingo.value?.add(Bingo(i, numbers[i], ))
        }

    }

    fun checkBingo(): Boolean {
        if (_bingo.value?.size!! < 3)
            return false


        TODO("unimplemented")
    }
}