package com.example.iteradmin.weatherapp

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.textclassifier.TextLinks
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.RequestFuture
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val e = findViewById<EditText>(R.id.edit)
        val b = findViewById<Button>(R.id.save)
        val queue = Volley.newRequestQueue(this)

        val t = findViewById<TextView>(R.id.report)
        val url:String = "https://api.openweathermap.org/data/2.5/weather?q="
        val api:String = "&appid=8c4573fb706135d588b53bcf23c37480"

        b.setOnClickListener {
            val city:String = url+e.text+api
             val strRequest:JsonObjectRequest = JsonObjectRequest(Request.Method.GET,
                     city,null,
                     Response.Listener<JSONObject> {
                         response ->
                         val ar:JSONObject = response.getJSONObject("coord")
                         val ar1:JSONObject = response.getJSONObject("main")
                         val s:String = " Longitude: "+ar.get("lon")+"Latitude: "+ar.get("lat")+
                                 " Temperature: "+ar1.get("temp")+" pressure: "+ar1.get("pressure")
                         t.text = s
                     },
                     Response.ErrorListener {

                     })
            queue.add(strRequest)
        }


    }
}
