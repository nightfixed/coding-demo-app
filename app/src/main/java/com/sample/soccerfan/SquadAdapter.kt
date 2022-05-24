package com.sample.soccerfan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.soccerfan.model.Player
import com.sample.soccerfan.model.Squad

class SquadAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var containerObject: Squad? = null

    private val ITEM_TYPE_PLAYER = 1
    private val ITEM_TYPE_FOOTER = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_TYPE_FOOTER -> {
                val containerView = LayoutInflater.from(parent.context).inflate(R.layout.item_footer, parent, false)
                FooterViewHolder(containerView)
            }
            else -> {
                val containerView = LayoutInflater.from(parent.context).inflate(R.layout.item_squad_section, parent, false)
                PlayerViewHolder(containerView)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is PlayerViewHolder) {
            val keysList = ArrayList<String>(containerObject?.squad?.keys ?: emptyList())
            val squadPosition = keysList[position]
            val players = containerObject?.squad?.get(squadPosition) ?: emptyList()

            holder.bind(squadPosition, players)
        }
    }

    override fun getItemCount(): Int {
        val teamSize = containerObject?.squad?.entries?.size ?: 0
        if (teamSize > 0) {
            return teamSize + 1 // account for footer
        }
        return teamSize
        //return containerObject?.squad?.entries?.size ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        if (position == containerObject?.squad?.entries?.size)  {
            return ITEM_TYPE_FOOTER
        }
        return ITEM_TYPE_PLAYER
    }

    fun updateSquad(squad: Squad) {
        containerObject = squad
    }

    inner class PlayerViewHolder(private val containerView: View): RecyclerView.ViewHolder(containerView) {

        fun bind(squadPosition: String, players: List<Player>?) {
            containerView.findViewById<TextView>(R.id.tv_squad_position).text = squadPosition
            val playersAdapter = SquadPlayerAdapter(players ?: emptyList())
            val recyclerView = containerView.findViewById<RecyclerView>(R.id.rv_players)
            recyclerView.layoutManager = LinearLayoutManager(containerView.context)
            recyclerView.adapter = playersAdapter
        }
    }

    inner class FooterViewHolder(private val containerView: View): RecyclerView.ViewHolder(containerView)
}