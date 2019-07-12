package com.example.mybmi.fragment


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mybmi.entity.ItemsOfBMI
import com.example.mybmi.R
//import com.example.mybmi.createDataList
import com.example.mybmi.view.ViewAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }
    //以下https://qiita.com/saiki-ii/items/78ed73134784f3e5db7e引用
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("lifeCycle", "りれきひょうじ")

        val recyclerView = recyclerView
        val adapter = ViewAdapter(createDataList(), object : ViewAdapter.ListListener {
            override fun onClickRow(tappedView: View, rowModel: ItemsOfBMI) {
                this@HistoryFragment.onClickRow(tappedView, rowModel)
            }
        })

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
    }

    private fun createDataList(): List<ItemsOfBMI> {

        val dataList = mutableListOf<ItemsOfBMI>()
        for (i in 0..49) {
            val data: ItemsOfBMI = ItemsOfBMI().also {
                it.height = "身長" + i + "こめだよ"
                it.weight = "体重" + i + "個目だよ"
            }
            dataList.add(data)
        }
        return dataList
    }


    fun onClickRow(tappedView: View, rowModel: ItemsOfBMI) {
        Snackbar.make(tappedView, "Replace with your own action tapped ${rowModel.height}", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show()
    }

}
