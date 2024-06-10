package com.example.quoteapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel

    private val quotetext:TextView
        get() = findViewById(R.id.quoteText)

    private val quoteAuth:TextView
        get() = findViewById(R.id.quoteAuthor)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel=ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuote(mainViewModel.getQuote())

    }

    fun setQuote(quote: Quote){
        quotetext.text=quote.text
        quoteAuth.text=quote.authName
    }

    fun onPrevious(view: View) {
        setQuote(mainViewModel.PrevQuote())
    }
    fun onNext(view: View) {
        setQuote(mainViewModel.NextQuote())
    }
    fun onShare(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
        startActivity(intent)
    }


}