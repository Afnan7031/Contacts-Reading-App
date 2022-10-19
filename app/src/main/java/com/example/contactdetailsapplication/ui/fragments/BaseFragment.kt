package com.example.contactdetailsapplication.ui.fragments

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.contactdetailsapplication.helpers.di.DIComponent

abstract class BaseFragment<T : ViewDataBinding>(private val layoutId: Int) : Fragment() {

    private var _binding: T? = null
    val binding get() = _binding!!

    val mContext by lazy { binding.root.context }
    val mActivity by lazy { mContext as Activity }
    val diComponent = DIComponent()
    private var hasInitializedRootView = false
    private var mListener: PermissionListener? = null
    private val contactPermission = arrayOf(
        Manifest.permission.READ_CONTACTS
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!hasInitializedRootView) {
            hasInitializedRootView = true
            onViewCreatedOneTime()
        } else
            onViewCreatedEverytime()
    }

    abstract fun onViewCreatedOneTime()
    abstract fun onViewCreatedEverytime()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private val permissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions(),
            fun(permission: Map<String, Boolean>) {
                val granted = permission.entries.all {
                    it.value
                }
                if (granted) {
                    mListener?.isPermissionGranted(true)
                    return
                }
                mListener?.isPermissionGranted(false)

            })

    fun permissionWork(listener: PermissionListener) {
        mListener = listener
        try {
            permissionLauncher.launch(contactPermission)
        } catch (e: Exception) {

        }
    }

    interface PermissionListener {
        fun isPermissionGranted(isGranted: Boolean)
    }

}