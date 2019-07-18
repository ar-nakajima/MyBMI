package com.example.mybmi.recyclerView

import com.example.mybmi.entity.ItemsOfBMI

//    RecyclerAdapterでの判定で使用するenum
//    intは 数値型を全部扱えるけど、enumは自分で決めた定数しか扱えない制約を
//    つけられるので enumをつかうのは一般的ですね。
//    enumで扱う定数を元にadapterで判断？


// RecyclerViewの表示タイプを保存するクラス
data class RecyclerState(val type: RecyclerType , val item: ItemsOfBMI)

enum class RecyclerType (val int: Int){

    SECTION(0),
    BODY(1);

    companion object {
        // Intからenumへの変換？？どゆこと
        fun fromInt(int: Int): RecyclerType{
            return values().firstOrNull { it.int == int }
                ?: RecyclerType.BODY
        }
    }
}