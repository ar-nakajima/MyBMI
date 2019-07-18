package com.example.mybmi


//import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
/*
historyフラグメントでデータを取得しきってから、adapterに接続して、
データセルを作成するごとに、holdenに保持させる
セルに使用するビューを保持するためのもの
 */

//BODY
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//    レイアウトを操作するクラス
    val dateView: TextView = itemView.findViewById(R.id.dateView)
    val heightView: TextView = itemView.findViewById(R.id.heightView)
    val weightView: TextView = itemView.findViewById(R.id.weightView)
    val bmiView: TextView = itemView.findViewById(R.id.bmiView)
    val memoView: TextView = itemView.findViewById(R.id.memoView)
}

//SECTION用
class SectionViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

    val monthView: TextView = itemView.findViewById(R.id.monthView)

}
