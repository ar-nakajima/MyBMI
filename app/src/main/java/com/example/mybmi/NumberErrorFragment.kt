package com.example.mybmi

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface

import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ErrorDialog : DialogFragment() {

    var title = "title"
    var msg = "msg"
    var okText = "OK"
    /** ok押下時の挙動 */
    var onOkClickListener: DialogInterface.OnClickListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the Builder class for convenient dialog construction
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
            .setMessage(msg)
            .setPositiveButton(okText, onOkClickListener)
        // Create the AlertDialog object and return it
        return builder.create()
    }

    override fun onPause() {
        super.onPause()
        // onPause でダイアログを閉じる場合
        dismiss()
    }



//
//    fun show(mng: FragmentManager, alert: String) {
//        alertText.text = alert
//    }
}
////import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import kotlinx.android.synthetic.main.fragment_number_error.*

//class SampleDialogFragment : DialogFragment() {
//
//    fun onCreateDialog(savedInstanceState: Bundle): Dialog {
//        return AlertDialog.Builder(getActivity())
//            .setTitle("タイトル")
//            .setMessage("メッセージ")
//            .create()
//    }
//
//    fun onPause() {
//        super.onPause()
//
//        // onPause でダイアログを閉じる場合
//        dismiss()
//    }
//}


//class NumberErrorFragment : Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_number_error, container, false)
//    }
//     fun setErrorAlert ( alert: String) {
//         errorAlertText.text = alert
//     }
//}
