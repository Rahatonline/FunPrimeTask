package com.rahatlabs.funprimetask

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerFragment : Fragment() {

    private val viewModel: PhotosViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recycler, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = PhotosAdapter { photo ->
            val action = RecyclerFragmentDirections.actionRecyclerFragmentToDetailFragment(photo)
            findNavController().navigate(action)
        }
        if (recyclerView != null) {
            recyclerView.adapter = adapter
        }

        viewModel.photos.observe(viewLifecycleOwner) { photos ->
            Log.d("Photos", photos.toString()) // Add this to see if the data is correct
            adapter.submitList(photos)
        }
    }
}