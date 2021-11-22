package com.example.mydatabase

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Collocation")
class Collocation(var wordId: Int, var relation: Int, var collocation: String) {

    var translation: String? = null
    var isChecked: Boolean = false
    var numberOfDownloads = 0

    @Ignore
    var sentences = ArrayList<Sentence>()

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}