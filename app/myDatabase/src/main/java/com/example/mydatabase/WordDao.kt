package com.example.mydatabase

import androidx.room.*

@Dao
interface WordDao {

    @Query("SELECT * FROM Word")
    fun getAll(): List<Word>

    @Query("SELECT * FROM Word WHERE id = :userIds")
    fun getById(userIds: Int): Word


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word): Long


    @Update
    fun update(word: Word): Int


    @Delete
    fun delete(word: Word): Int

    @Query("DELETE FROM Word WHERE id = :userIds")
    fun deleteById(userIds: Int): Int


    @Query("SELECT COUNT(*) FROM Word")
    fun getNumberOfWords(): Int

}