package com.github.blarc.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.blarc.BaseViewModel
import com.github.blarc.R
import androidx.fragment.app.activityViewModels
import com.caverock.androidsvg.SVGImageView


class CharacterFragment : Fragment() {

    private val baseViewModel: BaseViewModel by activityViewModels()

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
        // TODO @martinb: Add character fragment
    }
}