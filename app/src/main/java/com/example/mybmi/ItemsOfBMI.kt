package com.example.mybmi

import java.util.*

//JSONのプロパティに対応するdata classを作る
//    classの定義にdataという修飾子を付けることで、hashCodeやequals, toStringなどプロパティ関連の各種メソッドを自動で作ってくれます
data class ItemsOfBMI(
    val id: String,
    val height: String,
    val weight: String,
    val bmi: String,
    val memo: String?
)
