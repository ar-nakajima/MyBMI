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
このFragmentのメインコンテンツとなるViewを生成して返す必要があるライフサイクルイベントです。
ここではViewを生成して返すだけにとどめ、Viewの初期化はonViewCreated()で行います。
このメソッドにもsavedInstanceStateでFragmentの状態が渡されてきますが、ここでも復元は行いません
 */
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // レイアウトXMLからビューを作成する
        return inflater.inflate(R.layout.fragment_history, container, false)
    }
    //以下https://qiita.com/saiki-ii/items/78ed73134784f3e5db7e引用

    //    このFragmentのViewが生成された後に呼び出されます。Viewの初期化とFragmentの状態の復元はここで行うことを推奨します。
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("lifeCycle", "りれきひょうじ")

        val recyclerView = recyclerView
        val adapter = ViewAdapter(createDataList(), object : ViewAdapter.ListListener {
            override fun onClickRow(tappedView: View, items: ItemsOfBMI) {
                this@HistoryFragment.onClickRow(tappedView, items)
            }
        })

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

//    このフラグメント内でデータを取得して、adapterにセットして、holderに保持させて？
//    データgetってそもそも、沢野さんのDAOを利用すればいけるのでは？json形式だし、sharedのなかだし。
//    テストメソッド
    private fun createDataList(): List<ItemsOfBMI> {
//    とりあえずこれおいて、、

        val dataList = mutableListOf<ItemsOfBMI>()
//    val count = ViewAdapter()のgetIntCountを使うのでは？
//    alsoってなに？
        for (i in 0..49) {
            var data: ItemsOfBMI = ItemsOfBMI().also {

                it.height = "身長" + i + "こめだよ"
                it.weight = "体重" + i + "個目だよ"

            }
            dataList.add(data)
        }
        return dataList
    }

    private fun dataList(): List<ItemsOfBMI> {
        val pref = PreferenceManager.getDefaultSharedPreferences(activity)

        var dao = Dao(pref)

        return   dao.findAll().toList()
    }

    fun onClickRow(tappedView: View, rowModel: ItemsOfBMI) {
        Snackbar.make(tappedView, "Replace with your own action tapped ${rowModel.height}", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

}
