package com.example.mybmi.entity

import java.util.*
//リストのアイテム一つを表すクラス。
//JSONのプロパティに対応するdata classを作る
//    classの定義にdataという修飾子を付けることで、hashCodeやequals, toStringなどプロパティ関連の各種メソッドを自動で作ってくれます
data class ItemsOfBMI(
    val id: String = "",
    var height: String = "",
    var weight: String= "",
    val bmi: String= "",
    val memo: String?= ""
)


//＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊＊
//RecyclerAdapterでの判定で使用するenum
enum class RecyclerType(val int: Int){
    HEADER(0),
    FOOTER(1),
    SECTION(2),
    BODY(3);

    companion object {
        // Intからenumへの変換
        fun fromInt(int: Int): RecyclerType{
            return values().firstOrNull { it.int == int }
                ?: RecyclerType.BODY
        }
    }
}
//1. リストで表示する Enum クラスを作成する
enum class ListItem(val title: String) {
    HeaderFruits(title = "果物"),
    Apple(title = "りんご"),
    Orange(title = "オレンジ"),
    HeaderVegetables(title = "野菜"),
    Carrot(title = "人参"),
    Onion(title = "玉ねぎ"),
    HeaderDrinks(title = "飲み物"),
    Milk(title = "牛乳"),
    Water(title = "水")
}