package com.example.mybmi.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mybmi.R
import kotlinx.android.synthetic.main.fragment_footer.*

@Suppress("UNREACHABLE_CODE")
class FooterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_footer, container, false)



        addButton.setOnClickListener{
            val fragment = InputFragment()
            val fragmentManager = this.getFragmentManager()
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.container, fragment)
//                .addToBackStack(null)
//                .commit()
        }

       historyBuntton.setOnClickListener {
            val fragment = HistoryFragment()
            val fragmentManager = this.getFragmentManager()
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.container, fragment)
//                .addToBackStack(null)
//                .commit()
        }


    }


}
