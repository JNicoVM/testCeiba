package co.com.ceiba.mobile.pruebadeingreso.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.R
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.domain.models.User

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val view: View): RecyclerView.ViewHolder(view){
        //Binding variable
        private val binding  = UserListItemBinding.bind(view)

        fun bind(user: User){
            binding.name.text = user.name
            binding.phone.text = user.phone
            binding.email.text = user.email
            binding.btnViewPost.setOnClickListener {
                onItemClickListener?.let { it1 -> it1(user) }
            }
        }
    }

    private val differCallback = object: DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
           return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this@UserAdapter, differCallback)

    private var onItemClickListener: ((User) -> Unit) ? = null

    fun setOnItemClickListener(listener: (User)->Unit){
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
       return UserViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
       )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = differ.currentList[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}