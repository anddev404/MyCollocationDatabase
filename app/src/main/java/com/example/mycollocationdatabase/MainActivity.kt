package com.example.mycollocationdatabase

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room.databaseBuilder
import com.example.mydatabase.CollocationDatabase
import com.example.mydatabase.dao.CollocationDao
import com.example.mydatabase.dao.SentenceDao
import com.example.mydatabase.model.Collocation
import com.example.mydatabase.model.Sentence
import com.example.mydatabase.model.Translation
import com.example.mydatabase.model.UnifiedCollocations
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException


class MainActivity : AppCompatActivity() {
    lateinit var allCollocation: TextView
    lateinit var allUnifiedCollocation: TextView
    lateinit var sentRequest: TextView
    lateinit var sentRequestPercent: TextView
    lateinit var status200: TextView
    lateinit var statusNot200: TextView
    lateinit var convertGood: TextView
    lateinit var convertBad: TextView
    lateinit var parseGood: TextView
    lateinit var parseBad: TextView
    lateinit var timeTextView: TextView
    lateinit var status: TextView
    lateinit var alreadyTranslated: TextView

    var sendrequestInt = 0
    var statys200Int = 0
    var statusNot200Int = 0
    var convergoodInt = 0
    var converbadint = 0
    var parseGoodInt = 0
    var parseBadInt = 0
    lateinit var stop: Button
    lateinit var start: Button
    var translated = 0;
    var nottranslated = 0
    lateinit var dwatrzy: Button
    lateinit var czterydziesci: Button
    lateinit var trzytrzy: Button
    lateinit var szescszesc: Button
    lateinit var sto: Button
    lateinit var tysiuac: Button
    lateinit var piectysiecy: Button
    lateinit var pietnascitys: Button
    lateinit var przecinek: Button
    lateinit var znak1: Button
    lateinit var znak100: Button
    lateinit var znak500: Button
    lateinit var getAll: Button
    lateinit var getRest: Button
    lateinit var next1: Button
    lateinit var next10: Button
    lateinit var kropka: Button
    lateinit var znak1200: Button
    lateinit var updatedGood: TextView
    lateinit var updatedBad: TextView
    var stoped = false
    var time = 25L
    var odstotysiecy = 0
    var dostotysiecy = 2
    lateinit var sentenceDao: SentenceDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        allCollocation = findViewById(R.id.allCollocation) as TextView

        allUnifiedCollocation = findViewById(R.id.allUnifiedCollocations) as TextView
        sentRequest = findViewById(R.id.sentRequest) as TextView
        sentRequestPercent = findViewById(R.id.sentRequestPercent) as TextView
        status200 = findViewById(R.id.status200) as TextView
        statusNot200 = findViewById(R.id.statusNo200) as TextView
        convertGood = findViewById(R.id.converGood) as TextView
        convertBad = findViewById(R.id.convertBad) as TextView
        parseGood = findViewById(R.id.parseGood) as TextView
        parseBad = findViewById(R.id.parseBad) as TextView
        timeTextView = findViewById(R.id.time) as TextView
        timeTextView.text = "" + time
        status = findViewById(R.id.status) as TextView
        alreadyTranslated = findViewById(R.id.alreadytranslated) as TextView

        status.text = "nothing"
        stop = findViewById(R.id.buttonStop) as Button
        start = findViewById(R.id.buttonStart) as Button

        dwatrzy = findViewById(R.id.czterdziesci_piec) as Button
        czterydziesci = findViewById(R.id.czterdziesci) as Button
        trzytrzy = findViewById(R.id.trzydziesci) as Button
        szescszesc = findViewById(R.id.szescdziesiat) as Button
        sto = findViewById(R.id.sto) as Button
        tysiuac = findViewById(R.id.tysiac) as Button
        piectysiecy = findViewById(R.id.piectysiecy) as Button
        pietnascitys = findViewById(R.id.pietnascietys) as Button
        przecinek = findViewById(R.id.przecinek) as Button
        znak1 = findViewById(R.id.znakow1) as Button
        znak100 = findViewById(R.id.znakow100) as Button
        znak500 = findViewById(R.id.znakow500) as Button
        getAll = findViewById(R.id.getAll) as Button
        getRest = findViewById(R.id.getRest) as Button
        next1 = findViewById(R.id.next) as Button
        next10 = findViewById(R.id.next10) as Button
        kropka = findViewById(R.id.kropka) as Button
        updatedGood = findViewById(R.id.updatedGood) as TextView
        updatedBad = findViewById(R.id.updatedMax) as TextView
        znak1200 = findViewById(R.id.znakow1200) as Button

        getRest.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {


            }

        })
        next1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                odstotysiecy++
                dostotysiecy++
                status.text =
                    UnifiedCollocations.znak + " ||| " + time + " ||| " + UnifiedCollocations.liczbaZnakow + " znakow " + " ||| " + (odstotysiecy * 100000) + " - " + (dostotysiecy * 100000)
            }

        })
        next10.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                odstotysiecy = odstotysiecy + 10
                dostotysiecy = dostotysiecy + 10
                status.text =
                    UnifiedCollocations.znak + " ||| " + time + " ||| " + UnifiedCollocations.liczbaZnakow + " znakow " + " ||| " + (odstotysiecy * 100000) + " - " + (dostotysiecy * 100000)
            }

        })
        przecinek.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                UnifiedCollocations.znak = ","
                status.text =
                    UnifiedCollocations.znak + " ||| " + time + " ||| " + UnifiedCollocations.liczbaZnakow + " znakow " + " ||| " + (odstotysiecy * 100000) + " - " + (dostotysiecy * 100000)
            }

        })
        kropka.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                UnifiedCollocations.znak = "."
                status.text =
                    UnifiedCollocations.znak + " ||| " + time + " ||| " + UnifiedCollocations.liczbaZnakow + " znakow " + " ||| " + (odstotysiecy * 100000) + " - " + (dostotysiecy * 100000)
            }

        })

        start.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                status.text =
                    UnifiedCollocations.znak + " ||| " + time + " ||| " + UnifiedCollocations.liczbaZnakow + " znakow " + " ||| " + (odstotysiecy * 100000) + " - " + (dostotysiecy * 100000) + " started"
                startttt()
            }

        })
        stop.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                stoped = true
                status.text =
                    UnifiedCollocations.znak + " ||| " + time + " ||| " + UnifiedCollocations.liczbaZnakow + " znakow " + " ||| " + (odstotysiecy * 100000) + " - " + (dostotysiecy * 100000) + " stoped"
            }

        })
        dwatrzy.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                time = 23L
                timeTextView.text = "" + 23
            }

        })
        czterydziesci.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                time = 25L
                timeTextView.text = "" + 25
            }

        })
        trzytrzy.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                time = 33L
                timeTextView.text = "" + 33
            }

        })
        szescszesc.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                time = 66L
                timeTextView.text = "" + 66
            }

        })

        var context = this

        val db = initializeDatabase()
//        val sentenceDao = db.sentenceDao()
        sentenceDao = db.sentenceDao()
        status.text =
            UnifiedCollocations.znak + " ||| " + time + " ||| " + UnifiedCollocations.liczbaZnakow + " znakow " + " ||| " + (odstotysiecy * 100000) + " - " + (dostotysiecy * 100000)

    }

    var updatedGoodInt = 0
    var updatedMaxInt = 0
    var updatedBadInt = 0


    fun initializeDatabase(): CollocationDatabase {
        return databaseBuilder(
            applicationContext,
            CollocationDatabase::class.java, "collocation_database"
        ).build()
    }

    fun pobierz(collocations: Sentence, c: SentenceDao) {
        val clients = OkHttpClient()
        //TODO sprawdzic co sie stanie jak wyłacze internet
        val request: Request = Request.Builder()
            .url(
                "https://translo.p.rapidapi.com/translate?to=pl&text=" + collocations.sentencesAllInOne + "&from=en&translations=false"

            )
            .get()
            .addHeader(
                "x-rapidapi-key",
                "a8c8f09426mshbe1aa1787990dd7p12d76ejsn484423dcef66"
            ).addHeader(
                "x-rapidapi-host",
                "translo.p.rapidapi.com"


            )
            .build()
        Handler(Looper.getMainLooper()).postDelayed(
            {
//
                sendrequestInt++
                sentRequest.text = "" + sendrequestInt

            },
            0
        )
        clients.newCall(request).enqueue(object : Callback {


            override fun onFailure(call: okhttp3.Call, e: IOException) {
                Log.d(
                    "MARCIN_DATABASE",
                    "FAILURE" + Thread.currentThread().name
                )
                Handler(Looper.getMainLooper()).postDelayed(
                    {
//
                        statusNot200Int++
                        statusNot200.text = "" + statusNot200Int
                    },
                    0
                )
            }

            override fun onResponse(call: okhttp3.Call, response: Response) {

                Handler(Looper.getMainLooper()).postDelayed(
                    {
                        statys200Int++
                        status200.text = "" + statys200Int
                    },
                    0
                )
//                Log.d(
//                    "MARCIN_DATABASE",
//                    "RESPONSE OK " + limit + "  \n" + response.body!!.string() + " ====== " + Thread.currentThread().name
//                )

                try {
                    val obiektJava =
                        Gson().fromJson(response.body!!.string(), Translation::class.java)
                    if (obiektJava.ok == true) {
                        if (obiektJava.characters > 0) {
                            Handler(Looper.getMainLooper()).postDelayed(
                                {
//
                                    parseGoodInt++
                                    parseGood.text = "" + parseGoodInt
                                },
                                0
                            )

                            collocations.translationAllInOne = obiektJava.translated_text
                            c.update(collocations)
                            updatedGoodInt++



                            Handler(Looper.getMainLooper()).postDelayed(
                                {
                                    convertGood.text = "" + convergoodInt
                                    convertBad.text = "" + converbadint
                                    updatedGood.text =
                                        "" + updatedGoodInt + "  /   " + updatedMaxInt
                                    updatedBad.text = "" + updatedBadInt


                                },
                                0
                            )

                        } else {
                            Handler(Looper.getMainLooper()).postDelayed(
                                {
//
                                    parseBadInt++
                                    parseBad.text = "" + parseBadInt
                                },
                                0
                            )
                        }

                    }
                } catch (e: Exception) {

                }

            }

        })
    }

    // // po sciagnieciu dekoduje tluamczenie sprawdzajac wczesniej czy mozna tzn czy liczba rozdizelonych przez kropke == liczbie kolkacji
    fun startttt() {
        Log.d("MARCIN_DATABASE", "start sending request");



        GlobalScope.launch {

            for (i in odstotysiecy..4500000) {
                if (stoped) {
//                    Log.d("MARCIN_DATABASE", "stop");
                    break
                }
                var sentence = sentenceDao.getById(i)
                if (sentence != null) {
                    if (sentence.translationAllInOne.length > 0) {
                        translated++

                    } else {
                        nottranslated++
                        pobierz(sentence, sentenceDao)
                        delay(time)
                    }

                    Handler(Looper.getMainLooper()).postDelayed(
                        {
//
                            alreadyTranslated.text =
                                "tr= " + translated + ", not tr= " + nottranslated + ",   sum:" + (translated + nottranslated)
                        },
                        0
                    )
                }
            }


//
        }

    }
}
