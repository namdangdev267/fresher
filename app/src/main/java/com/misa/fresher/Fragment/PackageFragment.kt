package com.misa.fresher.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.misa.fresher.Adapter.ReceiverAdapter
import com.misa.fresher.InputShip.InputReceiver
import com.misa.fresher.InputShip.InputType
import com.misa.fresher.databinding.FragmentPackageBinding

class PackageFragment: Fragment() {
    private var title: String?= null
    private var page = 0
    private var binding: FragmentPackageBinding?= null
    private var adapter: ReceiverAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments?.getInt("packagePage", 0) ?: 0
        title = arguments?.getString("packageTitle")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPackageBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = context?.let { ReceiverAdapter(createData(), it) }
        binding!!.rcvPackage.layoutManager = LinearLayoutManager(context)
        binding!!.rcvPackage.adapter = adapter
        binding!!.rcvPackage.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun createData(): Array<InputReceiver> {
        return arrayOf(
            InputReceiver("Weight (g)", false, InputType.TAP_INSERT),
            InputReceiver("Size (cm)", false, InputType.PACKAGE_SIZE),
        )
    }

    companion object{
        fun newInstance(page: Int, title: String?): PackageFragment {
            val fragmentPackage = PackageFragment()
            val args = Bundle()
            args.putInt("packagePage", page)
            args.putString("packageTitle", title)
            fragmentPackage.setArguments(args)
            return fragmentPackage
        }
    }
}