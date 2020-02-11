package com.idiotleon.tutorialroom.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.idiotleon.tutorialroom.dao.WordDAO
import com.idiotleon.tutorialroom.db.WordRoomDatabase
import com.idiotleon.tutorialroom.model.Word

class WordRepository constructor(
    application: Application,
    private val wordDAO: WordDAO = WordRoomDatabase.getDatabase(application).wordDAO(),
    private val allWords: LiveData<List<Word>> = wordDAO.getAlphabetizedWords()
) {
    fun getAllWords(): LiveData<List<Word>> = allWords

    fun insert(word: Word) = WordRoomDatabase.databseWriteExecutor.execute { wordDAO.insert(word) }
}