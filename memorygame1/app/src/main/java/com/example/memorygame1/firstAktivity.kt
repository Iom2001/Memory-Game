package com.example.memorygame1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_first_aktivity.*

class firstAktivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_aktivity)




        btn_6.setOnClickListener {

            openGame(3, 2)

        }

        btn_12.setOnClickListener {

            openGame(3, 4)

        }

        btn_16.setOnClickListener {

            openGame(4, 4)

        }

    }

    private fun openGame(x: Int, y: Int){

        var intent = Intent(this, MainActivity::class.java)
        intent.putExtra("format", FormatXY(x, y))
        startActivity(intent)

    }


}
