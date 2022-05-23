package com.sample.soccerfan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ChooserFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_chooser, container, false)

        view.findViewById<Button>(R.id.btn_ucl).setOnClickListener {
            findNavController().navigate(R.id.action_chooser_to_ucl)
        }

        view.findViewById<Button>(R.id.btn_uel).setOnClickListener {
            findNavController().navigate(R.id.action_chooser_to_uel)
        }

        return view
    }
}