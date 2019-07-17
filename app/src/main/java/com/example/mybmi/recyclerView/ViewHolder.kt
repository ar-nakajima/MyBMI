package com.example.mybmi


//import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
/*
ほぼ初めてやったデバッグでやっと流れが分かりました。。。。。。。
historyフラグメントでデータを取得しきってから、adapterに接続して、データセルを作成するごとに、holdenに保持させる
アイテムのViewを保持
セルに使用するビューを保持するためのもの
セル：表示したい項目をグループ化した単位
 */
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val dateView: TextView = itemView.findViewById(R.id.dateView)
    val heightView: TextView = itemView.findViewById(R.id.heightView)
    val weightView: TextView = itemView.findViewById(R.id.weightView)
    val bmiView: TextView = itemView.findViewById(R.id.bmiView)
    val memoView: TextView = itemView.findViewById(R.id.memoView)
}
