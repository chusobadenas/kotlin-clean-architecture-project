package com.jesusbadenas.kotlin_clean_architecture_project.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jesusbadenas.kotlin_clean_architecture_project.R
import com.jesusbadenas.kotlin_clean_architecture_project.databinding.ItemUserBinding
import com.jesusbadenas.kotlin_clean_architecture_project.domain.model.User
import com.jesusbadenas.kotlin_clean_architecture_project.userlist.UserAdapter.UserViewHolder

class UserAdapter : ListAdapter<User, UserViewHolder>(UserDiffCallback()) {

    interface OnItemClickListener {
        fun onUserItemClicked(user: User)
    }

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = DataBindingUtil.inflate<ItemUserBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        holder.itemView.setOnClickListener {
            onItemClickListener?.onUserItemClicked(user)
        }
    }

    class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) = with(itemView) {
            binding.user = user
            binding.executePendingBindings()
        }
    }

    internal class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User) =
            oldItem.userId == newItem.userId

        override fun areContentsTheSame(oldItem: User, newItem: User) =
            oldItem.userId == newItem.userId &&
                oldItem.coverUrl == newItem.coverUrl &&
                oldItem.fullName == newItem.fullName &&
                oldItem.email == newItem.email &&
                oldItem.description == newItem.description &&
                oldItem.followers == newItem.followers
    }
}
