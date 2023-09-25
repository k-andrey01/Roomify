import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myroom.R
import com.example.myroom.database.dataclasses.MeetingRoom

class MeetingRoomAdapter(private val onItemClick: (MeetingRoom) -> Unit) : ListAdapter<MeetingRoom, MeetingRoomAdapter.MeetingRoomViewHolder>(MeetingRoomDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingRoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MeetingRoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: MeetingRoomViewHolder, position: Int) {
        val room = getItem(position)
        holder.bind(room)
        holder.itemView.setOnClickListener { onItemClick(room) }
    }

    inner class MeetingRoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.name)
        private val chairsTextView: TextView = itemView.findViewById(R.id.chairs)
        private val projectorTextView: TextView = itemView.findViewById(R.id.projector)
        private val whiteboardTextView: TextView = itemView.findViewById(R.id.whiteboard)

        fun bind(room: MeetingRoom) {
            nameTextView.text = "Комната " + room.roomName
            chairsTextView.text = "${room.numberOfChairs} мест"
            projectorTextView.text = "Проектор: ${if (room.hasProjector) "Да" else "Нет"}"
            whiteboardTextView.text = "Доска: ${if (room.hasWhiteboard) "Да" else "Нет"}"
        }
    }
}

class MeetingRoomDiffCallback : DiffUtil.ItemCallback<MeetingRoom>() {
    override fun areItemsTheSame(oldItem: MeetingRoom, newItem: MeetingRoom): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MeetingRoom, newItem: MeetingRoom): Boolean {
        return oldItem == newItem
    }
}
