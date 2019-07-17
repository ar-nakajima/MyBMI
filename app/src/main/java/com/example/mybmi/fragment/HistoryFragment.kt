package com.example.mybmi.fragment


import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybmi.Dao
import com.example.mybmi.entity.ItemsOfBMI
import com.example.mybmi.R
import com.example.mybmi.entity.items
import com.example.mybmi.recyclerView.ViewAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    //フラグメントが初めてUIを描画するタイミングで呼びだされる
    override fun onCreateView(
        /*onCreateView()は,
このFragmentのメインコンテンツとなるViewを生成して返す必要があるライフサイクルイベント
ここではViewを生成して返すだけにとどめ、Viewの初期化はonViewCreated()で。
 */
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // レイアウトXMLからビューを作成する
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    //    このFragmentのViewが生成された後に呼び出されます。Viewの初期化とFragmentの状態の復元はここで行うことを推奨します。
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("lifeCycle", "りれきひょうじ")

        val recyclerView = recyclerView
//       createList()メソッドで データを取得してadapterに渡してる！
        val adapter = ViewAdapter(createList(), object : ViewAdapter.ListListener {
            override fun onClickRow(tappedView: View, items: ItemsOfBMI) {
                this@HistoryFragment.onClickRow(tappedView, items)
            }
        }
        )
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }


    private fun createList(): List<ItemsOfBMI> {
        val pref = PreferenceManager.getDefaultSharedPreferences(activity)
//        val count = ViewAdapter(l！！！ist, listener =).getIntCo
//        val dataList = mutableListOf<ItemsOfBMI>()
        var dao = Dao(pref)
//        return dao.findAll().toList()
        return dao.findAll()
    }


    fun onClickRow(tappedView: View, rowModel: ItemsOfBMI) {
        Snackbar.make(tappedView, "Replace with your own action tapped ${rowModel.height}", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }





    //    テスト＊＊＊＊＊＊＊＊＊
    //    このフラグメント内でデータを取得して、adapterにセットして、holderに保持させて？
    private fun createDataList(): List<ItemsOfBMI> {

        val dataList = mutableListOf<ItemsOfBMI>()
        for (i in 0..5) {
            var data: ItemsOfBMI = ItemsOfBMI().also {
                it.height = "" + i + "こめ"
                it.weight = "" + i + "こめ"
            }
            dataList.add(data)
        }
        return dataList
    }
//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊テスト
}
