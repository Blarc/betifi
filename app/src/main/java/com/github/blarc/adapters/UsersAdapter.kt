package com.github.blarc.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.blarc.R
import com.github.blarc.UIUtils.replaceFragment
import com.github.blarc.activities.ChallengeCreateActivity
import com.github.blarc.entity.User
import com.github.blarc.fragments.ChallengeCreateFragment
import com.github.blarc.inflate
import kotlin.random.Random

class UsersAdapter(
    private var users: ArrayList<User>,
    private var layoutId: Int,
    private var context: Context?
): RecyclerView.Adapter<UsersAdapter.UserHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {

        return UserHolder(parent.inflate(layoutId, false))
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        if (users.isNotEmpty()) {
            val user = users[position]
            holder.bindUser(user)
        }
    }

    fun setUsers(users: List<User>) {
        this.users.clear()
        this.users.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        private var view: View = v
        private var user: User? = null

        init {
            v.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val activity = context as ChallengeCreateActivity
            activity.selectedFriend = user
            replaceFragment(activity, R.id.challenge_create_fragment_container, ChallengeCreateFragment::class.java)

        }

        fun bindUser(user: User) {
            this.user = user

//            val assignBtn: ImageButton = view.findViewById(R.id.assign_challenge_to_friend_btn)
//            val fireNumberTextView: TextView = view.findViewById(R.id.assign_item_fire_number)
//            fireNumberTextView.text = Random.nextInt(12).toString()

            val friendUsernameTextView: TextView = view.findViewById(R.id.user_item_user_name)
            friendUsernameTextView.text = user.userId

//            assignBtn.setOnClickListener {
//                if (!btnChecked) {
//                    assignBtn.setImageResource(R.drawable.ic_checked)
//                    btnChecked = true
//                } else {
//                    assignBtn.setImageResource(R.drawable.ic_krog)
//                    btnChecked = false
//                }
//                (context as ChallengeCreateActivity).addOrRemoveUserFromSelectedList(user)
//            }

        }
    }
}