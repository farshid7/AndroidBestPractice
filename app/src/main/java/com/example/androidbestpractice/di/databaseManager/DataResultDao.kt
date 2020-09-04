package com.example.androidbestpractice.di.databaseManager

import androidx.room.*
import com.example.androidbestpractice.entity.DataResult

@Dao
interface DataResultDao {
    @Query("SELECT * FROM DataResult")
    suspend fun getAll(): List<DataResult>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(dataResult: DataResult)

    @Delete
    suspend fun remove(dataResult: DataResult)

    @Query("SELECT EXISTS(SELECT * FROM DataResult WHERE id==(:id))")
    suspend fun isExists(id: Int): Boolean
}