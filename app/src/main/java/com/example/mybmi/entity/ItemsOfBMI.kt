package com.example.mybmi.entity

import java.util.*
//リストのアイテム一つを表すクラス。
//JSONのプロパティに対応するdata classを作る
//    classの定義にdataという修飾子を付けることで、hashCodeやequals, toStringなどプロパティ関連の各種メソッドを自動で作ってくれます
data class ItemsOfBMI(
//    日付
    val id: String = "",
//    身長
    var height: String = "",
//    体重
    var weight: String= "",
//  BMI
    val bmi: String= "",
//    メモ
    val memo: String?= ""
) {
    fun getMonth(): Int {
        return this.id.substring(4, 6).toInt()
    }

    fun getDate() : Int {
        return  this.id.substring(6,8).toInt()

    }
}


