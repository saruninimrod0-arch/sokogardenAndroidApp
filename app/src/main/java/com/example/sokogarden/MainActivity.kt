package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

//       find the buttons by use of their ids
        val signinButton = findViewById<Button>(R.id.signinBtn)
        val signupButton = findViewById<Button>(R.id.signupBtn)

// create intents to the two activities
        signupButton.setOnClickListener {
            val intent = Intent(applicationContext, Signup::class.java)
            startActivity(intent)

        }
//        =====================================
        signinButton.setOnClickListener {
            val intent = Intent(applicationContext, Signin::class.java)
            startActivity(intent)
        }
        //Refresh theactivity

        // find the recyclerView and the progress bar by use of their IDs
        val recyclerView = findViewById<RecyclerView>(R.id.recycleView)
        val progressbar = findViewById<ProgressBar>(R.id.progress_bar)

//specify the API URL endpoint for fetching the products (alwaysData)
//specify the API URL endpoint for fetching the products (alwaysData)
        val url = "https://saruninimrod.alwaysdata.net/api/get_products"

// import the helper class
        val helper = ApiHelper(applicationContext)

//inside of the helper class, access the function loadproducts
        helper.loadProducts(url, recyclerView, progressbar)
    }

}