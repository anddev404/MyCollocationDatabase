package com.example.mycollocationdatabase

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room.databaseBuilder
import com.example.mydatabase.CollocationDatabase
import com.example.mydatabase.model.Collocation
import com.example.mydatabase.model.Sentence
import com.example.mydatabase.model.Word
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var context = this

        val db = initializeDatabase()
        val sentenceDao = db.sentenceDao()
        val collocationDao = db.collocationDao()

        GlobalScope.launch {

            var sentence = Sentence("zdanie 1, zdanie 2", "", false, 0)
            var collocation = Collocation()

            var id = sentenceDao.insert(sentence)
            collocation.idSentences = id.toInt()

            collocationDao.insert(collocation)

            Log.d("MARCIN_DATABASE", "" + id);


            Handler(Looper.getMainLooper()).postDelayed(
                {
//                    Toast.makeText(
//                        context,
//                        "pobrano: " + list.size + " words" + "\npobrano: " + listCollocation.size + " collocations",
//                        Toast.LENGTH_LONG
//                    )
//                        .show();
                },
                0
            )
        }

    }

    fun initializeDatabase(): CollocationDatabase {
        return databaseBuilder(
            applicationContext,
            CollocationDatabase::class.java, "collocation_database"
        ).build()
    }

}