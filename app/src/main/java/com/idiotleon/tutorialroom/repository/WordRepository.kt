package com.idiotleon.tutorialroom.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.idiotleon.tutorialroom.dao.WordDAO
import com.idiotleon.tutorialroom.db.WordRoomDatabase
import com.idiotleon.tutorialroom.model.Word

class WordRepository constructor(
    private val wordDAO: WordDAO,
    private val allWords: LiveData<List<Word>>
) {
    constructor(application: Application) : this(
        WordRoomDatabase.getDatabase(application).wordDAO(),
        WordRoomDatabase.getDatabase(application).wordDAO().getAlphabetizedWords()
    )

    fun getAllWords(): LiveData<List<Word>> = allWords

    fun insert(word: Word) = WordRoomDatabase.databseWriteExecutor.execute { wordDAO.insert(word) }
}