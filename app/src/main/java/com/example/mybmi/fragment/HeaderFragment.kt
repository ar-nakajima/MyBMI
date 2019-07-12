package com.example.mybmi.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mybmi.R
import kotlinx.android.synthetic.main.fragment_header.*

class HeaderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_header, container, false)
    }
//    引数に指定した文字列を設定するメソッド
    fun setHeaderFragment(header: String) {
        headerFragment.text = header
    }

}
