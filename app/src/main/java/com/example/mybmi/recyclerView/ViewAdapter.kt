package com.example.mybmi.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybmi.entity.ItemsOfBMI
import com.example.mybmi.R
import com.example.mybmi.ViewHolder
import com.example.mybmi.entity.ListItem
import androidx.recyclerview.widget.RecyclerView.ViewHolder as ViewHolder1

//ViewHolderを生成したりに生成したViewHolderにViewModelをセットしたりする。
//一つのListに対して一つ存在する。

class ViewAdapter(private val list: List<ItemsOfBMI>, private val listener: ListListener) :
    RecyclerView.Adapter<ViewHolder>() {

/*
adapterのひな型
  override fun getItemViewType(position: Int): Int {
        // 1. ヘッダーかリストアイテムかで異なる viewType を返すように実装する
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        // 2. viewType によって読み込む layout ファイルを指定する
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        // 3. text のセットとアイテムクリック時のイベントをセットする
    }

    override fun getItemCount(): Int {
        return this.items.count()
    }

 */
//    1. ヘッダーかリストアイテムかで異なる viewType を返すように実装する
//ヘッダー用かリストアイテム用のレイアウトを返すか判別する為の viewType を返却するように設定します。
    override fun getItemViewType(position: Int): Int {
        return states[position].type.int
    }


    //    テーブルのセルを作成
//    list_itemのviewを作ってViewHolderを生成しreturn
//    2. viewType によって読み込む layout ファイルを指定する
//設定した viewTyep を利用してヘッダー用とリストアイテム用の Header を指定した ViewHolder を返却するようにします。
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("Adapter", "onCreateViewHolder")
//    fromメソッドでlayoutInflaterクラスのインスタンスを取得
//    inflaterメソッドでビューにレイアウトXMLを適用し、指定されたcontxtからインスタンスを取得
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.fragment_items, parent, false)

//        ＊＊タイプを判定してデータ格納2. viewType によって読み込む layout ファイルを指定する
        when(RecyclerType.fromInt(viewType)){
            RecyclerType.SECTION -> {
                val view = RecyclerItemSectionView(context)
                return RecyclerItemSectionViewHolder(view)
            }
            RecyclerType.BODY -> {
                val view = RecyclerItemView(context)
                return RecyclerItemViewHolder(view)
            }
        }

        return ViewHolder(rowView)



    }

    //   値をセット（viewHolderで保持しているビューに対して実際に表示するコンテンツの設定をする
//    指定された一にデータを表示するために呼ばれる
//   （ positionをListのindexとして、ViewHolderに）
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // 3. text のセットとアイテムクリック時のイベントをセットする
        //あとは Enum で定義した各アイテム毎に行いたいアクションを定義します。
        Log.d("Adapter", "onBindViewHolder")
        holder.dateView.text = list[position].id
        holder.heightView.text = list[position].height
        holder.weightView.text = list[position].weight
        holder.bmiView.text = list[position].bmi
        holder.memoView.text = list[position].memo
        holder.itemView.setOnClickListener {
            listener.onClickRow(it, list[position])


//            ＊＊＊＊＊


            when(viewHolder){
                is RecyclerItemSectionViewHolder ->{
                    viewHolder.update(states[position])
                }
                is RecyclerItemViewHolder ->{
                    viewHolder.update(states[position])
                }
            }

        }
    }







    override fun getItemCount(): Int {
//        listのサイズを返してあげる必要がある
        Log.d("Adapter", "getItemCount")
        return list.size
    }

    interface ListListener {
        fun onClickRow(tappedView: View, rowModel: ItemsOfBMI)
    }
}

