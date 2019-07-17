package com.example.mybmi.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybmi.entity.ItemsOfBMI
import com.example.mybmi.R
import com.example.mybmi.ViewHolder
import androidx.recyclerview.widget.RecyclerView.ViewHolder as ViewHolder1

//ViewHolderを生成したりに生成したViewHolderにViewModelをセットしたりする。
//一つのListに対して一つ存在する。

class ViewAdapter(private val list: List<ItemsOfBMI>, private val listener: ListListener) : RecyclerView.Adapter<ViewHolder>() {

//    テーブルのセルを作成
//    セルが必要になる度に呼び出される
//    list_itemのviewを作ってViewHolderを生成しreturn
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("Adapter", "onCreateViewHolder")
//    fromメソッドでlayoutInflaterクラスのインスタンスを取得
//    inflaterメソッドでビューにレイアウトXMLを適用し、指定されたcontxtからインスタンスを取得
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.items_history, parent, false)
        return ViewHolder(rowView)
    }

//   値をセット（viewHolderで保持しているビューに対して実際に表示するコンテンツの設定をする
//    指定された一にデータを表示するために呼ばれる
//   （ positionをListのindexとして、ViewHolderに）
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolder")
        holder.heightView.text = list[position].height
        holder.weightView.text = list[position].weight
        holder.bmiView.text = list[position].bmi
        holder.itemView.setOnClickListener {
            listener.onClickRow(it, list[position])
        }
    }

    override fun getItemCount(): Int {
//        listのサイズを返してあげる必要がある
        Log.d("Adapter", "getItemCount")
        return list.size
    }

    interface ListListener {
        fun onClickRow(tappedView: View, rowModel: ItemsOfBMI)
    }
}