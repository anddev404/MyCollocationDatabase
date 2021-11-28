package com.example.mydatabase.dao

import androidx.room.*
import com.example.mydatabase.model.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM words")
    fun getAll(): List<Word>

    @Query("SELECT * FROM words WHERE id = :userIds")
    fun getById(userIds: Int): Word?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word): Long

    //TODO to nie działa może trzeba vararg  ...
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(words: List<Word>): Int

    @Update
    fun update(word: Word): Int

    @Delete
    fun delete(word: Word): Int

    @Query("DELETE FROM words WHERE id = :userIds")
    fun deleteById(userIds: Int): Int


    @Query("SELECT COUNT(*) FROM words")
    fun getNumberOfWords(): Int

}