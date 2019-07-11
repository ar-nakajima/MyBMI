package com.example.mybmi

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.String.format
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

////        ヘッダーフラグメント
//        val headerFragment = headerFragment as? HeaderFragment
//        headerFragment?.setHeader("入力")


//日付の取得
        val sdf = SimpleDateFormat("yyyyMMdd")
        val id = sdf.format(Date()).toString()

        val pref = PreferenceManager.getDefaultSharedPreferences(this)

        // 計算ボタン押下時＊＊＊＊
        button_exec_calc.setOnClickListener {

            // 入力欄の文字列取得
            var height = height.text.toString()
            var weight = weight.text.toString()
//            var memo = memo.text.toString()

            // 入力値が不適切な場合
            if (height.toFloat() > 500F || weight.toFloat() > 500F) {
                val tag = "alert"
                var dialog = ErrorDialog()
                dialog.title = "ERROR"
                dialog.msg = "適正値を入力してね"
                dialog.okText = "OK"
                dialog.onOkClickListener = DialogInterface.OnClickListener { dialog, id ->
                    Log.d(tag, "ok clicked")
                }
                dialog.show(supportFragmentManager, "tag")
            }
            // 計算
            var bmi = format("%.1f", calcBmi(height, weight))
            // 結果表示
            text_BMI_view.setText("$bmi")

//            onSaveTapped()
//            val items = ItemsOfBMI(id, height, weight, bmi, memo)
////            var dao = Dao(pref)
////            dao.save(items)
////            dao.flush()
        }

        // 保存ボタン押下時＊＊＊＊＊
        save.setOnClickListener {
            // 入力欄の文字列取得
            var height = height.text.toString()
            var weight = weight.text.toString()
            var bmi = text_BMI_view.text.toString()
            var memo = memo.text.toString()

            // BMIが未入力の場合
            if (bmi == null || bmi.isEmpty()) {
                val tag = "alert"
                var dialog = ErrorDialog()
                dialog.title = "ERROR"
                dialog.msg = "BMIが未入力だよ"
                dialog.okText = "OK"
                dialog.onOkClickListener = DialogInterface.OnClickListener { dialog, id ->
                    Log.d(tag, "ok clicked")
                }
                dialog.show(supportFragmentManager, "tag")
            }

            //データ保存
            val items = ItemsOfBMI(id, height, weight, bmi, memo)
            var dao = Dao(pref)

            if (dao.save(items) === false) {
                //false日付が一致してる →上書き
                dao.update(id, items)
            } else {
                //true 日付が一致してない→　保存
                dao.save(items)
            }
            //flushで保存確定
            dao.flush()


////            履歴ボタン押下時
//            history.setOnClickListener {
//                val intent  = Intent(this, )
//
//            }

        }
    }
}

//***************************************************
//    // 共有プリファレンスにデータ保存するメソッド
//    fun onSaveTapped() {
//        val pref = PreferenceManager.getDefaultSharedPreferences(this)
//        pref.edit {
//            putString("HEIGHT", height.text.toString())
//            putString("WEIGHT", weight.text.toString())
//            putString("BMI", text_BMI_view.text.toString())
//        }
//    }

//    BMI計算メソッド
private fun calcBmi(height: String, weight: String): Float {
    val height: Float = height.toFloat()
    val weight: Float = weight.toFloat()
    val bmi: Float = weight / (height * height) * 10000
    return bmi
}


//***************************************************
//
//    fun main(args: Array<String>) {
////        jsonを用意する
//        val json = """
//        {
//            "name" : "beatdjam",
//            "age" : "28",
//            "url" : "http://beatdjam.hatenablog.com/"
//        }
//    """
//
//        // パースするJSON
//        val json = "{ \"color\": \"green\", \"kana\": \"みどり\", \"code\": { \"rgba\": [0,255,0,1], \"hex\": \"#0F0\" } }"
//
////data classを型パラメータに指定して3のメソッドを呼び出す
//
////        ここにshared~をつかう？？
//        val result = parseJSON<Person>(json)
//        println(result.name)
//        println(result.age)
//        println(result.url)
//    }
//    println(color)
//    println("色(かな)：${color.kana}")
//    println("RGBAコードのG値：${color.code.rgba[1]}")
//
//    //JSONをdata classへparseするメソッドを作る
//    inline fun <reified T : Any> parseJSON(json: String): T {
//        return jacksonObjectMapper().readValue(json, T::class.java)
//    }


//        //    ＊＊＊＊＊＊＊＊保存処理＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
////       ① アクティビティのonCreate が呼ばれるタイミングでSharedPreferencesのインスタンスを
////        取得したあと、共有プリファレンスに保存されている値を取得　
////        val pref = PreferenceManager.getDefaultSharedPreferences(this)
//        val heightP = pref.getString("HEIGHT", " ")
//        val weightP = pref.getString("WEIGHT", " ")
//        val bmiP = pref.getString("BMI", " ")
//
////        ②↑のをeditTextViewに表示
//        height.setText(heightP)
//        weight.setText(weightP)
//        text_BMI_view.setText(bmiP)

