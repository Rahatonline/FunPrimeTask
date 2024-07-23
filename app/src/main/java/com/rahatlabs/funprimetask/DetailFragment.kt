package com.rahatlabs.funprimetask

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)

        val photo = args.photo
        val titleView = view.findViewById<TextView>(R.id.title)
        val imageView = view.findViewById<ImageView>(R.id.image)
        val shareButton = view.findViewById<Button>(R.id.shareButton)
        val crossButton = view.findViewById<ImageView>(R.id.crossButton)
        crossButton.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_exitFragment)
        }
        titleView.text = photo.title

        if (photo.url.isNotEmpty()) {
            Glide.with(this).load(photo.url).into(imageView)
        }

        shareButton.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, Uri.parse(photo.url))
                type = "image/*"
            }
            startActivity(Intent.createChooser(intent, "Share Image"))
        }


        val adView = view.findViewById<AdView>(R.id.adView)
        adView.loadAd(AdRequest.Builder().build())

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        view?.findViewById<AdView>(R.id.adView)?.destroy()
    }
}