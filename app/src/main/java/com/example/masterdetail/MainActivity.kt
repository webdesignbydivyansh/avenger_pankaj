package com.example.masterdetail

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {
    lateinit var layoutManager: RecyclerView.LayoutManager
    lateinit var recyclerAdapter: AvengerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerAvenger = findViewById<RecyclerView>(R.id.rvView)
        val toolbars=findViewById<Toolbar>(R.id.toolBars)
        val toolbarTitle=findViewById<TextView>(R.id.toolbar_title_main)
        val spref=getSharedPreferences("ABC", MODE_PRIVATE)
        val avengerlist = arrayListOf(
                AvengerData("Hulk", "Very Good", BitmapFactory.decodeResource(resources, R.drawable.hulk)),
                AvengerData("Super Man", "Normal", BitmapFactory.decodeResource(resources, R.drawable.super_man)),
                AvengerData("Spider Man", "Awesome", BitmapFactory.decodeResource(resources, R.drawable.m_spider_man))
        )
        setSupportActionBar(toolbars)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        toolbarTitle.text="Avengers"
        layoutManager = LinearLayoutManager(this)
        recyclerAdapter = AvengerAdapter(avengerlist)
        recyclerAvenger.adapter = recyclerAdapter
        recyclerAvenger.layoutManager = layoutManager

        recyclerAdapter.setOnItemSelectListener(object : AvengerAdapter.onItemSelectListener {
            override fun onItemSelect(pos: Int) {
                val img = avengerlist[pos].image
                val tit = avengerlist[pos].name
                val i = Intent(this@MainActivity, AvengerDisplay::class.java)
                i.putExtra("image", img)
                i.putExtra("title", tit)
                startActivity(i)
//
                }

//            }
        })
        val names = intent.getStringExtra("names")
        val rate = intent.getFloatExtra("rate", 0f)
//        if(names=="Hulk")
            avengerlist[0].rating=spref.getString("hulky","default")
//        else if(names=="Spider Man")
            avengerlist[2].rating=spref.getString("spidy","default")
//        else if(names=="Super Man")
            avengerlist[1].rating=spref.getString("supery","default")

        recyclerAdapter.notifyDataSetChanged()
        Log.d("TAG","$names+$rate")
        if(names!=null) {

            when (names) {
                "Hulk" -> {
//                if (rate != 0f) {

                    if (rate in 0.0..1.0) {

                        avengerlist[0].rating = "Normal"
                        val editor=spref.edit()
                        editor
                                .putString("hulky","Normal")
                                .apply()
                    } else if (rate > 1 && rate <= 2) {

                        avengerlist[0].rating = "Very Good"
                        val editor=spref.edit()
                        editor
                                .putString("hulky","Very Good")
                                .apply()
                    } else {

                        avengerlist[0].rating = "Awesome"
                        val editor=spref.edit()
                        editor
                                .putString("hulky","Awesome")
                                .apply()
                    }
//                }
                    recyclerAdapter.notifyDataSetChanged()

//                    val obj = AvengerData()
//                    val sPref = getSharedPreferences("spref", MODE_PRIVATE)
//                    val editor = sPref.edit()
//                    val gson = Gson()
//                    val json = gson.toJson(obj)
//                    editor.putString("abc", json)
//                    editor.apply()
                }
                "Spider Man" -> {
//                if (rate != 0f) {
                    if (rate in 0.0..1.0) {
                        avengerlist[2].rating = "Normal"
                        val editor = spref.edit()
                        editor
                                .putString("spidy", "Normal")
                                .apply()
                    }
                    else if (rate > 1 && rate <= 2) {
                        avengerlist[2].rating = "Very Good"
                        val editor = spref.edit()
                        editor
                                .putString("spidy", "Very Good")
                                .apply()
                    }
                    else {
                        avengerlist[2].rating = "Awesome"
                        val editor = spref.edit()
                        editor
                                .putString("spidy", "Awesome")
                                .apply()
                    }
//                }
                    recyclerAdapter.notifyDataSetChanged()
                }
                "Super Man" -> {
//                if (rate != 0f) {
                    if (rate in 0.0..1.0) {
                        avengerlist[1].rating = "Normal"
                        val editor = spref.edit()
                        editor
                                .putString("supery", "Normal")
                                .apply()
                    }
                    else if (rate > 1 && rate <= 2) {
                        avengerlist[1].rating = "Very Good"
                        val editor = spref.edit()
                        editor
                                .putString("supery", "Very Good")
                                .apply()
                    }
                    else {
                        avengerlist[1].rating = "Awesome"
                        val editor = spref.edit()
                        editor
                                .putString("supery", "Awesome")
                                .apply()
                    }

//                }
                    recyclerAdapter.notifyDataSetChanged()
                }
                null -> {
                    Toast.makeText(this@MainActivity, "Null!", Toast.LENGTH_SHORT).show()
                }
            }

//            val sPref = getSharedPreferences("spref", MODE_PRIVATE)
//            val gson = Gson()
//            val json = sPref.getString("abc", "")
//            val obj = gson.fromJson(json, AvengerData::class.java)

        }
    }
}