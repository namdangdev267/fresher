package com.misa.fresher.ui.fragment.shipinfor.receiver

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.PublicViewModel
import com.misa.fresher.data.models.InforShip
import com.misa.fresher.databinding.FragmentReceiverBinding

class ReceiverFragment(
    private var receiverViewModel: ReceiverViewModel,
    private var sharedViewModel: PublicViewModel
) : Fragment() {
    var inforShip = InforShip(null, null, null, null)

    private val binding: FragmentReceiverBinding by lazy {
        getInflater(layoutInflater)
    }

    val getInflater: (LayoutInflater) -> FragmentReceiverBinding
        get() = FragmentReceiverBinding::inflate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        sharedViewModel = ViewModelProvider(requireActivity()).get(PublicViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        receiverViewModel = ReceiverViewModel(view.context)
        binding.rcvReceiver.layoutManager = LinearLayoutManager(this.context)
        binding.rcvReceiver.adapter = ReceiverAdapter(receiverViewModel.listItemShip) {
            changeEditText(it)
        }
    }

    private fun changeEditText(inforShip: InforShip) {
        if (inforShip.receiver != null) this.inforShip.receiver = inforShip.receiver
        if (inforShip.tel != null) this.inforShip.tel = inforShip.tel
        if (inforShip.address != null) this.inforShip.address = inforShip.address
    }
}

