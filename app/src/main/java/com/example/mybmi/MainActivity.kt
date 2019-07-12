package com.example.mybmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mybmi.fragment.HeaderFragment
import com.example.mybmi.fragment.HistoryFragment
import com.example.mybmi.fragment.InputFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_footer.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // FragmentManagerからFragmentTransactionを作成
        val fragmentManager = this.getSupportFragmentManager()
        val transaction = fragmentManager.beginTransaction()

//        初期表示
        val headerFragment = headerFragment as? HeaderFragment
        headerFragment?.setHeaderFragment("入力")
        var fragment = InputFragment()

//       Fragmentを組み込む。 第1引数はFragmentが入るViewのID、第2引数は挿入するFragmentです。
        transaction.replace(R.id.inputFragment, fragment).commit()

//        replaceFragment(fragment)


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


//    private fun replaceFragment(fragment: Fragment) {
//        // FragmentManagerからFragmentTransactionを作成
//        val fragmentManager = this.getSupportFragmentManager()
//        val transaction = fragmentManager.beginTransaction()
//        // Fragmentを組み込む
////        第1引数はFragmentが入るViewのID、第2引数は挿入するFragmentです。
//        transaction.replace(R.id.container, fragment).commit()
//        // 上記の変更を反映する
////        transaction.commit()
//    }





}







