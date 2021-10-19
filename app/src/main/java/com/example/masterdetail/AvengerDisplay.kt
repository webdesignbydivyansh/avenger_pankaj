package com.example.masterdetail

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class AvengerDisplay : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_avenger_display)

        val imgAvg=findViewById<ImageView>(R.id.imgAvenger)
        val toolbar=findViewById<Toolbar>(R.id.toolBar)
        val toolbarTitle=findViewById<TextView>(R.id.toolbar_title)
        val ratingbar=findViewById<RatingBar>(R.id.ratingBar)
        val i=intent.getParcelableExtra<Bitmap>("image")
        val j=intent.getStringExtra("title")
        imgAvg.setImageBitmap(i)


        ratingbar.onRatingBarChangeListener = RatingBar.OnRatingBarChangeListener { _, p1, _ ->
            //                if(p1!=0f)
//                {
            val rate=p1
            val i=Intent(this@AvengerDisplay,MainActivity::class.java)
            i.putExtra("rate",rate)
            i.putExtra("names",j)
//            Log.e("TAS","$j")
            startActivity(i)
//                }
        }
        setSupportActionBar(toolbar)
        toolbarTitle.text=j
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}