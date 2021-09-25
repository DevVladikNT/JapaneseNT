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

    private val task2map = mapOf("さ" to "sa", "ざ" to "dza", "し" to "shi", "じ" to "ji", "す" to "su", "ず" to "dzu", "せ" to "se", "ぜ" to "dze", "そ" to "so", "ぞ" to "dzo", "た" to "ta", "だ" to "da", "ち" to "chi", "つ" to "tsu", "て" to "te", "で" to "de", "と" to "to", "ど" to "do")
    private val task2keys = arrayListOf("さ", "ざ", "し", "じ", "す", "ず", "せ", "ぜ", "そ", "ぞ", "た", "だ", "ち", "つ", "て", "で", "と", "ど")
    private val task2mapReversed = mapOf("sa" to "さ", "dza" to "ざ", "shi" to "し", "ji" to "じ", "su" to "す", "dzu" to "ず", "se" to "せ", "dze" to "ぜ", "so" to "そ", "dzo" to "ぞ", "ta" to "た", "da" to "だ", "chi" to "ち", "tsu" to "つ", "te" to "て", "de" to "で", "to" to "と", "do" to "ど")
    private val task2keysReversed = arrayListOf("sa", "dza", "shi", "ji", "su", "dzu", "se", "dze", "so", "dzo", "ta", "da", "chi", "tsu", "te", "de", "to", "do")

    private val task3map = mapOf("な" to "na", "に" to "ni", "ぬ" to "nu", "ね" to "ne", "の" to "no", "は" to "ha", "ひ" to "hi", "ふ" to "fu", "へ" to "he", "ほ" to "ho", "ば" to "ba", "び" to "bi", "ぶ" to "bu", "べ" to "be", "ぼ" to "bo", "ぱ" to "pa", "ぴ" to "pi", "ぷ" to "pu", "ぺ" to "pe", "ぽ" to "po")
    private val task3keys = arrayListOf("な", "に", "ぬ", "ね", "の", "は", "ひ", "ふ", "へ", "ほ", "ば", "び", "ぶ", "べ", "ぼ", "ぱ", "ぴ", "ぷ", "ぺ", "ぽ")
    private val task3mapReversed = mapOf("na" to "な", "ni" to "に", "nu" to "ぬ", "ne" to "ね", "no" to "の", "ha" to "は", "hi" to "ひ", "fu" to "ふ", "he" to "へ", "ho" to "ほ", "ba" to "ば", "bi" to "び", "bu" to "ぶ", "be" to "べ", "bo" to "ぼ", "pa" to "ぱ", "pi" to "ぴ", "pu" to "ぷ", "pe" to "ぺ", "po" to "ぽ")
    private val task3keysReversed = arrayListOf("na", "ni", "nu", "ne", "no", "ha", "hi", "fu", "he", "ho", "ba", "bi", "bu", "be", "bo", "pa", "pi", "pu", "pe", "po")

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
            R.id.hTask2 -> "さ/ざ - sa/dza\n" +
                    "し/じ - shi/ji\n" +
                    "す/ず - su/dzu\n" +
                    "せ/ぜ - se/dze\n" +
                    "そ/ぞ - so/dzo\n" +
                    "た/だ - ta/da\n" +
                    "ち/[ぢ] - chi/[ji]\n" +
                    "つ/[づ] - tsu/[dzu]\n" +
                    "て/で - te/de\n" +
                    "と/ど - to/do"
            R.id.hTask3 -> "な - na\n" +
                    "に - ni\n" +
                    "ぬ - nu\n" +
                    "ね - ne\n" +
                    "の - no\n" +
                    "は/ば/ぱ - ha/ba/pa\n" +
                    "ひ/び/ぴ - hi/bi/pi\n" +
                    "ふ/ぶ/ぷ - fu/bu/pu\n" +
                    "へ/べ/ぺ - he/be/pe\n" +
                    "ほ/ぼ/ぽ - ho/bo/po"
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
        if (counter == 20) {
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
                if (counter < 10) {
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
                if (counter < 10) {
                    if (Math.random() < 0.25) {
                        question = task1keys[(Math.random() * 1000).toInt() % task1keys.size]
                        answer = task1map[question].toString()
                    } else {
                        question = task2keys[(Math.random() * 1000).toInt() % task2keys.size]
                        answer = task2map[question].toString()
                    }
                    findViewById<TextView>(R.id.hQuestion).text = question
                    when ((Math.random() * 1000).toInt() % 4) {
                        0 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = answer
                            findViewById<TextView>(R.id.hAnswer2).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                            findViewById<TextView>(R.id.hAnswer3).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                            findViewById<TextView>(R.id.hAnswer4).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                        }
                        1 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                            findViewById<TextView>(R.id.hAnswer2).text = answer
                            findViewById<TextView>(R.id.hAnswer3).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                            findViewById<TextView>(R.id.hAnswer4).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                        }
                        2 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                            findViewById<TextView>(R.id.hAnswer2).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                            findViewById<TextView>(R.id.hAnswer3).text = answer
                            findViewById<TextView>(R.id.hAnswer4).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                        }
                        3 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                            findViewById<TextView>(R.id.hAnswer2).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                            findViewById<TextView>(R.id.hAnswer3).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                            findViewById<TextView>(R.id.hAnswer4).text = answer
                        }
                    }
                } else {
                    if (Math.random() < 0.25) {
                        question = task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]
                        answer = task1mapReversed[question].toString()
                    } else {
                        question = task2keysReversed[(Math.random() * 1000).toInt() % task2keysReversed.size]
                        answer = task2mapReversed[question].toString()
                    }
                    findViewById<TextView>(R.id.hQuestion).text = question
                    when ((Math.random() * 1000).toInt() % 4) {
                        0 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = answer
                            findViewById<TextView>(R.id.hAnswer2).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer3).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer4).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                        }
                        1 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer2).text = answer
                            findViewById<TextView>(R.id.hAnswer3).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer4).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                        }
                        2 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer2).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer3).text = answer
                            findViewById<TextView>(R.id.hAnswer4).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                        }
                        3 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer2).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer3).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer4).text = answer
                        }
                    }
                }
            }
            3 -> {
                if (counter < 10) {
                    if (Math.random() < 0.25) {
                        question = task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]
                        answer = task1map.plus(task2map)[question].toString()
                    } else {
                        question = task3keys[(Math.random() * 1000).toInt() % task3keys.size]
                        answer = task3map[question].toString()
                    }
                    findViewById<TextView>(R.id.hQuestion).text = question
                    when ((Math.random() * 1000).toInt() % 4) {
                        0 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = answer
                            findViewById<TextView>(R.id.hAnswer2).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                            findViewById<TextView>(R.id.hAnswer3).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                            findViewById<TextView>(R.id.hAnswer4).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                        }
                        1 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                            findViewById<TextView>(R.id.hAnswer2).text = answer
                            findViewById<TextView>(R.id.hAnswer3).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                            findViewById<TextView>(R.id.hAnswer4).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                        }
                        2 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                            findViewById<TextView>(R.id.hAnswer2).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                            findViewById<TextView>(R.id.hAnswer3).text = answer
                            findViewById<TextView>(R.id.hAnswer4).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                        }
                        3 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                            findViewById<TextView>(R.id.hAnswer2).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                            findViewById<TextView>(R.id.hAnswer3).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                            findViewById<TextView>(R.id.hAnswer4).text = answer
                        }
                    }
                } else {
                    if (Math.random() < 0.25) {
                        question = task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]
                        answer = task1mapReversed.plus(task2mapReversed)[question].toString()
                    } else {
                        question = task3keysReversed[(Math.random() * 1000).toInt() % task3keysReversed.size]
                        answer = task3mapReversed[question].toString()
                    }
                    findViewById<TextView>(R.id.hQuestion).text = question
                    when ((Math.random() * 1000).toInt() % 4) {
                        0 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = answer
                            findViewById<TextView>(R.id.hAnswer2).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer3).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer4).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                        }
                        1 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer2).text = answer
                            findViewById<TextView>(R.id.hAnswer3).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer4).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                        }
                        2 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer2).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer3).text = answer
                            findViewById<TextView>(R.id.hAnswer4).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                        }
                        3 -> {
                            findViewById<TextView>(R.id.hAnswer1).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer2).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer3).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                            findViewById<TextView>(R.id.hAnswer4).text = answer
                        }
                    }
                }
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
                findViewById<TextView>(R.id.hProgressbar).text = "$counter/20"
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