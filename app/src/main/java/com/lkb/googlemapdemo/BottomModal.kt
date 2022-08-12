package com.lkb.googlemapdemo

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lkb.googlemapdemo.databinding.BottomSheetBinding

class BottomModal : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetBinding
    val viewModel: MapViewModel by viewModels {
        MapViewModelFactory((activity?.application as MapApp).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = arguments?.getBundle("content")
        val lat = bundle?.getString("lat")
        val long = bundle?.getString("long")
        binding.etLatlongValue.setText("$lat, $long")
        binding.button.setOnClickListener {
            viewModel.saveData(lat, long)
            this.dismiss()
        }
    }


    companion object {
        private const val TAG = "content"
        fun newInstance(args: Bundle): BottomModal {
            return BottomModal().apply {
                arguments = bundleOf(TAG to args)
            }
        }
    }
}