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
        "pants" to R.id.svgImageView6
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


        FirebaseUtils.subscribeToUserEquipmentOnFirebase { equipmentList ->

            // TODO: Set equipment images via it

            //set trausers
            val pickedImageView: ImageView = view.findViewById(R.id.svgImageView62)
            val avatarImageView: ImageView = view.findViewById(R.id.characterImageViewPants)
            val equippedPants = equipmentList.filter { item -> item.type == "pants" }
            if (equippedPants.size === 1) {
                view.context.resources.getIdentifier(
                    "picked_item_${equippedPants[0].iconRef}",
                    "drawable",
                    view.context.packageName
                ).let { pickedImageView.setImageResource(it) }

                view.context.resources.getIdentifier(
                    "equipped_item_${equippedPants[0].iconRef}",
                    "drawable",
                    view.context.packageName
                ).let { avatarImageView.setImageResource(it) }

                pickedImageView.alpha = 1.0F
            } else {
                pickedImageView.alpha = 0.6F
            }

            //set shirt
            val shirtPickedImageView: ImageView = view.findViewById(R.id.svgImageView22)
            val shirtAvatarImageView: ImageView = view.findViewById(R.id.characterImageViewShirt)
            val equippedShirts = equipmentList.filter { item -> item.type == "torso" }
            if (equippedShirts.size === 1) {
                view.context.resources.getIdentifier(
                    "picked_item_${equippedShirts[0].iconRef}",
                    "drawable",
                    view.context.packageName
                ).let { shirtPickedImageView.setImageResource(it) }

                view.context.resources.getIdentifier(
                    "equipped_item_${equippedShirts[0].iconRef}",
                    "drawable",
                    view.context.packageName
                ).let { shirtAvatarImageView.setImageResource(it) }

                shirtPickedImageView.alpha = 1.0F
            } else {
                shirtPickedImageView.alpha = 0.6F
            }

            //set head
            val headPickedImageView: ImageView = view.findViewById(R.id.svgImageView12)
            val headAvatarImageView: ImageView = view.findViewById(R.id.characterImageViewHat)
            val equippedHats = equipmentList.filter { item -> item.type == "head" }
            if (equippedHats.size === 1) {
                view.context.resources.getIdentifier(
                    "picked_item_${equippedHats[0].iconRef}",
                    "drawable",
                    view.context.packageName
                ).let { headPickedImageView.setImageResource(it) }

                view.context.resources.getIdentifier(
                    "equipped_item_${equippedHats[0].iconRef}",
                    "drawable",
                    view.context.packageName
                ).let { headAvatarImageView.setImageResource(it) }

                headPickedImageView.alpha = 1.0F
            } else {
                headPickedImageView.alpha = 0.6F
            }

            //set sword
            val swordPickedImageView: ImageView = view.findViewById(R.id.svgImageView42)
            val swordAvatarImageView: ImageView = view.findViewById(R.id.characterImageViewSword)
            val equippedSwords = equipmentList.filter { item -> item.type == "mainHand" }
            if (equippedSwords.size === 1) {
                view.context.resources.getIdentifier(
                    "picked_item_${equippedSwords[0].iconRef}",
                    "drawable",
                    view.context.packageName
                ).let { swordPickedImageView.setImageResource(it) }

                view.context.resources.getIdentifier(
                    "equipped_item_${equippedSwords[0].iconRef}",
                    "drawable",
                    view.context.packageName
                ).let { swordAvatarImageView.setImageResource(it) }

                swordPickedImageView.alpha = 1.0F
            } else {
                swordPickedImageView.alpha = 0.6F
            }

            //set shild
            val shieldPickedImageView: ImageView = view.findViewById(R.id.svgImageView52)
            val shieldAvatarImageView: ImageView = view.findViewById(R.id.characterImageViewShield)
            val equippedShields = equipmentList.filter { item -> item.type == "offHand" }
            if (equippedShields.size === 1) {
                view.context.resources.getIdentifier(
                    "picked_item_${equippedShields[0].iconRef}",
                    "drawable",
                    view.context.packageName
                ).let { shieldPickedImageView.setImageResource(it) }

                view.context.resources.getIdentifier(
                    "equipped_item_${equippedShields[0].iconRef}",
                    "drawable",
                    view.context.packageName
                ).let { shieldAvatarImageView.setImageResource(it) }

                swordPickedImageView.alpha = 1.0F
            } else {
                swordPickedImageView.alpha = 0.6F
            }

            //set shoes
            val shoesPickedImageView: ImageView = view.findViewById(R.id.svgImageView32)
            val shoesAvatarImageView: ImageView = view.findViewById(R.id.characterImageViewShoes)
            val equippedShoes = equipmentList.filter { item -> item.type == "feet" }
            if (equippedShoes.size === 1) {
                view.context.resources.getIdentifier(
                    "picked_item_${equippedShoes[0].iconRef}",
                    "drawable",
                    view.context.packageName
                ).let { shoesPickedImageView.setImageResource(it) }

                view.context.resources.getIdentifier(
                    "equipped_item_${equippedShoes[0].iconRef}",
                    "drawable",
                    view.context.packageName
                ).let { shoesAvatarImageView.setImageResource(it) }

                shoesPickedImageView.alpha = 1.0F
            } else {
                shoesPickedImageView.alpha = 0.6F
            }


            // Loop through equipment views and set click listeners
            for (equipmentView in equipmentViews) {
                val svgImageView = view.findViewById<ImageView>(equipmentView.value)

                svgImageView.setOnClickListener {
                    UIUtils.replaceFragment(
                        requireActivity(),
                        R.id.main_fragment_container,
                        InventoryFragment.newInstance(
                            equipmentView.key,
                            equipmentList.toMutableList()
                        )
                    )
                }
            }
        }
    }
}