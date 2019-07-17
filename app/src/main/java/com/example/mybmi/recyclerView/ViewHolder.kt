package com.example.mybmi


//import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
/*
アイテムのViewを保持
セルに使用するビューを保持するためのもの
セル：表示したい項目をグループ化した単位
 */
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val heightView: TextView = itemView.findViewById(R.id.heightView)
    val weightView: TextView = itemView.findViewById(R.id.weightView)
    val bmiView: TextView = itemView.findViewById(R.id.bmiView)
}
