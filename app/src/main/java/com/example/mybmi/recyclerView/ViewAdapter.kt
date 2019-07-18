package com.example.mybmi.recyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybmi.R
import com.example.mybmi.SectionViewHolder
import com.example.mybmi.ViewHolder
import com.example.mybmi.entity.ItemsOfBMI
import androidx.recyclerview.widget.RecyclerView.ViewHolder as ViewHolder1

//ViewHolderを生成したりに生成したViewHolderにViewModelをセットしたりする。
//一つのListに対して一つ存在する。

class ViewAdapter(private val list: List<ItemsOfBMI>, private val listener: ListListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    //  セルを作成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.d("onCreateViewHolder", "viewType によって読み込む layout ファイルを指定")
//    fromメソッドでlayoutInflaterクラスのインスタンスを取得
//    inflaterメソッドでビューにレイアウトXMLを適用し、指定されたcontxtからインスタンスを取得
//        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_view, parent, false)

//       viewType によって読み込む layout ファイルを指定する
        when (RecyclerType.fromInt(viewType)) {
            RecyclerType.SECTION -> {
                //    fromメソッドでlayoutInflaterクラスのインスタンスを取得
//    inflaterメソッドでビューにレイアウトXMLを適用し、指定されたcontxtからインスタンスを取得
                val view: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_section_view, parent, false)
                return SectionViewHolder(view)

            }


            RecyclerType.BODY -> {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_view, parent, false)
                return ViewHolder(view)
//                val view = RecyclerItemView(context)
//                return RecyclerItemViewHolder(view)
            }
        }


    }

    //   値をセット（viewHolderで保持しているビューに対して実際に表示するコンテンツの設定をする
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        // 3. text のセットとアイテムクリック時のイベントをセットする
        //Enum で定義した各アイテム毎に行いたいアクションを定義します。
//        holder.itemView.setOnClickListener {listener.onClickRow(it, list[position])

//            ＊＊＊＊＊

        when (holder) {
            is ViewHolder -> {
                //BODY
                val item = list[position]

                holder.dateView.text = list[position].id
                holder.heightView.text = list[position].height
                holder.weightView.text = list[position].weight
                holder.bmiView.text = list[position].bmi
                holder.memoView.text = list[position].memo

            }

            is SectionViewHolder -> {
                // SECTION
                val item = list[position]
                holder.monthView.text = item.getDate().toString()
//                viewHolder.update(states[position])
            }
        }

    }


    //ヘッダー用かリストアイテム用のレイアウトを返すか判別する為の viewType を返却するように設定します。
    override fun getItemViewType(position: Int): Int {
//        return list[position].type.int
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


