package com.example.sokogarden

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class PaymentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        // find the views by use of their ids
        val txtname = findViewById<TextView>(R.id.txtProductName)
        val txtcost = findViewById<TextView>(R.id.txtProductCost)
        val imgProduct = findViewById<ImageView>(R.id.imgProduct)
        val txtdescription = findViewById<TextView>(R.id.txtProductDescription)


        // Retrieve the Data passed through previous Activity(MainActivity)
        val name = intent.getStringExtra("product_name")
        val cost = intent.getIntExtra("product_cost", 0)
        val product_description = intent.getStringExtra("product_description")
        val product_photo = intent.getStringExtra("product_photo")

        // Update the textview with the data passed from the previous Activity
        txtname.text = name
        txtcost.text = "ksh $cost"
        txtdescription.text = product_description

        // specify the image URL
        val imageUrl = "https://saruninimrod.alwaysdata.net/static/images/$product_photo"


        Glide.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.ic_launcher_background) // Make sure you have a placeholder image
            .into(imgProduct)

        // find the EditText and the Pay Now button by use
        val phone = findViewById<EditText>(R.id.phone)
        val btnPay = findViewById<Button>(R.id.pay)

        // use click listener on the button
        btnPay.setOnClickListener {
            //specify the api endpoint for making payment
            val api = "https://saruninimrod.alwaysdata.net/api/mpesa_payment"

            // create a Request Params
            val data = RequestParams()

            // insert data into the RequestParams
            data.put("amount", cost)
            data.put("phone", phone.text.toString().trim())

            // import the helper class
            val helper = ApiHelper(applicationContext)

            //Access the post function inside of helper class
            helper.post(api, data)

            // clear phone number from edit text
            phone.text.clear()


        }


    }
}