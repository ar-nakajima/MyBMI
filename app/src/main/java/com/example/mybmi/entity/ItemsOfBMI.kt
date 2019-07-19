package com.example.mybmi.entity

import java.util.*
//リストのアイテム一つを表すクラス。
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
)


