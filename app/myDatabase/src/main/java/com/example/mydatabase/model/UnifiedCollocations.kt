package com.example.mydatabase.model

class UnifiedCollocations {
    var lista = arrayListOf<Collocation>()
    var string = ""

    companion object {
        var znak = "."
        var liczbaZnakow = 650
    }

    //dodanie zmiany znaku na przecinek ewentualnie innny, zxmiana liczby znakow na mniejsza np 10 z 1200
    //example string:  pierwsza kolkacja. druga kolkacja. trzecia kolokacja
    fun addToString(c: Collocation) {
//usuniecie kropek i białych znaków
        string = string + znak + " " + c.collocation.replace(znak, " ").trim()
    }

    fun addToStringAsFirst(c: Collocation) {
        //  usuniecie kropek i białych znaków
        string = c.collocation.replace(".", " ").trim()

    }

    fun checkAdding(c: Collocation): Boolean {

        if (c.collocation.length + string.length < liczbaZnakow) {
            return true
        }
        return false
    }

    fun decodeString(translated: String): Boolean {
        var decode = translated.split(znak)
        if (decode.size == lista.size) {
            for (i in decode.indices) {
                lista.get(i).translatedCollocationTranslo = decode.get(i).trim()
            }

            return true
        }
        return false
    }

}