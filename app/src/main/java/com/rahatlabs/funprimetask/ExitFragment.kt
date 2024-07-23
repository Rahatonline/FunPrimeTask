package com.rahatlabs.funprimetask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class ExitFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_exit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<Button>(R.id.exitButton)?.setOnClickListener {
            activity?.finish()
        }

        view.findViewById<Button>(R.id.goBackButton)?.setOnClickListener {
            findNavController().navigate(R.id.action_exitFragment_to_recyclerFragment)
        }

    }

}