package com.example.mydatabase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Word")
class Word(word: String, pronunciation: String?, partOfSpeech: Int) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object {
        val UNKNOWN = 0
        val NOUN = 1
        val ADJECTIVE = 2
        val VERB = 3
    }
}