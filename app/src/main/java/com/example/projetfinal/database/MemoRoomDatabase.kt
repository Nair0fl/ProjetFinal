package com.example.projetfinal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.projetfinal.models.Memo
import com.example.projetfinal.models.MemoDAO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Memo::class), version = 1, exportSchema = false)
abstract class MemoRoomDatabase : RoomDatabase() {

    abstract fun memoDao(): MemoDAO

    private class MemoDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var memoDAO = database.memoDao()

                }
            }
        }
    }

    companion object {

        @Volatile
        private var INSTANCE: MemoRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): MemoRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MemoRoomDatabase::class.java,
                    "memo_database"
                )
                    .addCallback(MemoDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}