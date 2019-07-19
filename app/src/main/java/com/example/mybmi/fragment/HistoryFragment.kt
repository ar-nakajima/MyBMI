package com.example.mybmi.fragment


import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybmi.Dao
import com.example.mybmi.R
import com.example.mybmi.entity.ItemsOfBMI
import com.example.mybmi.recyclerView.RecyclerState
import com.example.mybmi.recyclerView.RecyclerType
import com.example.mybmi.recyclerView.ViewAdapter
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
        Log.d("lifeCycle", "りれきひょうじするよ!!")

        val recyclerView = recyclerView
//       createList()メソッドで データを取得してadapterに渡してる！
        val adapter = ViewAdapter(createList())

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    private fun createList(): List<RecyclerState> {
        val pref = PreferenceManager.getDefaultSharedPreferences(activity)
//        val count = ViewAdapter(l！！！ist, listener =).getIntCo
//        val dataList = mutableListOf<ItemsOfBMI>()
        var dao = Dao(pref)
//        return dao.findAll().toList()
//        return dao.findAll()
        val items = dao.findAll()
//ここで全件データをGETする

        //ループを回す！
        val dataList = mutableListOf<RecyclerState>()

        //beforeMonthの初期化
         var beforeMonth: String? = null

        items.forEach {

            //    fun splitMonth(): Int {
            //        return this.id.substring(4, 6).toInt()
            //    }
            //
            //    fun splitDate() : Int {
            //        return  this.id.substring(6,8).toInt()
            //
            //    }

            /**　if (月初めなら)｛
             *
             *  //セクションを表示する
             * sectionData = RecyclerState(RecyclerType.SECTION, it)
             * dataList.add(sectionData)
             * 　
             * 　beforeMonth = ItemsOfBMI.id(月だけ)
             *
             * ｝
             * */
            //月初め　＝　月部分がソートされた前のデータと一致してない
            // 前のデータ→何月か→idからとる
            // 前の月　！＝　（比較したいデータ）今回のid

            if (beforeMonth != it.id.substring(4,6)){
                //セクションを表示する
                var sectionData = RecyclerState(RecyclerType.SECTION, it)
                dataList.add(sectionData)

                beforeMonth = it.id.substring(4,6)

            }


            //ＢＯＤＹを表示する
            //RecyclerStateに引数RecyclerType,ItemsOfBMIが必要
            var bodyData = RecyclerState(RecyclerType.BODY, it)
            dataList.add(bodyData)


            //if メモが入ってたら
        }


        return dataList
    }


//    fun onClickRow(tappedView: View, rowModel: ItemsOfBMI) {
//        Snackbar.make(tappedView, "Replace with your own action tapped ${rowModel.height}", Snackbar.LENGTH_LONG)
//            .setAction("Action", null).show()
//    }


    //    テスト＊＊＊＊＊＊＊＊＊
    //    このフラグメント内でデータを取得して、adapterにセットして、holderに保持させるはず
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
