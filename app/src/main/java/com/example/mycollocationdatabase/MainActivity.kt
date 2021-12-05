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
import com.example.mydatabase.model.Collocation
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
    var listUnified = arrayListOf<UnifiedCollocations>()
    lateinit var collocationDao: CollocationDao
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
        getAll.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                getAll()
            }

        })
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
        znak1.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                UnifiedCollocations.liczbaZnakow = 1
                obliczUnified(collocation)
                status.text =
                    UnifiedCollocations.znak + " ||| " + time + " ||| " + UnifiedCollocations.liczbaZnakow + " znakow " + " ||| " + (odstotysiecy * 100000) + " - " + (dostotysiecy * 100000)
            }

        })
        znak100.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                UnifiedCollocations.liczbaZnakow = 100
                obliczUnified(collocation)
                status.text =
                    UnifiedCollocations.znak + " ||| " + time + " ||| " + UnifiedCollocations.liczbaZnakow + " znakow " + " ||| " + (odstotysiecy * 100000) + " - " + (dostotysiecy * 100000)
            }

        })
        znak500.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                UnifiedCollocations.liczbaZnakow = 650
                obliczUnified(collocation)
                status.text =
                    UnifiedCollocations.znak + " ||| " + time + " ||| " + UnifiedCollocations.liczbaZnakow + " znakow " + " ||| " + (odstotysiecy * 100000) + " - " + (dostotysiecy * 100000)
            }

        })
        znak1200.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                UnifiedCollocations.liczbaZnakow = 1200
                obliczUnified(collocation)
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
        sto.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                time = 100L
                timeTextView.text = "" + 100
            }

        })
        tysiuac.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                time = 1000L
                timeTextView.text = "" + 1000
            }

        })
        piectysiecy.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                time = 5000L
                timeTextView.text = "" + 5000
            }

        })
        pietnascitys.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                time = 15000L
                timeTextView.text = "" + 15000
            }

        })
        var context = this

        val db = initializeDatabase()
//        val sentenceDao = db.sentenceDao()
        collocationDao = db.collocationDao()
        status.text =
            UnifiedCollocations.znak + " ||| " + time + " ||| " + UnifiedCollocations.liczbaZnakow + " znakow " + " ||| " + (odstotysiecy * 100000) + " - " + (dostotysiecy * 100000)

    }

    var unifiedCol = UnifiedCollocations()
    var collocation = listOf<Collocation>()
    fun getAll() {
        listUnified = arrayListOf<UnifiedCollocations>()

        GlobalScope.launch {

//1 tworze nowy typ ktory porzechiowuje liste kolokacji================
            //2 pobieram wszytkie kolokacjie===========================
            //3 sprawdzam kazda kolokacje pod wzgledem dlugosic:================
            //4 tworze pusty stringSumujacy===================
            //5 jesli string sumujacy + kolokacja < 1200 to:===================
            //6 dodaje kolokacje do listy  i dodjae stringi i przechodze do nastepnej kolokacji===============
            //7 jesli nie to zapisuje obiekt(z lista kolkacji) do listy i tworze nowy obiekt z pustym stringiem================
            // przed zapisem nalezy usunac kropki i białe znaki na koncu i podczatku oraz styl zapisu jest taki kolokacja. drugfa kolkacja. trzecia kolkacja================
            //jesli mam cala liste obiektow z listami to sciagam//======================
            //przy dodaniu pierwszegho nie sprawdzac czy jest wiekszy niz liczba znakow bo mozna by sie zapetlic==============
            // po sciagnieciu dekoduje tluamczenie sprawdzajac wczesniej czy mozna tzn czy liczba rozdizelonych przez kropke == liczbie kolkacji
//dodaje to textviewu liczbe kolkacji, liczbe obiektow z listami, procentowa liczbe przetworzonych, \////=================
// liczbe zdekodowanych dobrze i żle,  liczbe utworzonych watkow oraz liczbe dobrze doewbranych oraz zle odebranych z kodem nie 200================
            //regulacja czasu pobierania tzn przyciski oreaz zmienna w petli czas np 1000/45 =23, 1000/40=25 , itp
            //  text view informujacy o zakonczeniu pobierania tzn jak wyjdzie z petli
//dodanie kolokcaji do telefonuuu bo nie mam całej bazy w telefonie
            //uruchomienie
//przycisk stopu oraz pauzy

/////////
            Handler(Looper.getMainLooper()).postDelayed(
                {
//
                    allCollocation.text =
                        "pobieram " + (odstotysiecy * 100000) + " - " + (dostotysiecy * 100000)

                },
                0
            )
            collocation =collocationDao.getAllNotTranslated()
//            collocation = collocationDao.getAll(odstotysiecy * 100000, dostotysiecy * 100000)
            Handler(Looper.getMainLooper()).postDelayed(
                {
//
                    allCollocation.text = "" + collocation.size
                    sentRequest.text = "" + 0
                    sentRequestPercent.text = "" + 0
                    sendrequestInt = 0

                    status200.text = "0"
                    statusNot200.text = "0"

                    parseGood.text = "0"
                    parseBad.text = "0"

                    convertGood.text = "0"
                    convertBad.text = "0"

                },
                0
            )

            //Log.d("MARCIN_DATABASE", "jest ${collocation.size} w bazie");


            obliczUnified(collocation)
            var debug = ""


            // tu dodac  bo ostatni nie bedzie zapisany do listy wiec trzeba go zapisac pod warunkiem ze lista kolokacji w srodku jest wieksza niz 0===========

//            Handler(Looper.getMainLooper()).postDelayed(
//                {
////                    Toast.makeText(
////                        context,
////                        "pobrano: " + list.size + " words" + "\npobrano: " + listCollocation.size + " collocations",
////                        Toast.LENGTH_LONG
////                    )
////                        .show();
//                },
//                0
//            )
//

        }
    }

    var updatedGoodInt = 0
    var updatedMaxInt = 0

    var updatedBadInt = 0

    fun obliczUnified(collocation: List<Collocation>) {
        listUnified = arrayListOf()
        translated = 0
        nottranslated = 0
        updatedGoodInt = 0
        updatedMaxInt = 0
        translated = 0
        nottranslated = 0
        convergoodInt = 0
        updatedGoodInt = 0
        updatedBadInt = 0
        converbadint = 0
        sendrequestInt = 0
        parseBadInt = 0
        parseGoodInt = 0
        statys200Int = 0
        statusNot200Int = 0
        updatedBadInt = 0
        for (c in collocation) {
            if (c.translatedCollocationTranslo.length == 0) {
                nottranslated++
                if (unifiedCol.string.length == 0) {
                    unifiedCol.lista.add(c)
                    unifiedCol.addToStringAsFirst(c)
                    //  Log.d("MARCIN_DATABASE", "d==" + c.collocation);


                } else {
                    var check = unifiedCol.checkAdding(c)
                    if (check) {
                        unifiedCol.lista.add(c)
                        //    Log.d("MARCIN_DATABASE", "d==" + c.collocation);
                        unifiedCol.addToString(c)
                    } else {
                        listUnified.add(unifiedCol)
                        unifiedCol = UnifiedCollocations()
                        unifiedCol.lista.add(c)
                        //     Log.d("MARCIN_DATABASE", "d==" + c.collocation);
                        unifiedCol.addToStringAsFirst(c)
                    }
                }
            } else {
                translated++
            }
        }
        if (unifiedCol.lista.size > 0) {
            listUnified.add(unifiedCol)
        }
        //
        Handler(Looper.getMainLooper()).postDelayed(
            {
//
                allUnifiedCollocation.text = "" + listUnified.size
                alreadyTranslated.text =
                    "tr= " + translated + ", not tr= " + nottranslated + ",   sum:" + (translated + nottranslated)
            },
            0
        )
    }

    fun initializeDatabase(): CollocationDatabase {
        return databaseBuilder(
            applicationContext,
            CollocationDatabase::class.java, "collocation_database"
        ).build()
    }

    fun pobierz(collocations: UnifiedCollocations, c: CollocationDao) {
        val clients = OkHttpClient()
        //TODO sprawdzic co sie stanie jak wyłacze internet
        val request: Request = Request.Builder()
            .url(
                "https://translo.p.rapidapi.com/translate?to=pl&text=" + collocations.string + "&from=en&translations=false"

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
                sentRequestPercent.text =
                    "" + ((sendrequestInt.toFloat() / listUnified.size) * 100) + " %"
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


                            if (collocations.decodeString(obiektJava.translated_text)) {
                                convergoodInt++
                                updatedMaxInt = updatedMaxInt + (collocations.lista.size)
                                for (cc in collocations.lista) {
                                    c.update(cc)
                                    updatedGoodInt++
                                }
                            } else {
                                converbadint++

                            }
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
            for (c in listUnified) {
                if (stoped) {
                    Log.d("MARCIN_DATABASE", "stop");
                    break
                }
                Log.d("MARCIN_DATABASE", "download");

                pobierz(c, collocationDao)
                delay(time)


            }
            Log.d("MARCIN_DATABASE", "end sending request");

            Handler(Looper.getMainLooper()).postDelayed(
                {
//
                    status.text =
                        UnifiedCollocations.znak + " ||| " + time + " ||| " + UnifiedCollocations.liczbaZnakow + " znakow " + " ||| " + (odstotysiecy * 100000) + " - " + (dostotysiecy * 100000) + " ENDEDDDDDDDDDDDDDDD"
                },
                0
            )
        }

    }
}
//try {
//    collocations =
//        Gson().fromJson(
//            getStringDog(),
//            object : TypeToken<List<Collocation?>?>() {}.type
//        )
//
//    collocations.get(1).isChecked = true
//    collocations.get(3).isChecked = true
//    collocations.get(5).isChecked = true
//    collocations.get(10).isChecked = true
//
//    setExampleTranslations(collocations)
//    for (c in collocations) {
//        if (c.relation.equals(relation)) collocations2.add(c)
//    }
//
//} catch (e: Exception) {
//    return ArrayList<Collocation>()
//}