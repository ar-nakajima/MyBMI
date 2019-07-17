package com.example.mybmi

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.mybmi.entity.ItemsOfBMI
import com.example.mybmi.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_footer.*
import kotlinx.android.synthetic.main.fragment_input.*
import java.lang.String
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = PreferenceManager.getDefaultSharedPreferences(this)

//        初期表示
        val header = headerFragmet as? HeaderFragment
        header?.setHeaderFragment("入力")
        var inputFragment = InputFragment()
        replaceFragment(inputFragment)


        // 入力ボタン押下時＊＊＊
        addButton.setOnClickListener {
            header?.setHeaderFragment("入力")
            val inputFragment = InputFragment()
            replaceFragment(inputFragment)
        }

//        履歴ボタン押下時＊＊＊
        historyBuntton.setOnClickListener {
            header?.setHeaderFragment("履歴")
            val historyFragment = HistoryFragment()
            replaceFragment(historyFragment)
        }


//        // 計算ボタン押下時＊＊＊＊
//        calcButton.setOnClickListener {
//            /* 日付の取得 */
//            val sdf = SimpleDateFormat("yyyyMMdd")
//            val id = sdf.format(Date()).toString()
//            // 入力欄の文字列取得
//            var height = height.text.toString()
//            var weight = weight.text.toString()
//            var memo = memo.text.toString()
//
//            // 入力値が不適切な場合
//            if (height.toFloat() > 500F || weight.toFloat() > 500F) {
//                val tag = "alert"
//                var dialog = ErrorDialog()
//                dialog.title = "ERROR"
//                dialog.msg = "適正値を入力してね"
//                dialog.okText = "OK"
//                dialog.onOkClickListener = DialogInterface.OnClickListener { dialog, id ->
//                    Log.d(tag, "ok clicked")
//                }
////                dialog.show(supportFragmentManager, "tag")
//            }
//            // 計算
//            var bmi = String.format("%.1f", calcBmi(height, weight))
//            // 結果表示
//            text_BMI_view.setText("$bmi")
//            //データ保存
//            val items = ItemsOfBMI(id, height, weight, bmi, memo)
//            var dao = Dao(pref)
//            if (dao.save(items) === false) {
//                dao.update(id, items)
//            } else {
//                dao.save(items)
//            }
//            dao.flush()
//        }
//
//        // 保存ボタン押下時＊＊＊
//        saveButton.setOnClickListener {
//            /* 日付の取得 */
//            val sdf = SimpleDateFormat("yyyyMMdd")
//            val id = sdf.format(Date()).toString()
//            // 入力欄の文字列取得
//            var height = height.text.toString()
//            var weight = weight.text.toString()
//            var bmi = text_BMI_view.text.toString()
//            var memo = memo.text.toString()
//
//            // BMIが未入力の場合
//            if (bmi == null || bmi.isEmpty()) {
//                val tag = "alert"
//                var dialog = ErrorDialog()
//                dialog.title = "ERROR"
//                dialog.msg = "BMIが未入力だよ"
//                dialog.okText = "OK"
//                dialog.onOkClickListener = DialogInterface.OnClickListener { dialog, id ->
//                    Log.d(tag, "ok clicked")
//                }
////                dialog.show(supportFragmentManager, "tag")
//            }
//
//            //データ保存
//            val items = ItemsOfBMI(id, height, weight, bmi, memo)
//            var dao = Dao(pref)
//            if (dao.save(items) === false) {
//                dao.update(id, items)
//            } else {
//                dao.save(items)
//            }
//            dao.flush()
//
//        }
//
//
    }

//    //******************************
    //    BMI計算メソッド
    private fun calcBmi(height: kotlin.String, weight: kotlin.String): Float {
        val height: Float = height.toFloat()
        val weight: Float = weight.toFloat()
        val bmi: Float = weight / (height * height) * 10000
        return bmi
    }

//    フラグメントを切り替えるメソッド
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = this.getSupportFragmentManager()
        // FragmentManagerからfragmentTransactionを作成
        val fragmentTransaction = fragmentManager.beginTransaction()
        // Fragmentを組み込む
//        第1引数はFragmentが入るViewのID、第2引数は挿入するFragment
        fragmentTransaction.replace(R.id.container, fragment).addToBackStack(null).commit()

    }


    //    入力メソッド
    private fun addButtonTapped() {


    }

    //  履歴メソッド
    private fun historyBunttonTapped() {


    }


//


    //        //入力ボタン
//        addButton.setOnClickListener {
//            headerFragment?.setHeaderFragment("入力")
//            fragment = InputFragment()
//            replaceFragment(fragment)
//        }
//
//        // 履歴ボタン
//        historyBuntton.setOnClickListener {
//            headerFragment?.setHeaderFragment("履歴")
//            val fragment = HistoryFragment()
//            replaceFragment(fragment)


//        }
}













