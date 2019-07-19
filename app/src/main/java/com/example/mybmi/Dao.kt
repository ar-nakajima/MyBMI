package com.example.mybmi

import android.content.SharedPreferences
import android.util.Log
import com.example.mybmi.entity.ItemsOfBMI
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

/*
SharedPreferencesとは情報をAndroidデバイス内に保存する仕組みです.
、キー・バリュー形式で1対1で保存されるだけ.保存できるデータの型はString, int, float, long, boolean, Set<String>です。
1.SharedPreferenceのインスタンスを生成します
SharedPreferences pr = getSharedPreferences("DATA", Context.MODE_PRIVATE);
1つめの引数は生成する設定ファイルのキー（つまりファイル名のようなもの）です。これはアプリ内で固有のものである必要があります。
2つめの引数はこの設定ファイルが自分のアプリ内のみからアクセス可能か、他のアプリからも読み書きが出来るかを設定する定数です

2. データを書き込みます
SharedPreferences.Editor editor = preferences.edit();
editor.putInt("HEIGHT", 100);
editor.putBoolean("MARRIED", true);
editor.apply();

3. データを読み込みます
SharedPreferences preferences = getSharedPreferences("DATA", Context.MODE_PRIVATE);
int height = preferences.getInt("HEIGHT", 150);
1つめの引数は1. で指定した設定ファイルのキー、
2つめの引数はもしそのキーに値が存在しないときに代入する初期値です。

一般的にはSharedPreferencesにアクセスを行うクラスを作ったりすると思います。
沢野さんにおしえていただきました！！！！！！！！！！！
 */

const val KEY_ITEMS_LIST = "KEY_ITEMS_LIST"

class Dao(sharedPreferences: SharedPreferences) {
    private var itemsList = mutableSetOf<ItemsOfBMI>()
    private var pref = sharedPreferences

    init {
        val json = pref.getStringSet(KEY_ITEMS_LIST, null)
//letで対象オブジェクトをitで参照でき、null安全に。
        json?.let {
            itemsList = jsonSetToObject(json)
        }
    }

    fun save(item: ItemsOfBMI): Boolean {
        // Idが一致するなら同一オブジェクトが存在するため保存しない
        itemsList.forEach {
            if (it?.id == item.id) return false
        }
        // 同一IDが存在しない場合、新規保存
        itemsList.add(item)
        return true
    }

//     fun findAll(): MutableSet<ItemsOfBMI> {
//        return this.itemsList.sortedBy { it.id }
//    }

    //Kotlin の List や Set はあくまで「読み取り専用」であり、「イミュータブル」ではないけど、取得したらあとは読み取るだけだから
//    問題ないよね！
    fun findAll(): List<ItemsOfBMI> {
        return this.itemsList.toList().sortedBy { it.id }
//           itemsList = itemsList.sorted(it.id)
    }

    fun findById(id: String): ItemsOfBMI? {
        for (item in itemsList) {
            if (item?.id == id) return item
        }
        // 対象のIDがなければNULLを返却する
        return null
    }

    fun update(id: String, item: ItemsOfBMI): Boolean {
        //
        itemsList.forEach {
            if (it?.id == id) {
                // Setの中にある同一要素を削除する
                itemsList.remove(it)
                // 引数のオブジェクトを追加する
                itemsList.add(item)
                // 更新が完了したら true
                return true
            }
        }
        // 更新対象がなかったら false
        return false
    }

    //    日付をもらって
    fun delete(id: String): Boolean {
//    itemsOfBmiデータをとってきて、、
        itemsList.forEach {
            if (it?.id == id) {
                // Setの中にある同一要素を削除する
                itemsList.remove(it)
                return true
            }
        }
//    その日のデータがなかったら、削除しない
        return false
    }

    //    確定
    fun flush() {
        val editor = this.pref.edit()
        // 共有プリファレンスに現在の状態を保存する。
        editor.putStringSet(KEY_ITEMS_LIST, convertJsonSet())
            .apply()
    }

    /**
     * SharedPreferenceから取得したMutableSet<String>を、オブジェクトとして使えるようにする
     * @param {MutableSet<String>} SharedPreferenceから取得した値
     * @return {MutableSet<ItemsOfBMI?>} jsonなStringをItemsOfBMIに変換した値が返却される
     */
    private fun jsonSetToObject(items: MutableSet<String>): MutableSet<ItemsOfBMI> {
        // 共有プリファレンスから取得した値がNULLの場合は空のMutableSetを返却する
        if (items == null) return mutableSetOf()

        val itemsList = mutableSetOf<ItemsOfBMI>()
        items.forEach {

            it?.let {
                itemsList.add(parseJSON(it))
            }
        }

        // 全ての値が未登録の場合はNULLが返却される。
        return itemsList
    }

    /**
     * itemsListをSharedPreferenceのputStringSet()で使用できるようにコンバートする
     */
    private fun convertJsonSet(): MutableSet<String> {
        var list = mutableSetOf<String>()
        val mapper = ObjectMapper()

        this.itemsList.forEach {
            val jsonString = mapper.writeValueAsString(it)
            list.add(jsonString)

            Log.d("返還後JSON", "$jsonString")
        }

        return list
    }

    /**
     * JSON文字列をオブジェクトに変換する
     * @param {json} JSON文字列
     * @return JSONをパースしたオブジェクトが返却される
     */
    private inline fun <reified T : Any> parseJSON(json: String): T {
        return jacksonObjectMapper().readValue(json, T::class.java)
    }
}


/*
 editor.putString(KEY_ITEMS_LIST ,"test message").apply()
 editor.putString(KEY_ITEMS_LIST ,"test message 2").apply()
 editor.putStringSet(KEY_ITEMS_LIST ,MutableSet<String>) mutableSetOf(json)
 {id: "20190709" ,height: "123" ,weight: "34" ,bmi:"22"} => Items
 class Items(id ,height ,weight ,bmi) {} => json

 MutableSet<Items> list
 list.add(Items(20190709 ,123.0 ,34.0 ,34.9)

 var setObject = mutableSetOf<String>()
 list.forEach {
    setObject.add(parseJson(it))
 }

 editor.putStringSet(KEY ,setObject)

======================

class Item(id ,height ,weight ,bmi)
// {id: "" ,height: "" ,weight: "" ,bmi: ""}
// 保存するとき
const val KEY_LIST = "KEY_LIST"
var keyList = mutableSetOf<String>()


const val KEY_ITEMS = "KEY_ITEMS_"
var item = ITEMS("" ,"" ,"" ,"")

keyList.add(KEY_ITEMS + "yyyyMMdd")

val mapper = jacksonObjectMapper()
editor.putStringSet(KEY_LIST ,keyList).apply()
editor.putSTring(KEY_ITEMS + "yyyyMMdd" ,mapper.writeAsString(item)).apply()
======
// 共有プリファレンスから取得するとき
val keyList = editor.get(KEY_LIST)

lateinit val item: Items

keyList.forEach {
	item = it
}

for (obj in keyList) {
	item = obj
}


var height = item.height

========
Fragment or MainActivity

heightInput.text = item.height.toString()
heightInput.setText(item.height.toString())

=======
val sdf = SimpleDateFormat("yyyyMMdd")
val now = sdf.format(Date())
*/
