package com.vladiknt.japanesent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class KatakanaActivity : AppCompatActivity() {
    private var task = 0
    private var counter = 0
    private var question = ""
    private var answer = ""
    private var answered = false

    private val task1map = mapOf("ア" to "a", "イ" to "i", "ウ" to "u", "エ" to "e", "オ" to "o", "カ" to "ka", "ガ" to "ga", "キ" to "ki", "ギ" to "gi", "ク" to "ku", "グ" to "gu", "ケ" to "ke", "ゲ" to "ge", "コ" to "ko", "ゴ" to "go")
    private val task1keys = arrayListOf("ア", "イ", "ウ", "エ", "オ", "カ", "ガ", "キ", "ギ", "ク", "グ", "ケ", "ゲ", "コ", "ゴ")
    private val task1mapReversed = mapOf("a" to "ア", "i" to "イ", "u" to "ウ", "e" to "エ", "o" to "オ", "ka" to "カ", "ga" to "ガ", "ki" to "キ", "gi" to "ギ", "ku" to "ク", "gu" to "グ", "ke" to "ケ", "ge" to "ゲ", "ko" to "コ", "go" to "ゴ")
    private val task1keysReversed = arrayListOf("a", "i", "u", "e", "o", "ka", "ga", "ki", "gi", "ku", "gu", "ke", "ge", "ko", "go")

    private val task2map = mapOf("サ" to "sa", "ザ" to "dza", "シ" to "shi", "ジ" to "ji", "ス" to "su", "ズ" to "dzu", "セ" to "se", "ゼ" to "dze", "ソ" to "so", "ゾ" to "dzo", "タ" to "ta", "ダ" to "da", "チ" to "chi", "ツ" to "tsu", "テ" to "te", "デ" to "de", "ト" to "to", "ド" to "do")
    private val task2keys = arrayListOf("サ", "ザ", "シ", "ジ", "ス", "ズ", "セ", "ゼ", "ソ", "ゾ", "タ", "ダ", "チ", "ツ", "テ", "デ", "ト", "ド")
    private val task2mapReversed = mapOf("sa" to "サ", "dza" to "ザ", "shi" to "シ", "ji" to "ジ", "su" to "ス", "dzu" to "ズ", "se" to "セ", "dze" to "ゼ", "so" to "ソ", "dzo" to "ゾ", "ta" to "タ", "da" to "ダ", "chi" to "チ", "tsu" to "ツ", "te" to "テ", "de" to "デ", "to" to "ト", "do" to "ド")
    private val task2keysReversed = arrayListOf("sa", "dza", "shi", "ji", "su", "dzu", "se", "dze", "so", "dzo", "ta", "da", "chi", "tsu", "te", "de", "to", "do")

    private val task3map = mapOf("な" to "na", "に" to "ni", "ぬ" to "nu", "ね" to "ne", "の" to "no", "は" to "ha", "ひ" to "hi", "ふ" to "fu", "へ" to "he", "ほ" to "ho", "ば" to "ba", "び" to "bi", "ぶ" to "bu", "べ" to "be", "ぼ" to "bo", "ぱ" to "pa", "ぴ" to "pi", "ぷ" to "pu", "ぺ" to "pe", "ぽ" to "po")
    private val task3keys = arrayListOf("な", "に", "ぬ", "ね", "の", "は", "ひ", "ふ", "へ", "ほ", "ば", "び", "ぶ", "べ", "ぼ", "ぱ", "ぴ", "ぷ", "ぺ", "ぽ")
    private val task3mapReversed = mapOf("na" to "な", "ni" to "に", "nu" to "ぬ", "ne" to "ね", "no" to "の", "ha" to "は", "hi" to "ひ", "fu" to "ふ", "he" to "へ", "ho" to "ほ", "ba" to "ば", "bi" to "び", "bu" to "ぶ", "be" to "べ", "bo" to "ぼ", "pa" to "ぱ", "pi" to "ぴ", "pu" to "ぷ", "pe" to "ぺ", "po" to "ぽ")
    private val task3keysReversed = arrayListOf("na", "ni", "nu", "ne", "no", "ha", "hi", "fu", "he", "ho", "ba", "bi", "bu", "be", "bo", "pa", "pi", "pu", "pe", "po")

    private val task4map = mapOf("ま" to "ma", "み" to "mi", "む" to "mu", "め" to "me", "も" to "mo", "や" to "ya", "ゆ" to "yu", "よ" to "yo", "ら" to "ra", "り" to "ri", "る" to "ru", "れ" to "re", "ろ" to "ro", "わ" to "wa", "を" to "wo", "ん" to "n")
    private val task4keys = arrayListOf("ま", "み", "む", "め", "も", "や", "ゆ", "よ", "ら", "り", "る", "れ", "ろ", "わ", "を", "ん")
    private val task4mapReversed = mapOf("ma" to "ま", "mi" to "み", "mu" to "む", "me" to "め", "mo" to "も", "ya" to "や", "yu" to "ゆ", "yo" to "よ", "ra" to "ら", "ri" to "り", "ru" to "る", "re" to "れ", "ro" to "ろ", "wa" to "わ", "wo" to "を", "n" to "ん")
    private val task4keysReversed = arrayListOf("ma", "mi", "mu", "me", "mo", "ya", "yu", "yo", "ra", "ri", "ru", "re", "ro", "wa", "wo", "n")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_katakana)
    }

    fun explainTask(view: View?) {
        findViewById<LinearLayout>(R.id.kTrain).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.kProgressbar).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.kNextButton).visibility = View.INVISIBLE
        findViewById<ScrollView>(R.id.kExplainTextOutside).visibility = View.VISIBLE
        findViewById<TextView>(R.id.kStartButton).visibility = View.VISIBLE
        task = when (view!!.id) {
            R.id.kTask1 -> 1
            R.id.kTask2 -> 2
            R.id.kTask3 -> 3
            R.id.kTask4 -> 4
            R.id.kTask5 -> 5
            else -> 0
        }
        findViewById<TextView>(R.id.kExplainText).text = when (view.id) {
            R.id.kTask1 -> "ア - a\n" +
                    "イ - i\n" +
                    "ウ - u\n" +
                    "エ - e\n" +
                    "オ - o\n" +
                    "カ/ガ - ka/ga\n" +
                    "キ/ギ - ki/gi\n" +
                    "ク/グ - ku/gu\n" +
                    "ケ/ゲ - ke/ge\n" +
                    "コ/ゴ - ko/go"
            R.id.kTask2 -> "サ/ザ - sa/dza\n" +
                    "シ/ジ - shi/ji\n" +
                    "ス/ズ - su/dzu\n" +
                    "セ/ゼ - se/dze\n" +
                    "ソ/ゾ - so/dzo\n" +
                    "タ/ダ - ta/da\n" +
                    "チ/[ヂ] - chi/[ji]\n" +
                    "ツ/[ヅ] - tsu/[dzu]\n" +
                    "テ/デ - te/de\n" +
                    "ト/ド - to/do"
            R.id.kTask3 -> "ナ - na\n" +
                    "ニ - ni\n" +
                    "ヌ - nu\n" +
                    "ネ - ne\n" +
                    "ノ - no\n" +
                    "ハ/バ/パ - ha/ba/pa\n" +
                    "ヒ/ビ/ピ - hi/bi/pi\n" +
                    "フ/ブ/プ - fu/bu/pu\n" +
                    "ヘ/ベ/ペ - he/be/pe\n" +
                    "ホ/ボ/ポ - ho/bo/po"
            R.id.kTask4 -> "マ - ma\n" +
                    "ミ - mi\n" +
                    "ム - mu\n" +
                    "メ - me\n" +
                    "モ - mo\n" +
                    "ヤ - ya\n" +
                    "ユ - yu\n" +
                    "ヨ - yo\n" +
                    "ラ - ra\n" +
                    "リ - ri\n" +
                    "ル - ru\n" +
                    "レ - re\n" +
                    "ロ - ro\n" +
                    "ワ - wa\n" +
                    "ヲ - o(wo)\n" +
                    "ン - n"
            R.id.kTask5 -> "empty"
            else -> ""
        }
    }

    fun startTrain(view: View?) {
        findViewById<LinearLayout>(R.id.kTrain).visibility = View.VISIBLE
        findViewById<TextView>(R.id.kProgressbar).visibility = View.VISIBLE
        findViewById<TextView>(R.id.kNextButton).visibility = View.VISIBLE
        findViewById<ScrollView>(R.id.kExplainTextOutside).visibility = View.INVISIBLE
        findViewById<TextView>(R.id.kStartButton).visibility = View.INVISIBLE
        counter = 0
        answered = true
        loadQuestion(null)
    }

    fun loadQuestion(view: View?) {
        if (!answered) return
        if (counter == 20) {
            findViewById<LinearLayout>(R.id.kTrain).visibility = View.INVISIBLE
            findViewById<TextView>(R.id.kProgressbar).visibility = View.INVISIBLE
            findViewById<TextView>(R.id.kNextButton).visibility = View.INVISIBLE
            findViewById<ScrollView>(R.id.kExplainTextOutside).visibility = View.VISIBLE
            findViewById<TextView>(R.id.kStartButton).visibility = View.VISIBLE
            return
        }
        findViewById<TextView>(R.id.kAnswer1).setTextColor(resources.getColor(R.color.dayColorText))
        findViewById<TextView>(R.id.kAnswer2).setTextColor(resources.getColor(R.color.dayColorText))
        findViewById<TextView>(R.id.kAnswer3).setTextColor(resources.getColor(R.color.dayColorText))
        findViewById<TextView>(R.id.kAnswer4).setTextColor(resources.getColor(R.color.dayColorText))
        answered = false
        when (task) {
            1 -> {
                if (counter < 10) {
                    question = task1keys[(Math.random() * 1000).toInt() % task1keys.size]
                    answer = task1map[question].toString()
                    findViewById<TextView>(R.id.kQuestion).text = question
                    findViewById<TextView>(R.id.kAnswer1).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                    findViewById<TextView>(R.id.kAnswer2).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                    findViewById<TextView>(R.id.kAnswer3).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                    findViewById<TextView>(R.id.kAnswer4).text = task1map[task1keys[(Math.random() * 1000).toInt() % task1keys.size]]
                    when ((Math.random() * 1000).toInt() % 4) {
                        0 -> findViewById<TextView>(R.id.kAnswer1).text = answer
                        1 -> findViewById<TextView>(R.id.kAnswer2).text = answer
                        2 -> findViewById<TextView>(R.id.kAnswer3).text = answer
                        3 -> findViewById<TextView>(R.id.kAnswer4).text = answer
                    }
                } else {
                    question = task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]
                    answer = task1mapReversed[question].toString()
                    findViewById<TextView>(R.id.kQuestion).text = question
                    findViewById<TextView>(R.id.kAnswer1).text = task1mapReversed[task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                    findViewById<TextView>(R.id.kAnswer2).text = task1mapReversed[task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                    findViewById<TextView>(R.id.kAnswer3).text = task1mapReversed[task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                    findViewById<TextView>(R.id.kAnswer4).text = task1mapReversed[task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]]
                    when ((Math.random() * 1000).toInt() % 4) {
                        0 -> findViewById<TextView>(R.id.kAnswer1).text = answer
                        1 -> findViewById<TextView>(R.id.kAnswer2).text = answer
                        2 -> findViewById<TextView>(R.id.kAnswer3).text = answer
                        3 -> findViewById<TextView>(R.id.kAnswer4).text = answer
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
                    findViewById<TextView>(R.id.kQuestion).text = question
                    findViewById<TextView>(R.id.kAnswer1).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                    findViewById<TextView>(R.id.kAnswer2).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                    findViewById<TextView>(R.id.kAnswer3).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                    findViewById<TextView>(R.id.kAnswer4).text = task1map.plus(task2map)[task1keys.plus(task2keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size)]]
                    when ((Math.random() * 1000).toInt() % 4) {
                        0 -> findViewById<TextView>(R.id.kAnswer1).text = answer
                        1 -> findViewById<TextView>(R.id.kAnswer2).text = answer
                        2 -> findViewById<TextView>(R.id.kAnswer3).text = answer
                        3 -> findViewById<TextView>(R.id.kAnswer4).text = answer
                    }
                } else {
                    if (Math.random() < 0.25) {
                        question = task1keysReversed[(Math.random() * 1000).toInt() % task1keysReversed.size]
                        answer = task1mapReversed[question].toString()
                    } else {
                        question = task2keysReversed[(Math.random() * 1000).toInt() % task2keysReversed.size]
                        answer = task2mapReversed[question].toString()
                    }
                    findViewById<TextView>(R.id.kQuestion).text = question
                    findViewById<TextView>(R.id.kAnswer1).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                    findViewById<TextView>(R.id.kAnswer2).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                    findViewById<TextView>(R.id.kAnswer3).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                    findViewById<TextView>(R.id.kAnswer4).text = task1mapReversed.plus(task2mapReversed)[task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]]
                    when ((Math.random() * 1000).toInt() % 4) {
                        0 -> findViewById<TextView>(R.id.kAnswer1).text = answer
                        1 -> findViewById<TextView>(R.id.kAnswer2).text = answer
                        2 -> findViewById<TextView>(R.id.kAnswer3).text = answer
                        3 -> findViewById<TextView>(R.id.kAnswer4).text = answer
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
                    findViewById<TextView>(R.id.kQuestion).text = question
                    findViewById<TextView>(R.id.kAnswer1).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                    findViewById<TextView>(R.id.kAnswer2).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                    findViewById<TextView>(R.id.kAnswer3).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                    findViewById<TextView>(R.id.kAnswer4).text = task1map.plus(task2map).plus(task3map)[task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]]
                    when ((Math.random() * 1000).toInt() % 4) {
                        0 -> findViewById<TextView>(R.id.kAnswer1).text = answer
                        1 -> findViewById<TextView>(R.id.kAnswer2).text = answer
                        2 -> findViewById<TextView>(R.id.kAnswer3).text = answer
                        3 -> findViewById<TextView>(R.id.kAnswer4).text = answer
                    }
                } else {
                    if (Math.random() < 0.25) {
                        question = task1keysReversed.plus(task2keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size)]
                        answer = task1mapReversed.plus(task2mapReversed)[question].toString()
                    } else {
                        question = task3keysReversed[(Math.random() * 1000).toInt() % task3keysReversed.size]
                        answer = task3mapReversed[question].toString()
                    }
                    findViewById<TextView>(R.id.kQuestion).text = question
                    findViewById<TextView>(R.id.kAnswer1).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                    findViewById<TextView>(R.id.kAnswer2).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                    findViewById<TextView>(R.id.kAnswer3).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                    findViewById<TextView>(R.id.kAnswer4).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]]
                    when ((Math.random() * 1000).toInt() % 4) {
                        0 -> findViewById<TextView>(R.id.kAnswer1).text = answer
                        1 -> findViewById<TextView>(R.id.kAnswer2).text = answer
                        2 -> findViewById<TextView>(R.id.kAnswer3).text = answer
                        3 -> findViewById<TextView>(R.id.kAnswer4).text = answer
                    }
                }
            }
            4 -> {
                if (counter < 10) {
                    if (Math.random() < 0.25) {
                        question = task1keys.plus(task2keys).plus(task3keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size)]
                        answer = task1map.plus(task2map).plus(task3map)[question].toString()
                    } else {
                        question = task4keys[(Math.random() * 1000).toInt() % task4keys.size]
                        answer = task4map[question].toString()
                    }
                    findViewById<TextView>(R.id.kQuestion).text = question
                    findViewById<TextView>(R.id.kAnswer1).text = task1map.plus(task2map).plus(task3map).plus(task4map)[task1keys.plus(task2keys).plus(task3keys).plus(task4keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size + task4keys.size)]]
                    findViewById<TextView>(R.id.kAnswer2).text = task1map.plus(task2map).plus(task3map).plus(task4map)[task1keys.plus(task2keys).plus(task3keys).plus(task4keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size + task4keys.size)]]
                    findViewById<TextView>(R.id.kAnswer3).text = task1map.plus(task2map).plus(task3map).plus(task4map)[task1keys.plus(task2keys).plus(task3keys).plus(task4keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size + task4keys.size)]]
                    findViewById<TextView>(R.id.kAnswer4).text = task1map.plus(task2map).plus(task3map).plus(task4map)[task1keys.plus(task2keys).plus(task3keys).plus(task4keys)[(Math.random() * 1000).toInt() % (task1keys.size + task2keys.size + task3keys.size + task4keys.size)]]
                    when ((Math.random() * 1000).toInt() % 4) {
                        0 -> findViewById<TextView>(R.id.kAnswer1).text = answer
                        1 -> findViewById<TextView>(R.id.kAnswer2).text = answer
                        2 -> findViewById<TextView>(R.id.kAnswer3).text = answer
                        3 -> findViewById<TextView>(R.id.kAnswer4).text = answer
                    }
                } else {
                    if (Math.random() < 0.25) {
                        question = task1keysReversed.plus(task2keysReversed).plus(task3keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size)]
                        answer = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed)[question].toString()
                    } else {
                        question = task4keysReversed[(Math.random() * 1000).toInt() % task4keysReversed.size]
                        answer = task4mapReversed[question].toString()
                    }
                    findViewById<TextView>(R.id.kQuestion).text = question
                    findViewById<TextView>(R.id.kAnswer1).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed).plus(task4mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed).plus(task4keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size + task4keysReversed.size)]]
                    findViewById<TextView>(R.id.kAnswer2).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed).plus(task4mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed).plus(task4keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size + task4keysReversed.size)]]
                    findViewById<TextView>(R.id.kAnswer3).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed).plus(task4mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed).plus(task4keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size + task4keysReversed.size)]]
                    findViewById<TextView>(R.id.kAnswer4).text = task1mapReversed.plus(task2mapReversed).plus(task3mapReversed).plus(task4mapReversed)[task1keysReversed.plus(task2keysReversed).plus(task3keysReversed).plus(task4keysReversed)[(Math.random() * 1000).toInt() % (task1keysReversed.size + task2keysReversed.size + task3keysReversed.size + task4keysReversed.size)]]
                    when ((Math.random() * 1000).toInt() % 4) {
                        0 -> findViewById<TextView>(R.id.kAnswer1).text = answer
                        1 -> findViewById<TextView>(R.id.kAnswer2).text = answer
                        2 -> findViewById<TextView>(R.id.kAnswer3).text = answer
                        3 -> findViewById<TextView>(R.id.kAnswer4).text = answer
                    }
                }
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
                findViewById<TextView>(R.id.kProgressbar).text = "$counter/20"
            }
            else {
                findViewById<TextView>(view.id).setTextColor(resources.getColor(R.color.falseAnswer))
                when (answer) {
                    findViewById<TextView>(R.id.kAnswer1).text -> findViewById<TextView>(R.id.kAnswer1).setTextColor(resources.getColor(R.color.trueAnswer))
                    findViewById<TextView>(R.id.kAnswer2).text -> findViewById<TextView>(R.id.kAnswer2).setTextColor(resources.getColor(R.color.trueAnswer))
                    findViewById<TextView>(R.id.kAnswer3).text -> findViewById<TextView>(R.id.kAnswer3).setTextColor(resources.getColor(R.color.trueAnswer))
                    findViewById<TextView>(R.id.kAnswer4).text -> findViewById<TextView>(R.id.kAnswer4).setTextColor(resources.getColor(R.color.trueAnswer))
                }
            }
        }
    }
}