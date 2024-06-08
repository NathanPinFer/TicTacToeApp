package com.example.tictactoe

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class TicTacToeActivity : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)

        val letsPlay = findViewById<ImageView>(R.id.letsplay)



        fun setOnTouchListener(view: View, event: MotionEvent): Boolean {
            val letsplay = findViewById<ImageView>(R.id.letsplay)
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    letsplay.scaleX = 1.2f
                    letsplay.scaleY = 1.2f
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    letsplay.scaleX = 1.0f
                    letsplay.scaleY = 1.0f
                    val intent = Intent(view.context, GameActivity::class.java)
                    view.context.startActivity(intent)

                }
            }
            return true
        }
        letsPlay.setOnTouchListener { _, event ->
            setOnTouchListener(letsPlay, event)
        }


    }
}
