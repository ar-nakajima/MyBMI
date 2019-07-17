package com.example.mybmi.view
/*
ViewHolderを生成したりに生成したViewHolderにViewModelをセットしたりする。
一つのListに対して一つ存在する。
今回はクリックのリスナーも一緒にセットしています。
ここが難しそうに見えてたんですが実際書いてみると全然難しくないです。

コンストラクタにリスト表示するRowModelのリストとタップを検出するためのリスナーを渡されるようにします。
（リスナーはこのクラスの下部にinterfaceを定義してあります）

onCreateViewHolder()でlist_itemのviewを作ってそれを元に先ほどのViewHolderを生成しreturnします。


よく考えたらここでViewHolderにRowModelを渡してあげてもいいかもしれません。
また、今回はここでクリックリスナーもセットしています。リスナーについては公式の例をまだ見つけられてないので何が正しいのかあんまわかっていませんがとりあえずこれが楽だと思いました。
 */
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybmi.entity.ItemsOfBMI
import com.example.mybmi.R
import com.example.mybmi.ViewHolder
import androidx.recyclerview.widget.RecyclerView.ViewHolder as ViewHolder1

class ViewAdapter(private val list: List<ItemsOfBMI>, private val listener: ListListener) : RecyclerView.Adapter<ViewHolder>() {

//    テーブルのセルを作成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("Adapter", "onCreateViewHolder")
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.items_history, parent, false)
        return ViewHolder(rowView)
    }

//   値をセット（ positionをListのindexとして、ViewHolderに）
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolder")
        holder.heightView.text = list[position].height
        holder.weightView.text = list[position].weight
        holder.bmiView.text = list[position].bmi
        holder.itemView.setOnClickListener {
            listener.onClickRow(it, list[position])
        }
    }

    override fun getItemCount(): Int {
        Log.d("Adapter", "getItemCount")
        return list.size
    }

    interface ListListener {
        fun onClickRow(tappedView: View, rowModel: ItemsOfBMI)
    }
}