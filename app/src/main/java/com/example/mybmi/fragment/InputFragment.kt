package com.example.mybmi.fragment


import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mybmi.Dao
import com.example.mybmi.R
import com.example.mybmi.entity.ItemsOfBMI
//import com.example.mybmi.calcBmi
import kotlinx.android.synthetic.main.fragment_input.*
import java.lang.String
import java.text.SimpleDateFormat
import java.util.*

class InputFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_input, container, false)
        val pref = PreferenceManager.getDefaultSharedPreferences(activity)
/*
/** フラグメント内では、直接（idで）Viewが取得できないためfindViewByIdを使用する */
val button = view.findViewById<Button>(R.id.使用したいViewのid)
button?.setOnClickListener {
  //ボタン押下時処理
}
 */
        // 計算ボタン押下時＊＊＊＊
        val calcButton = view?.findViewById<Button>(R.id.calcButton)

        calcButton?.setOnClickListener {
            /* 日付の取得 */
            val sdf = SimpleDateFormat("yyyyMMdd")
            val id = sdf.format(Date()).toString()
            // 入力欄の文字列取得
            var height = height.text.toString()
            var weight = weight.text.toString()
            var memo = memo.text.toString()

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
//                dialog.show(supportFragmentManager, "tag")
            }
            // 計算
            var bmi = String.format("%.1f", calcBmi(height, weight))
            // 結果表示
            text_BMI_view.setText("$bmi")
            //データ保存
            val items = ItemsOfBMI(id, height, weight, bmi, memo)
            var dao = Dao(pref)
            if (dao.save(items) === false) {
                dao.update(id, items)
            } else {
                dao.save(items)
            }
            dao.flush()
//            return@setOnClickListener
        }


        // 保存ボタン押下時＊＊＊＊＊
        val saveButton = view?.findViewById<View>(R.id.saveButton)
        saveButton?.setOnClickListener {
            /* 日付の取得 */
            val sdf = SimpleDateFormat("yyyyMMdd")
            val id = sdf.format(Date()).toString()
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
//                dialog.show(supportFragmentManager, "tag")
            }

            //データ保存
            val items = ItemsOfBMI(id, height, weight, bmi, memo)
            var dao = Dao(pref)
            if (dao.save(items) === false) {
                dao.update(id, items)
            } else {
                dao.save(items)
            }
            dao.flush()
//            return@setOnClickListener
        }

//        リターンすればよい！？？
        return view
    }

    //    BMI計算メソッド
    private fun calcBmi(height: kotlin.String, weight: kotlin.String): Float {
        val height: Float = height.toFloat()
        val weight: Float = weight.toFloat()
        val bmi: Float = weight / (height * height) * 10000
        return bmi
    }

}
