package com.lkb.googlemapdemo

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.lkb.googlemapdemo.databinding.BottomSheetBinding

class BottomModal : BottomSheetDialogFragment() {
     private var listener: (() -> Unit?)? = null
    lateinit var binding: BottomSheetBinding
    var flag = 0
    private val viewModel: MapViewModel by viewModels {
        MapViewModelFactory((activity?.application as MapApp).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
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
        binding.fbCancel.setOnClickListener {
            listener?.invoke()
            this.dismiss()
        }
        binding.etLatlongValue.setText("$lat, $long")
        binding.button.setOnClickListener {
            flag = 1
            viewModel.saveData(lat, long)
            this.dismiss()
        }
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        if (flag == 0) {
            listener?.invoke()
        } else {
            flag = 0
        }

    }

    fun setCallback(function: () -> Unit) {
        listener = function
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