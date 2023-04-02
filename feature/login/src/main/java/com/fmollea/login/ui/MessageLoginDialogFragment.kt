package com.fmollea.login.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.fmollea.login.R
import com.fmollea.login.databinding.FragmentMessageLoginDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MessageLoginDialogFragment : BottomSheetDialogFragment(R.layout.fragment_message_login_dialog) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMessageLoginDialogBinding.bind(view)
        val args: MessageLoginDialogFragmentArgs by navArgs()

        binding.tvMessage.text = args.message
    }
}


