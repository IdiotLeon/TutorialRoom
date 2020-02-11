package com.idiotleon.tutorialroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.idiotleon.tutorialroom.model.Word
import com.idiotleon.tutorialroom.repository.WordRepository

class WordViewModel constructor(
    application: Application,
    private val repository: WordRepository,
    private val allWords: LiveData<List<Word>>
) : AndroidViewModel(application) {

    constructor(application: Application) : this(
        application,
        WordRepository(application),
        WordRepository(application).getAllWords()
    )

    fun getAllWords(): LiveData<List<Word>> = allWords

    fun insert(word: Word) = repository.insert(word)
}