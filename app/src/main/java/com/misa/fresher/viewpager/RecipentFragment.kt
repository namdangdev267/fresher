package com.misa.fresher.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import com.misa.fresher.R
import com.misa.fresher.ShipInforModel
import com.misa.fresher.model.ShipInfor

class RecipentFragment : Fragment() {
    private var model: ShipInforModel? = null
    private var shipInfor: ShipInfor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        model = ViewModelProvider(requireActivity()).get(ShipInforModel::class.java)
        return inflater.inflate(R.layout.fragment_receiver, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getShippingInfor(view)
    }

    private fun getShippingInfor(view: View) {
        val shipInfor=ShipInfor("","","","","")
        val etReceiver = view.findViewById<EditText>(R.id.edtReceiver)
        val etPhone = view.findViewById<EditText>(R.id.edtPhone)
        val etAddress = view.findViewById<EditText>(R.id.edtAddress)
        val etArea = view.findViewById<EditText>(R.id.txtArea)
        val etWard = view.findViewById<EditText>(R.id.edtWard)
        etReceiver.doAfterTextChanged {
           shipInfor.receiver=etReceiver.text.toString()
        }
        etAddress.doAfterTextChanged {
            shipInfor.addres=etAddress.text.toString()
        }
        etPhone.doAfterTextChanged {
            shipInfor.phone=etPhone.text.toString()
        }
        etArea.doAfterTextChanged {
            shipInfor.area=etArea.text.toString()
        }
        etWard.doAfterTextChanged {
            shipInfor.ward=etWard.text.toString()
        }
            model?.add(shipInfor)
    }
    //private fun addShipInfor(input:String)
}