package com.example.projetfinal.helpers

import android.content.Context
import androidx.room.Room
import com.example.projetfinal.database.MemoRoomDatabase

class MemoRoomDatabaseHelpers(context: Context?) {
    companion object {
        private var dbHelper: MemoRoomDatabaseHelpers? = null
        private lateinit var db: MemoRoomDatabase

        @Synchronized
        fun getDatabase(c: Context): MemoRoomDatabase {
            if (dbHelper == null)
                dbHelper =
                    MemoRoomDatabaseHelpers(
                        c.applicationContext
                    )

            return db
        }
    }

    init {
        db = context?.let {
            Room
                .databaseBuilder(it, MemoRoomDatabase::class.java, "memos.db")
                .allowMainThreadQueries()
                .build()
        }!!
    }
}