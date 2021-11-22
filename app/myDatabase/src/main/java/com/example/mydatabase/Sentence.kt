package com.example.mydatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sentence")
class Sentence(var collocationId: Int, var sentence: String) {

    var translation: String? = null
    var isChecked: Boolean = false
    var numberOfDownloads = 0

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}