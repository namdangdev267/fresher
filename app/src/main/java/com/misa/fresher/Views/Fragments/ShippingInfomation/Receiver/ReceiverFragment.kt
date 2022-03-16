package com.misa.fresher.Views.Fragments.ShippingInfomation.Receiver

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.Models.InforShip
import com.misa.fresher.Views.Fragments.SharedViewModel
import com.misa.fresher.databinding.FragmentReceiverBinding

class ReceiverFragment : Fragment() {

    lateinit var binding: FragmentReceiverBinding
    lateinit var receiverViewModel: ReceiverViewModel
    lateinit var sharedViewModel: SharedViewModel
    var inforShip = InforShip(null,null,null,null,null,null,null,null,null,false)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReceiverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        receiverViewModel = ReceiverViewModel(view.context)
        binding.recyclerviewShippingReceiver.layoutManager = LinearLayoutManager(this.context)

        binding.recyclerviewShippingReceiver.adapter = ReceiverAdapter(receiverViewModel.listItemRecyclerView) {
            changeEditText(it)
        }
    }

    fun changeEditText(inforShip: InforShip)
    {
//        sharedViewModel.updateInforShip(inforShip)
    }

}

