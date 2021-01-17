package com.example.memorygame1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.graphics.set
import kotlinx.android.synthetic.main.activity_main.*
import java.text.Format

class MainActivity : AppCompatActivity() {

    var parlayt = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT
        ,LinearLayout.LayoutParams.MATCH_PARENT, 1f)
    var list = ArrayList<Figure>()
    var listImg = ArrayList<Int>()
    var buttonArrayList = ArrayList<ImageButton>()
    var arrId = ArrayList<Int>()
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intentKey = intent.getSerializableExtra("format") as FormatXY

        creatNumber(intentKey.x, intentKey.y)
        creatbutton(intentKey.x, intentKey.y)
    }

    private fun creatbutton(x: Int , y: Int) {


        var parimgbut = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT
            , LinearLayout.LayoutParams.MATCH_PARENT, 1f)
        val USERID = 6000

        for (k in 1..y) {

            var ll =  LinearLayout(this)
            ll.id = USERID + k
            linear_main.addView(ll, parlayt)
            for (i in 1..x) {


                var imageButton = ImageButton(this)
//
                val bitmap = Bitmap.createBitmap(
                    50, 50,
                    Bitmap.Config.ARGB_8888
                )
                imageButton.setImageBitmap(bitmap)

//                val bitmap = BitmapFactory.decodeResource(resources, R.drawable.question)
//
//                imageButton.setImageBitmap(bitmap)

                imageButton.layoutParams = parimgbut
                imageButton.background = resources.getDrawable(R.drawable.btnstyle)
                imageButton.setImageResource(list[list.lastIndex].id)


                //imageButton.resources.getDrawable(R.drawable.znak_vopros)
                ll.addView(imageButton)

                buttonArrayList.add(imageButton)
                imageButton.setOnClickListener {

                    if (list[buttonArrayList.indexOf(imageButton)].vop == 0)
                    {
                        chektheButton(buttonArrayList.indexOf(imageButton))
                    }
                }
            }
        }
    }



    private fun chektheButton(id: Int){


        when(count){

            0,1 -> {
                animationaction(buttonArrayList[id], id)
                arrId.add(id)
                count++
                list[id].vop = 1
            }
            2 -> {
                list[id].vop = 1
                if (list[arrId[0]].id == list[arrId[1]].id){
                    list[id].vop = 1
                    animationaction(buttonArrayList[id], id)
                }else
                {
                    animationaction(buttonArrayList[id], id)

                    animationaction(buttonArrayList[arrId[0]], list.lastIndex)
                    animationaction(buttonArrayList[arrId[1]], list.lastIndex)

                    list[arrId[0]].vop = 0
                    list[arrId[1]].vop = 0

                }

                count = 1
                arrId.clear()
                arrId.add(id)
            }
        }

        checkButton()
    }

    private fun animationaction(imageButton1: ImageButton, id: Int){

        val animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.scale)

        animation.duration = 250
        imageButton1.startAnimation(animation)

        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {

                imageButton1.setImageResource(list[id].id)

                var animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.scale_open)
                animation.duration = 250
                imageButton1.startAnimation(animation)
            }

            override fun onAnimationStart(animation: Animation?) {

            }

        })

    }
    private fun checkButton(){

        var ans = false
        for (i in 0..list.size-2)
        {
            if (list[i].vop == 0){
                ans = true
                break
            }
        }

        if (ans == false){

            Toast.makeText(this,"YOU WIN !", Toast.LENGTH_SHORT).show()
        }
    }


    private fun creatNumber(x: Int, y: Int) {

        listImg.add(R.drawable.cilindr)
        listImg.add(R.drawable.krug)
        listImg.add(R.drawable.kub)
        listImg.add(R.drawable.kvadrat)
        listImg.add(R.drawable.piramida)
        listImg.add(R.drawable.romb)
        listImg.add(R.drawable.shestiugolnik)
        listImg.add(R.drawable.treugolnik)
        listImg.add(R.drawable.question)


        list.clear()

        var z = (x * y)/2

        for (i in 1..z) {

            list.add(Figure(listImg[i-1], 0))
            list.add(Figure(listImg[i-1], 0))
        }
        list.shuffle()
        list.add(Figure(listImg[listImg.lastIndex], 0))

    }



}

