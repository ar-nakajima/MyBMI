package com.example.mybmi.recyclerView

import com.example.mybmi.entity.RecyclerType

class RecyclerState {
/*
Adapterのデータクラス
RecyclerAdapterでのヘッダ、フッタ、セクション、ボディ部分の判定もtypeにて行う
今回はカスタムビューでも使用する
 */
    constructor(type: RecyclerType, text: String): this(){
        this.type = type
        this.text = text
    }

    // RcyclerAdapterにて追加するレコードのタイプ
    var type: RecyclerType = RecyclerType.BODY
    var text: String = ""
}