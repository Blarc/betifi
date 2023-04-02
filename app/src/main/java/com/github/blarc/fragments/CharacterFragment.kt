package com.github.blarc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.caverock.androidsvg.SVGImageView
import com.github.blarc.MyApplication
import com.github.blarc.R
import com.github.blarc.UIUtils
import com.github.blarc.firebase.FirebaseUtils


class CharacterFragment : Fragment() {

    // Create map of equipment to svg image view
    private val equipmentViews = mapOf(
        "head" to R.id.svgImageView1,
        "torso" to R.id.svgImageView2,
        "feet" to R.id.svgImageView3,
        "mainHand" to R.id.svgImageView4,
        "offHand" to R.id.svgImageView5,
        "legs" to R.id.svgImageView6
    )

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
        val usernameTextField = view.findViewById<TextView>(R.id.usernameText)
        usernameTextField.text = MyApplication.curUserId

        val levelTextField = view.findViewById<TextView>(R.id.level)
        levelTextField.text = "Level " + MyApplication.level.toString()

        val svgImageView1 = view.findViewById<ImageView>(R.id.svgImageView1)
        svgImageView1.setOnClickListener {

            FirebaseUtils.subscribeToUserEquipmentOnFirebase { equipmentList ->

                // TODO: Set equipment images via it

                // Loop through equipment views and set click listeners
                for (equipmentView in equipmentViews) {
                    val svgImageView = view.findViewById<ImageView>(equipmentView.value)

                    svgImageView.setOnClickListener {
                        UIUtils.replaceFragment(
                            requireActivity(),
                            R.id.main_fragment_container,
                            InventoryFragment.newInstance(equipmentView.key, equipmentList.toMutableList())
                        )
                    }
                }
            }
        }
    }
}