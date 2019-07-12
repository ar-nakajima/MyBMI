package com.example.mybmi


//import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
/*
アイテムのViewを保持します。
アイテムと対になって存在するイメージ。（実際はリサイクルされているようなので同じ数ではない?）
よく考えると名前そのまんまですね。
 */
class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val heightView: TextView = itemView.findViewById(R.id.row_height)
    val weightView: TextView = itemView.findViewById(R.id.row_weight)
    val bmiView: TextView = itemView.findViewById(R.id.row_bmi)
}
