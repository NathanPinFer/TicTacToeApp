package com.example.tictactoe

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var btnClicked = false
        var player1Win = false
        var player2Win = false
        lateinit var board: Array<Array<Int>>

        fun initBoard() {
            board = Array(3) {
                Array(3) {
                    0
                }
            }
        }

        val player1Wins = findViewById<ImageView>(R.id.win1)
        val player2Wins = findViewById<ImageView>(R.id.win2)
        val tie = findViewById<ImageView>(R.id.tiee)
        val btn01 = findViewById<Button>(R.id.btn01)
        val btn02 = findViewById<Button>(R.id.btn02)
        val btn03 = findViewById<Button>(R.id.btn03)
        val btn04 = findViewById<Button>(R.id.btn04)
        val btn05 = findViewById<Button>(R.id.btn05)
        val btn06 = findViewById<Button>(R.id.btn06)
        val btn07 = findViewById<Button>(R.id.btn07)
        val btn08 = findViewById<Button>(R.id.btn08)
        val btn09 = findViewById<Button>(R.id.btn09)
        val restart = findViewById<Button>(R.id.restart)

        player1Wins.visibility = View.GONE
        player2Wins.visibility = View.GONE
        restart.visibility = View.GONE
        tie.visibility = View.GONE

        fun disableImages() {
            player1Wins.visibility = View.GONE
            player2Wins.visibility = View.GONE
            restart.visibility = View.GONE
            tie.visibility = View.GONE
            btn01.setBackgroundResource(R.color.transparent)
            btn02.setBackgroundResource(R.color.transparent)
            btn03.setBackgroundResource(R.color.transparent)
            btn04.setBackgroundResource(R.color.transparent)
            btn05.setBackgroundResource(R.color.transparent)
            btn06.setBackgroundResource(R.color.transparent)
            btn07.setBackgroundResource(R.color.transparent)
            btn08.setBackgroundResource(R.color.transparent)
            btn09.setBackgroundResource(R.color.transparent)
        }

        fun restartGame(btn: Button, event: MotionEvent): Boolean {
            restart.visibility = View.VISIBLE
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    btn.scaleX = 1.2f
                    btn.scaleY = 1.2f
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    btn.scaleX = 1.0f
                    btn.scaleY = 1.0f
                    initBoard()
                    disableImages()
                    player1Win = false
                    player2Win = false
                    btnClicked = false
                    btn01.isEnabled = true
                    btn02.isEnabled = true
                    btn03.isEnabled = true
                    btn04.isEnabled = true
                    btn05.isEnabled = true
                    btn06.isEnabled = true
                    btn07.isEnabled = true
                    btn08.isEnabled = true
                    btn09.isEnabled = true

                }
            }
            return true
        }

        fun disableAllButtonsExcept() {
            btn01.isEnabled = false
            btn02.isEnabled = false
            btn03.isEnabled = false
            btn04.isEnabled = false
            btn05.isEnabled = false
            btn06.isEnabled = false
            btn07.isEnabled = false
            btn08.isEnabled = false
            btn09.isEnabled = false
        }

        fun checkTie() {
            var containZero = false
            for (i in 0 until 3) {
                for (j in 0 until 3) {
                    if (board[i][j] == 0) {
                        containZero = true
                        break
                    }
                }
            }
            if (!containZero && !player1Win && !player2Win) {
                tie.visibility = View.VISIBLE
                restart.visibility = View.VISIBLE
                disableAllButtonsExcept()
            }
        }

        fun btn01SetOnTouchListener(btn: Button, event: MotionEvent, player1: ImageView, player2: ImageView): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!btnClicked) {
                        btn.setBackgroundResource(R.drawable.x)
                        board[0][0] = 1
                        println(board[0][0])
                    } else {
                        btn.setBackgroundResource(R.drawable.circle)
                        board[0][0] = 2
                    }
                    btn.isEnabled = false
                    btnClicked = !btnClicked
                    for (i in 0 until 3) {
                        for (j in 0 until 1) {
                            if (board[i][j] == 1 && board[i][j + 1] == 1 && board[i][j + 2] == 1) {
                                player1Win = true
                            }
                            if (board[i][j] == 2 && board[i][j + 1] == 2 && board[i][j + 2] == 2) {
                                player2Win = true
                            }
                            if (board[j][i] == 1 && board[j + 1][i] == 1 && board[j + 2][i] == 1) {
                                player1Win = true
                            }
                            if (board[j][i] == 2 && board[j + 1][i] == 2 && board[j + 2][i] == 2) {
                                player2Win = true
                            }
                        }
                    }

                    for (i in 0 until 1) {
                        for (j in 0 until 1) {
                            if (board[i][j] == 1 && board[i + 1][j + 1] == 1 && board[i + 2][j + 2] == 1) {
                                player1Win = true
                            }
                            if (board[i][j] == 2 && board[i + 1][j + 1] == 2 && board[i + 2][j + 2] == 2) {
                                player2Win = true
                            }
                            if (board[i][j + 2] == 1 && board[i + 1][j + 1] == 1 && board[i + 2][j] == 1) {
                                player1Win = true
                            }
                            if (board[i][j + 2] == 2 && board[i + 1][j + 1] == 2 && board[i + 2][j] == 2) {
                                player2Win = true
                            }
                        }
                    }
                    checkTie()
                    if (player1Win) {
                        player1.visibility = View.VISIBLE
                        disableAllButtonsExcept()
                        restart.visibility = View.VISIBLE
                    }
                    if (player2Win) {
                        player2.visibility = View.VISIBLE
                        disableAllButtonsExcept()
                        restart.visibility = View.VISIBLE
                    }
                    true
                }

                MotionEvent.ACTION_UP -> {
                    true
                }

                else -> false
            }
            return true
        }

        fun btn02SetOnTouchListener(btn: Button, event: MotionEvent, player1: ImageView, player2: ImageView): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!btnClicked) {
                        btn.setBackgroundResource(R.drawable.x)
                        board[0][1] = 1
                    } else {
                        btn.setBackgroundResource(R.drawable.circle)
                        board[0][1] = 2
                    }
                    btn.isEnabled = false
                    btnClicked = !btnClicked
                    for (i in 0 until 3) {
                        for (j in 0 until 1) {
                            if (board[i][j] == 1 && board[i][j + 1] == 1 && board[i][j + 2] == 1) {
                                player1Win = true
                            }
                            if (board[i][j] == 2 && board[i][j + 1] == 2 && board[i][j + 2] == 2) {
                                player2Win = true
                            }
                            if (board[j][i] == 1 && board[j + 1][i] == 1 && board[j + 2][i] == 1) {
                                player1Win = true
                            }
                            if (board[j][i] == 2 && board[j + 1][i] == 2 && board[j + 2][i] == 2) {
                                player2Win = true
                            }
                        }
                        checkTie()
                        if (player1Win) {
                            player1.visibility = View.VISIBLE
                            disableAllButtonsExcept()
                            restart.visibility = View.VISIBLE
                        }
                        if (player2Win) {
                            player2.visibility = View.VISIBLE
                            disableAllButtonsExcept()
                            restart.visibility = View.VISIBLE
                        }
                    }
                    true
                }

                MotionEvent.ACTION_UP -> {
                    true
                }

                else -> false
            }
            return true
        }

        fun btn03SetOnTouchListener(btn: Button, event: MotionEvent, player1: ImageView, player2: ImageView): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!btnClicked) {
                        btn.setBackgroundResource(R.drawable.x)
                        board[0][2] = 1
                    } else {
                        btn.setBackgroundResource(R.drawable.circle)
                        board[0][2] = 2
                    }
                    btn.isEnabled = false
                    btnClicked = !btnClicked
                    for (i in 0 until 3) {
                        for (j in 0 until 1) {
                            if (board[i][j] == 1 && board[i][j + 1] == 1 && board[i][j + 2] == 1) {
                                player1Win = true
                            }
                            if (board[i][j] == 2 && board[i][j + 1] == 2 && board[i][j + 2] == 2) {
                                player2Win = true
                            }
                            if (board[j][i] == 1 && board[j + 1][i] == 1 && board[j + 2][i] == 1) {
                                player1Win = true
                            }
                            if (board[j][i] == 2 && board[j + 1][i] == 2 && board[j + 2][i] == 2) {
                                player2Win = true
                            }
                        }
                    }

                    for (i in 0 until 1) {
                        for (j in 0 until 1) {
                            if (board[i][j] == 1 && board[i + 1][j + 1] == 1 && board[i + 2][j + 2] == 1) {
                                player1Win = true
                            }
                            if (board[i][j] == 2 && board[i + 1][j + 1] == 2 && board[i + 2][j + 2] == 2) {
                                player2Win = true
                            }
                            if (board[i][j + 2] == 1 && board[i + 1][j + 1] == 1 && board[i + 2][j] == 1) {
                                player1Win = true
                            }
                            if (board[i][j + 2] == 2 && board[i + 1][j + 1] == 2 && board[i + 2][j] == 2) {
                                player2Win = true
                            }
                        }
                    }
                    checkTie()

                    if (player1Win) {
                        player1.visibility = View.VISIBLE
                        disableAllButtonsExcept()
                        restart.visibility = View.VISIBLE
                    }
                    if (player2Win) {
                        player2.visibility = View.VISIBLE
                        disableAllButtonsExcept()
                        restart.visibility = View.VISIBLE
                    }
                    true
                }

                MotionEvent.ACTION_UP -> {
                    true
                }

                else -> false
            }
            return true
        }

        fun btn04SetOnTouchListener(btn: Button, event: MotionEvent, player1: ImageView, player2: ImageView): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!btnClicked) {
                        btn.setBackgroundResource(R.drawable.x)
                        board[1][0] = 1
                    } else {
                        btn.setBackgroundResource(R.drawable.circle)
                        board[1][0] = 2
                    }
                    btn.isEnabled = false
                    btnClicked = !btnClicked
                    for (i in 0 until 3) {
                        for (j in 0 until 1) {
                            if (board[i][j] == 1 && board[i][j + 1] == 1 && board[i][j + 2] == 1) {
                                player1Win = true
                            }
                            if (board[i][j] == 2 && board[i][j + 1] == 2 && board[i][j + 2] == 2) {
                                player2Win = true
                            }
                            if (board[j][i] == 1 && board[j + 1][i] == 1 && board[j + 2][i] == 1) {
                                player1Win = true
                            }
                            if (board[j][i] == 2 && board[j + 1][i] == 2 && board[j + 2][i] == 2) {
                                player2Win = true
                            }
                        }
                        checkTie()
                        if (player1Win) {
                            player1.visibility = View.VISIBLE
                            disableAllButtonsExcept()
                            restart.visibility = View.VISIBLE
                        }
                        if (player2Win) {
                            player2.visibility = View.VISIBLE
                            disableAllButtonsExcept()
                            restart.visibility = View.VISIBLE
                        }
                    }
                    true
                }

                MotionEvent.ACTION_UP -> {
                    true
                }

                else -> false
            }
            return true
        }

        fun btn05SetOnTouchListener(btn: Button, event: MotionEvent, player1: ImageView, player2: ImageView): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!btnClicked) {
                        btn.setBackgroundResource(R.drawable.x)
                        board[1][1] = 1
                    } else {
                        btn.setBackgroundResource(R.drawable.circle)
                        board[1][1] = 2
                    }
                    btn.isEnabled = false
                    btnClicked = !btnClicked
                    for (i in 0 until 3) {
                        for (j in 0 until 1) {
                            if (board[i][j] == 1 && board[i][j + 1] == 1 && board[i][j + 2] == 1) {
                                player1Win = true
                            }
                            if (board[i][j] == 2 && board[i][j + 1] == 2 && board[i][j + 2] == 2) {
                                player2Win = true
                            }
                            if (board[j][i] == 1 && board[j + 1][i] == 1 && board[j + 2][i] == 1) {
                                player1Win = true
                            }
                            if (board[j][i] == 2 && board[j + 1][i] == 2 && board[j + 2][i] == 2) {
                                player2Win = true
                            }
                        }
                    }

                    for (i in 0 until 1) {
                        for (j in 0 until 1) {
                            if (board[i][j] == 1 && board[i + 1][j + 1] == 1 && board[i + 2][j + 2] == 1) {
                                player1Win = true
                            }
                            if (board[i][j] == 2 && board[i + 1][j + 1] == 2 && board[i + 2][j + 2] == 2) {
                                player2Win = true
                            }
                            if (board[i][j + 2] == 1 && board[i + 1][j + 1] == 1 && board[i + 2][j] == 1) {
                                player1Win = true
                            }
                            if (board[i][j + 2] == 2 && board[i + 1][j + 1] == 2 && board[i + 2][j] == 2) {
                                player2Win = true
                            }
                        }
                    }
                    checkTie()
                    if (player1Win) {
                        player1.visibility = View.VISIBLE
                        disableAllButtonsExcept()
                        restart.visibility = View.VISIBLE
                    }
                    if (player2Win) {
                        player2.visibility = View.VISIBLE
                        disableAllButtonsExcept()
                        restart.visibility = View.VISIBLE
                    }
                    true
                }

                MotionEvent.ACTION_UP -> {
                    true
                }

                else -> false
            }
            return true
        }

        fun btn06SetOnTouchListener(btn: Button, event: MotionEvent, player1: ImageView, player2: ImageView): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!btnClicked) {
                        btn.setBackgroundResource(R.drawable.x)
                        board[1][2] = 1
                    } else {
                        btn.setBackgroundResource(R.drawable.circle)
                        board[1][2] = 2
                    }
                    btn.isEnabled = false
                    btnClicked = !btnClicked
                    for (i in 0 until 3) {
                        for (j in 0 until 1) {
                            if (board[i][j] == 1 && board[i][j + 1] == 1 && board[i][j + 2] == 1) {
                                player1Win = true
                            }
                            if (board[i][j] == 2 && board[i][j + 1] == 2 && board[i][j + 2] == 2) {
                                player2Win = true
                            }
                            if (board[j][i] == 1 && board[j + 1][i] == 1 && board[j + 2][i] == 1) {
                                player1Win = true
                            }
                            if (board[j][i] == 2 && board[j + 1][i] == 2 && board[j + 2][i] == 2) {
                                player2Win = true
                            }
                        }
                        checkTie()
                        if (player1Win) {
                            player1.visibility = View.VISIBLE
                            disableAllButtonsExcept()
                            restart.visibility = View.VISIBLE
                        }
                        if (player2Win) {
                            player2.visibility = View.VISIBLE
                            disableAllButtonsExcept()
                            restart.visibility = View.VISIBLE
                        }
                    }
                    true
                }

                MotionEvent.ACTION_UP -> {
                    true
                }

                else -> false
            }
            return true
        }

        fun btn07SetOnTouchListener(btn: Button, event: MotionEvent, player1: ImageView, player2: ImageView): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!btnClicked) {
                        btn.setBackgroundResource(R.drawable.x)
                        board[2][0] = 1
                    } else {
                        btn.setBackgroundResource(R.drawable.circle)
                        board[2][0] = 2
                    }
                    btn.isEnabled = false
                    btnClicked = !btnClicked
                    for (i in 0 until 3) {
                        for (j in 0 until 1) {
                            if (board[i][j] == 1 && board[i][j + 1] == 1 && board[i][j + 2] == 1) {
                                player1Win = true
                            }
                            if (board[i][j] == 2 && board[i][j + 1] == 2 && board[i][j + 2] == 2) {
                                player2Win = true
                            }
                            if (board[j][i] == 1 && board[j + 1][i] == 1 && board[j + 2][i] == 1) {
                                player1Win = true
                            }
                            if (board[j][i] == 2 && board[j + 1][i] == 2 && board[j + 2][i] == 2) {
                                player2Win = true
                            }
                        }
                    }

                    for (i in 0 until 1) {
                        for (j in 0 until 1) {
                            if (board[i][j] == 1 && board[i + 1][j + 1] == 1 && board[i + 2][j + 2] == 1) {
                                player1Win = true
                            }
                            if (board[i][j] == 2 && board[i + 1][j + 1] == 2 && board[i + 2][j + 2] == 2) {
                                player2Win = true
                            }
                            if (board[i][j + 2] == 1 && board[i + 1][j + 1] == 1 && board[i + 2][j] == 1) {
                                player1Win = true
                            }
                            if (board[i][j + 2] == 2 && board[i + 1][j + 1] == 2 && board[i + 2][j] == 2) {
                                player2Win = true
                            }
                        }
                    }
                    checkTie()
                    if (player1Win) {
                        player1.visibility = View.VISIBLE
                        disableAllButtonsExcept()
                        restart.visibility = View.VISIBLE
                    }
                    if (player2Win) {
                        player2.visibility = View.VISIBLE
                        disableAllButtonsExcept()
                        restart.visibility = View.VISIBLE
                    }
                    true
                }

                MotionEvent.ACTION_UP -> {
                    true
                }

                else -> false
            }
            return true
        }

        fun btn08SetOnTouchListener(btn: Button, event: MotionEvent, player1: ImageView, player2: ImageView): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!btnClicked) {
                        btn.setBackgroundResource(R.drawable.x)
                        board[2][1] = 1
                    } else {
                        btn.setBackgroundResource(R.drawable.circle)
                        board[2][1] = 2
                    }
                    btn.isEnabled = false
                    btnClicked = !btnClicked
                    for (i in 0 until 3) {
                        for (j in 0 until 1) {
                            if (board[i][j] == 1 && board[i][j + 1] == 1 && board[i][j + 2] == 1) {
                                player1Win = true
                            }
                            if (board[i][j] == 2 && board[i][j + 1] == 2 && board[i][j + 2] == 2) {
                                player2Win = true
                            }
                            if (board[j][i] == 1 && board[j + 1][i] == 1 && board[j + 2][i] == 1) {
                                player1Win = true
                            }
                            if (board[j][i] == 2 && board[j + 1][i] == 2 && board[j + 2][i] == 2) {
                                player2Win = true
                            }
                        }
                        checkTie()
                        if (player2Win) {
                            player2.visibility = View.VISIBLE
                            disableAllButtonsExcept()
                            restart.visibility = View.VISIBLE
                        }
                        if (player1Win) {
                            player1.visibility = View.VISIBLE
                            disableAllButtonsExcept()
                            restart.visibility = View.VISIBLE
                        }
                    }
                    true
                }

                MotionEvent.ACTION_UP -> {
                    true
                }

                else -> false
            }
            return true
        }

        fun btn09SetOnTouchListener(btn: Button, event: MotionEvent, player1: ImageView, player2: ImageView): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    if (!btnClicked) {
                        btn.setBackgroundResource(R.drawable.x)
                        board[2][2] = 1
                    } else {
                        btn.setBackgroundResource(R.drawable.circle)
                        board[2][2] = 2
                    }
                    btn.isEnabled = false
                    btnClicked = !btnClicked
                    for (i in 0 until 3) {
                        for (j in 0 until 1) {
                            if (board[i][j] == 1 && board[i][j + 1] == 1 && board[i][j + 2] == 1) {
                                player1Win = true
                            }
                            if (board[i][j] == 2 && board[i][j + 1] == 2 && board[i][j + 2] == 2) {
                                player2Win = true
                            }
                            if (board[j][i] == 1 && board[j + 1][i] == 1 && board[j + 2][i] == 1) {
                                player1Win = true
                            }
                            if (board[j][i] == 2 && board[j + 1][i] == 2 && board[j + 2][i] == 2) {
                                player2Win = true
                            }
                        }
                    }

                    for (i in 0 until 1) {
                        for (j in 0 until 1) {
                            if (board[i][j] == 1 && board[i + 1][j + 1] == 1 && board[i + 2][j + 2] == 1) {
                                player1Win = true
                            }
                            if (board[i][j] == 2 && board[i + 1][j + 1] == 2 && board[i + 2][j + 2] == 2) {
                                player2Win = true
                            }
                            if (board[i][j + 2] == 1 && board[i + 1][j + 1] == 1 && board[i + 2][j] == 1) {
                                player1Win = true
                            }
                            if (board[i][j + 2] == 2 && board[i + 1][j + 1] == 2 && board[i + 2][j] == 2) {
                                player2Win = true
                            }
                        }
                    }
                    checkTie()
                    if (player2Win) {
                        player2.visibility = View.VISIBLE
                        disableAllButtonsExcept()
                        restart.visibility = View.VISIBLE
                    }
                    if (player1Win) {
                        player1.visibility = View.VISIBLE
                        disableAllButtonsExcept()
                        restart.visibility = View.VISIBLE
                    }
                    true
                }

                MotionEvent.ACTION_UP -> {
                    true
                }

                else -> false
            }

            return true
        }

        initBoard()

        btn01.setOnTouchListener { _, event ->
            btn01SetOnTouchListener(btn01, event, player1Wins, player2Wins)
        }
        btn02.setOnTouchListener { _, event ->
            btn02SetOnTouchListener(btn02, event, player1Wins, player2Wins)
        }
        btn03.setOnTouchListener { _, event ->
            btn03SetOnTouchListener(btn03, event, player1Wins, player2Wins)
        }
        btn04.setOnTouchListener { _, event ->
            btn04SetOnTouchListener(btn04, event, player1Wins, player2Wins)
        }
        btn05.setOnTouchListener { _, event ->
            btn05SetOnTouchListener(btn05, event, player1Wins, player2Wins)
        }
        btn06.setOnTouchListener { _, event ->
            btn06SetOnTouchListener(btn06, event, player1Wins, player2Wins)
        }
        btn07.setOnTouchListener { _, event ->
            btn07SetOnTouchListener(btn07, event, player1Wins, player2Wins)
        }
        btn08.setOnTouchListener { _, event ->
            btn08SetOnTouchListener(btn08, event, player1Wins, player2Wins)
        }
        btn09.setOnTouchListener { _, event ->
            btn09SetOnTouchListener(btn09, event, player1Wins, player2Wins)
        }
        restart.setOnTouchListener {_, event ->
            restartGame(restart, event)
        }
    }


}
