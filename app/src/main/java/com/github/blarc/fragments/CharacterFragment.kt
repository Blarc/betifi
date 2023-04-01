package com.github.blarc.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.blarc.R
import androidx.fragment.app.activityViewModels
import com.caverock.androidsvg.SVGImageView


class CharacterFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance() = CharacterFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


//        return inflater.inflate(R.layout.fragment_character, container, false)

        val view = inflater.inflate(R.layout.fragment_character, container, false)

        val svgImageView1 = view.findViewById<SVGImageView>(R.id.svgImageView1)
        svgImageView1.setOnClickListener {
            val item = "head"
        }

        val svgImageView2 = view.findViewById<SVGImageView>(R.id.svgImageView2)
        svgImageView2.setOnClickListener {
            val item = "mainHand"
        }

        val svgImageView3 = view.findViewById<SVGImageView>(R.id.svgImageView3)
        svgImageView3.setOnClickListener {
            val item = "offHand"
        }

        val svgImageView4 = view.findViewById<SVGImageView>(R.id.svgImageView4)
        svgImageView4.setOnClickListener {
            val item = "torso"
        }

        val svgImageView5 = view.findViewById<SVGImageView>(R.id.svgImageView5)
        svgImageView5.setOnClickListener {
            val item = "legs"
        }

        val svgImageView6 = view.findViewById<SVGImageView>(R.id.svgImageView6)
        svgImageView6.setOnClickListener {
            val item = "feet"
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // TODO @martinb: Add character fragment
    }
}