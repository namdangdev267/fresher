package com.misa.fresher

import android.app.DialogFragment


//class ShopSelectDialog(
//    context: Context,
//    private val binding: DialogSelectWorkingShopBinding,
//    private val onAcceptListener: (String) -> Unit
//) {
//    private val dialog by lazy { AlertDialog.Builder(context).setView(binding.root).create() }
//
//    private fun configureDialog() {
//        dialog.setCancelable(false)
//        dialog.setCanceledOnTouchOutside(false)
//    }
//
//    private fun bindingData() {
//        binding.btnOk.setOnClickListener {
//            dialog.dismiss()
//            val res = if (binding.rbKhoTong.isChecked)
//                binding.rbKhoTong.text.toString()
//            else binding.rbFashion.text.toString()
//            onAcceptListener(res)
//        }
//    }
//
//    fun showDialog(){
//        bindingData()
//        dialog.show()
//    }
//}

class ShopSelectDialog : DialogFragment() {
//    private val TAG = "ShopSelectDialog"
//
//    interface OnInputSelected {
//        fun clickOK(input: String)
//    }
//
//    var mOnInputSelected: OnInputSelected? = null
//    private val dialog by lazy { AlertDialog.Builder(context).setView(binding.root).create() }
//
//    private var btnOk: Button? = null
//
//    @Nullable
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        @Nullable container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        btnOk = view.findViewById(R.id.btnOk)
//        btnOk.setOnClickListener(object : OnClickListener() {
//            fun onClick(v: View?) {
//                Log.d(TAG, "onClick: closing dialog")
//                dialog!!.dismiss()
//            }
//        })
//        mActionOk!!.setOnClickListener(object : OnClickListener() {
//            fun onClick(v: View?) {
//                Log.d(TAG, "onClick: capturing input.")
//                val input = mInput!!.text.toString()
//                if (input != "") {
////
////                    //Easiest way: just set the value.
////                    MainFragment fragment = (MainFragment) getActivity().getFragmentManager().findFragmentByTag("MainFragment");
////                    fragment.mInputDisplay.setText(input);
//                    mOnInputSelected?.clickOK(input)
//                }
//                dialog!!.dismiss()
//            }
//        })
//        return view
//    }
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        try {
//            mOnInputSelected = targetFragment as OnInputSelected?
//        } catch (e: ClassCastException) {
//            Log.e(TAG, "onAttach: ClassCastException : " + e.message)
//        }
//    }

}

