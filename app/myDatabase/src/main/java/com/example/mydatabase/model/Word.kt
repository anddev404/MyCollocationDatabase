package com.example.mydatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "words")
class Word(var word: String, var pronunciation: String?, var partOfSpeech: Int) {

    var spareString = ""//możźe kiedyś sie przyda żeby nie aktualizować bazy
    var spareInt = 0//możźe kiedyś sie przyda żeby nie aktualizować bazy

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    companion object {
        val UNKNOWN = 0
        val NOUN = 1
        val ADJECTIVE = 2
        val VERB = 3
    }
}