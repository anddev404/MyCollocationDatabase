package com.example.mydatabase.dao

import androidx.room.*
import com.example.mydatabase.model.Word

@Dao
interface WordDao {

    @Query("SELECT * FROM Word.words")
    fun getAll(): List<Word>

    @Query("SELECT * FROM Word.words WHERE id = :userIds")
    fun getById(userIds: Int): Word?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: Word): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(words: List<Word>): Int

    @Update
    fun update(word: Word): Int

    @Delete
    fun delete(word: Word): Int

    @Query("DELETE FROM Word.words WHERE id = :userIds")
    fun deleteById(userIds: Int): Int


    @Query("SELECT COUNT(*) FROM Word.words")
    fun getNumberOfWords(): Int

}