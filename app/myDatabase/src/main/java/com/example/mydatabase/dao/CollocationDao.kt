package com.example.mydatabase.dao

import androidx.room.*
import com.example.mydatabase.model.Collocation

@Dao
interface CollocationDao {

    @Query("SELECT * FROM Collocation")
    fun getAll(): List<Collocation>

    @Query("SELECT * FROM Collocation WHERE id = :collocationId")
    fun getById(collocationId: Int): Collocation

    @Query("SELECT * FROM Collocation WHERE wordId = :wordId")
    fun getByWordId(wordId: Int): List<Collocation>

    @Query("SELECT * FROM Collocation WHERE  wordId = :wordId AND relation = :relation")
    fun getByWordIdAndRelation(wordId: Int, relation: String): List<Collocation>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(sentence: Collocation): Long


    @Update
    fun update(sentence: Collocation): Int


    @Delete
    fun delete(sentence: Collocation): Int

    @Query("DELETE FROM Collocation WHERE id = :collocationId")
    fun deleteById(collocationId: Int): Int

    @Query("SELECT COUNT(*) FROM Collocation")
    fun getNumberOfCollocations(): Int

}