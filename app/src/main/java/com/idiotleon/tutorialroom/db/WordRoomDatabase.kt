package com.idiotleon.tutorialroom.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.idiotleon.tutorialroom.dao.WordDAO
import com.idiotleon.tutorialroom.model.Word
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordRoomDatabase : RoomDatabase() {
    abstract fun wordDAO(): WordDAO

    companion object {
        @Volatile
        private var INSTANCE: WordRoomDatabase? = null
        private const val NUMBER_OF_THREADS: Int = 4
        val databseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): WordRoomDatabase {
            INSTANCE?.let {
                synchronized(it::class.java) {
                    INSTANCE?.let {
                        INSTANCE =
                            Room.databaseBuilder(
                                context.applicationContext,
                                it::class.java,
                                "word_database"
                            )
                                .addCallback(roomDatabaseCallback)
                                .build()
                    }
                }
            }

            return INSTANCE as WordRoomDatabase
        }

        private val roomDatabaseCallback: Callback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

                databseWriteExecutor.execute {
                    val dao: WordDAO? = INSTANCE?.wordDAO()
                    dao?.deleteAll()

                    var word: Word = Word("Hello")
                    dao?.insert(word)
                    word = Word("World")
                    dao?.insert(word)
                }
            }
        }
    }
}