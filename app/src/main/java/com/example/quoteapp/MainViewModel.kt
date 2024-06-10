package com.example.quoteapp

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(val context:Context) :ViewModel() {

    private var QuoteList:Array<Quote> =emptyArray()
    private var index=0

    init {
        QuoteList=loadQuotesFromAssets()
    }

    private fun loadQuotesFromAssets(): Array<Quote> {
        //reading the file and storing in an array
        val inputStrem=context.assets.open("quotes.json")
        //getting the siz of the inputstream
        val size:Int=inputStrem.available()
        //defined an ByteArray to store the data from the file read
        val buffer=ByteArray(size)
        //read() method reads the file and adds the contentsto the mentioned array
        inputStrem.read(buffer)
        inputStrem.close()
        //now we have to convert the byte array to string
        val json=String(buffer, Charsets.UTF_8)
        val gson=Gson()
        return gson.fromJson(json,Array<Quote>::class.java)

    }
    fun getQuote()=QuoteList[index]

    fun NextQuote()=QuoteList[++index]
    fun PrevQuote()=QuoteList[--index]


}