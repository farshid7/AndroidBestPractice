package com.example.androidbestpractice.di.databaseManager

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidbestpractice.entity.DataResult


@Database(
    entities = [DataResult::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase(){
    abstract fun DataResultDao(): DataResultDao

}