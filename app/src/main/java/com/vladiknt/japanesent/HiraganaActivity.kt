package com.vladiknt.japanesent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class HiraganaActivity : AppCompatActivity() {
    private var task = 0
    private var counter = 0
    private var question = ""
    private var answer = ""
    private var answered = false

    private val task1map = mapOf("あ" to "a", "い" to "i", "う" to "u", "え" to "e", "お" to "o", "か" to "ka", "が" to "ga", "き" to "ki", "ぎ" to "gi", "く" to "ku", "ぐ" to "gu", "け" to "ke", "げ" to "ge", "こ" to "ko", "ご" to "go")
    private val task1keys = arrayListOf("あ", "い", "う", "え", "お", "か", "が", "き", "ぎ", "く", "ぐ", "け", "げ", "こ", "ご")
    private val task1mapReversed = mapOf("a" to "あ", "i" to "い", "u" to "う", "e" to "え", "o" to "お", "ka" to "か", "ga" to "が", "ki" to "き", "gi" to "ぎ", "ku" to "く", "gu" to "ぐ", "ke" to "け", "ge" to "げ", "ko" to "こ", "go" to "ご")
    private val task1keysReversed = arrayListOf("a", "i", "u", "e", "o", "ka", "ga", "ki", "gi", "ku", "gu", "ke", "ge", "ko", "go")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hiragana)
    }

    fun explainTask(view: View?) {
        findViewById<LinearLayout>(R.id.hTrain).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.hProgressbar).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.hNextButton).visibility = View.INVISIBLE
        findViewById<ScrollView>(R.id.hExplainTextOutside).visibility = View.VISIBLE
        findViewById<TextView>(R.id.hStartButton).visibility = View.VISIBLE
        task = when (view!!.id) {
            R.id.hTask1 -> 1
            R.id.hTask2 -> 2
            R.id.hTask3 -> 3
            R.id.hTask4 -> 4
            R.id.hTask5 -> 5
            else -> 0
        }
        findViewById<TextView>(R.id.hExplainText).text = when (view.id) {
            R.id.hTask1 -> "あ - a\n" +
                    "い - i\n" +
                    "う - u\n" +
                    "え - e\n" +
                    "お - o\n" +
                    "か/が - ka/ga\n" +
                    "き/ぎ - ki/gi\n" +
                    "く/ぐ - ku/gu\n" +
                    "け/げ - ke/ge\n" +
                    "こ/ご - ko/go"
            R.id.hTask2 -> "empty"
            R.id.hTask3 -> "empty"
            R.id.hTask4 -> "empty"
            R.id.hTask5 -> "empty"
            else -> ""
        }
    }

    fun startTrain(view: View?) {
        findViewById<LinearLayout>(R.id.hTrain).visibility = View.VISIBLE
        findViewById<TextView>(R.id.hProgressbar).visibility = View.VISIBLE
        findViewById<TextView>(R.id.hNextButton).visibility = View.VISIBLE
        findViewById<ScrollView>(R.id.hExplainTextOutside).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.hStartButton).visibility = View.INVISIBLE
        counter = 0
        answered = true
        loadQuestion(null)
    }

    fun loadQuestion(view: View?) {
        if (!answered) return
        if (counter == 10) {
            findViewById<LinearLayout>(R.id.hTrain).visibility = View.INVISIBLE
            findViewById<TextView>(R.id.hProgressbar).visibility = View.INVISIBLE
            findViewById<TextView>(R.id.hNextButton).visibility = View.INVISIBLE
            findViewById<ScrollView>(R.id.hExplainTextOutside).visibility = View.VISIBLE
            findViewById<TextView>(R.id.hStartButton).visibility = View.VISIBLE
            return
        }
        findViewById<TextView>(R.id.hAnswer1).setTextColor(resources.getColor(R.color.dayColorText))
        findViewById<TextView>(R.id.hAnswer2).setTextColor(resources.getColor(R.color.dayColorText))
        findViewById<TextView>(R.id.hAnswer3).setTextColor(resources.getColor(R.color.dayColorText))
        findViewById<TextView>(R.id.hAnswer4).setTextColor(resources.getColor(R.color.dayColorText))
        answered = false
        when (task) {
            1 -> {
                if (counter % 2 == 0) {
                    question = task1keys[(Math.random() * 1000).toInt() % task1keys.size]
                    answer = task1map[question].toString()
                    findViewById<TextView>(R.id.hQuestion).text = question
                    when ((Math.random() * 1000).toInt() % 4) {
                        0 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = answer
                            findViewById<TextView>(R.id.hAnswer2).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                            findViewById<TextView>(R.id.hAnswer3).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                            findViewById<TextView>(R.id.hAnswer4).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                        }
                        1 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                            findViewById<TextView>(R.id.hAnswer2).text = answer
                            findViewById<TextView>(R.id.hAnswer3).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                            findViewById<TextView>(R.id.hAnswer4).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                        }
                        2 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                            findViewById<TextView>(R.id.hAnswer2).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                            findViewById<TextView>(R.id.hAnswer3).text = answer
                            findViewById<TextView>(R.id.hAnswer4).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                        }
                        3 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                            findViewById<TextView>(R.id.hAnswer2).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                            findViewById<TextView>(R.id.hAnswer3).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                            findViewById<TextView>(R.id.hAnswer4).text = answer
                        }
                    }
                } else {
                    question = task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]
                    answer = task1mapReversed[question].toString()
                    findViewById<TextView>(R.id.hQuestion).text = question
                    when ((Math.random() * 1000).toInt() % 4) {
                        0 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = answer
                            findViewById<TextView>(R.id.hAnswer2).text = task1mapReversed[task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                            findViewById<TextView>(R.id.hAnswer3).text = task1mapReversed[task1keys[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                            findViewById<TextView>(R.id.hAnswer4).text = task1mapReversed[task1keys[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                        }
                        1 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1mapReversed[task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                            findViewById<TextView>(R.id.hAnswer2).text = answer
                            findViewById<TextView>(R.id.hAnswer3).text = task1mapReversed[task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                            findViewById<TextView>(R.id.hAnswer4).text = task1mapReversed[task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                        }
                        2 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1mapReversed[task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                            findViewById<TextView>(R.id.hAnswer2).text = task1mapReversed[task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                            findViewById<TextView>(R.id.hAnswer3).text = answer
                            findViewById<TextView>(R.id.hAnswer4).text = task1mapReversed[task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                        }
                        3 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1mapReversed[task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                            findViewById<TextView>(R.id.hAnswer2).text = task1mapReversed[task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                            findViewById<TextView>(R.id.hAnswer3).text = task1mapReversed[task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                            findViewById<TextView>(R.id.hAnswer4).text = answer
                        }
                    }
                }
            }
            2 -> {
                //
            }
            3 -> {
                //
            }
            4 -> {
                //
            }
            5 -> {
                //
            }
        }
    }

    fun checkAnswer(view: View?) {
        if (answered)
            loadQuestion(null)
        else {
            answered = true
            if (findViewById<TextView>(view!!.id).text == answer) {
                findViewById<TextView>(view.id).setTextColor(resources.getColor(R.color.trueAnswer))
                counter++
                findViewById<TextView>(R.id.hProgressbar).text = "$counter/10"
            }
            else {
                findViewById<TextView>(view.id).setTextColor(resources.getColor(R.color.falseAnswer))
                when (answer) {
                    findViewById<TextView>(R.id.hAnswer1).text -> findViewById<TextView>(R.id.hAnswer1).setTextColor(resources.getColor(R.color.trueAnswer))
                    findViewById<TextView>(R.id.hAnswer2).text -> findViewById<TextView>(R.id.hAnswer2).setTextColor(resources.getColor(R.color.trueAnswer))
                    findViewById<TextView>(R.id.hAnswer3).text -> findViewById<TextView>(R.id.hAnswer3).setTextColor(resources.getColor(R.color.trueAnswer))
                    findViewById<TextView>(R.id.hAnswer4).text -> findViewById<TextView>(R.id.hAnswer4).setTextColor(resources.getColor(R.color.trueAnswer))
                }
            }
        }
    }
}