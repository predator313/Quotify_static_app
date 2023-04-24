package com.aamirashraf.myapplication

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson


class MyViewModel(private val context: Context):ViewModel() {
    private var quoteList:Array<Quotes> = emptyArray()
    private var index=0
    init {
        quoteList=loadQuotesFromAssert()
    }

    private fun loadQuotesFromAssert(): Array<Quotes> {
        val inputStream= context.assets.open("quotes.json")
        val size:Int=inputStream.available()
        val buffer=ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json=String(buffer,Charsets.UTF_8)
        val gson=Gson()
        return gson.fromJson(json,Array<Quotes>::class.java)
    }
    fun getQuotes()=quoteList[index]
    fun nextQuotes()=quoteList[++index]
    fun previousQuotes()=quoteList[--index]
}