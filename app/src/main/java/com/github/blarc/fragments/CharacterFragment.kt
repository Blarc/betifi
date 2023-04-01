package com.github.blarc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.caverock.androidsvg.SVGImageView
import com.github.blarc.R
import com.github.blarc.UIUtils


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
        return inflater.inflate(R.layout.fragment_character, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val svgImageView1 = view.findViewById<SVGImageView>(R.id.svgImageView1)
        svgImageView1.setOnClickListener {

            UIUtils.replaceFragment(
                requireActivity(),
                R.id.main_fragment_container,
                InventoryFragment.newInstance("head")
            )
        }

        val svgImageView2 = view.findViewById<SVGImageView>(R.id.svgImageView2)
        svgImageView2.setOnClickListener {
            UIUtils.replaceFragment(
                requireActivity(),
                R.id.main_fragment_container,
                InventoryFragment.newInstance("mainHand")
            )
        }

        val svgImageView3 = view.findViewById<SVGImageView>(R.id.svgImageView3)
        svgImageView3.setOnClickListener {
            UIUtils.replaceFragment(
                requireActivity(),
                R.id.main_fragment_container,
                InventoryFragment.newInstance("offHand")
            )
        }

        val svgImageView4 = view.findViewById<SVGImageView>(R.id.svgImageView4)
        svgImageView4.setOnClickListener {
            UIUtils.replaceFragment(
                requireActivity(),
                R.id.main_fragment_container,
                InventoryFragment.newInstance("torso")
            )
        }

        val svgImageView5 = view.findViewById<SVGImageView>(R.id.svgImageView5)
        svgImageView5.setOnClickListener {
            UIUtils.replaceFragment(
                requireActivity(),
                R.id.main_fragment_container,
                InventoryFragment.newInstance("legs")
            )
        }

        val svgImageView6 = view.findViewById<SVGImageView>(R.id.svgImageView6)
        svgImageView6.setOnClickListener {
            UIUtils.replaceFragment(
                requireActivity(),
                R.id.main_fragment_container,
                InventoryFragment.newInstance("feet")
            )
        }
    }
}