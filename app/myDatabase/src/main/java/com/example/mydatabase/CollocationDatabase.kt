package com.example.mydatabase

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mydatabase.dao.CollocationDao
import com.example.mydatabase.dao.SentenceDao
import com.example.mydatabase.dao.WordDao
import com.example.mydatabase.model.Collocation
import com.example.mydatabase.model.Sentence
import com.example.mydatabase.model.Word

@Database(entities = [Word::class, Collocation::class, Sentence::class], version = 4)
abstract class CollocationDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
    abstract fun collocationDao(): CollocationDao
    abstract fun sentenceDao(): SentenceDao

    companion object {

        fun initializeDatabase(context: Context, databaseName: String): CollocationDatabase {
            return databaseBuilder(
                context,
                CollocationDatabase::class.java, databaseName
            ).addMigrations(MIGRATION_1_2).addMigrations(MIGRATION_2_3).addMigrations(MIGRATION_3_4)
                .build()
//            ).addMigrations(MIGRATION_1_2).build()

        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                Log.d("DATABASE_MARCIN", "migrate from 1 to 2 ... Start!");

                database.execSQL("CREATE TABLE words2 (word TEXT NOT NULL, translations TEXT NOT NULL, pronunciation TEXT NOT NULL, partOfSpeech INTEGER NOT NULL, greenCollocationsCount INTEGER NOT NULL, greenSentencesCount INTEGER NOT NULL, id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)")
                database.execSQL("INSERT INTO words2 SELECT word, translations, pronunciation,partOfSpeech,0,0,id FROM words")
                database.execSQL("DROP TABLE words")
                database.execSQL("ALTER TABLE words2 RENAME TO words")

                Log.d("DATABASE_MARCIN", "migrate from 1 to 2 ... Done!");

            }
        }

        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {

                Log.d("DATABASE_MARCIN", "migrate from 2 to 3 ... Start!");

                database.execSQL("ALTER TABLE words ADD favourite INTEGER")

                Log.d("DATABASE_MARCIN", "migrate from 2 to 3 ... Done!");

            }
        }
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                var from = 3
                var to = 4

                Log.d("DATABASE_MARCIN", "migrate from $from to $to ... Start!");

                database.execSQL("ALTER TABLE words ADD 'group' INTEGER")
                database.execSQL("ALTER TABLE words ADD time1 INTEGER")
                database.execSQL("ALTER TABLE words ADD time2 INTEGER")
                database.execSQL("ALTER TABLE words ADD time3 INTEGER")
                database.execSQL("ALTER TABLE words ADD time4 INTEGER")
                database.execSQL("ALTER TABLE words ADD time5 INTEGER")

                Log.d("DATABASE_MARCIN", "migrate from $from to $to ... Done!");

            }
        }
    }


}
