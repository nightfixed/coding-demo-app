package com.sample.soccerfan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.soccerfan.model.Player
import com.sample.soccerfan.model.Squad

class SquadAdapter: RecyclerView.Adapter<SquadAdapter.SquadViewHolder>() {

    var containerObject: Squad? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquadViewHolder {
        val containerView = LayoutInflater.from(parent.context).inflate(R.layout.item_squad_section, parent, false)
        return SquadViewHolder(containerView)
    }

    override fun onBindViewHolder(holder: SquadViewHolder, position: Int) {
        val keysList = ArrayList<String>(containerObject?.squad?.keys ?: emptyList())
        val squadPosition = keysList[position]
        val players = containerObject?.squad?.get(squadPosition) ?: emptyList()

        holder.bind(squadPosition, players)
    }

    override fun getItemCount(): Int = containerObject?.squad?.entries?.size ?: 0

    inner class SquadViewHolder(private val containerView: View): RecyclerView.ViewHolder(containerView) {

        fun bind(squadPosition: String, players: List<Player>) {
            containerView.findViewById<TextView>(R.id.tv_squad_position).text = squadPosition
            val playersAdapter = SquadPlayerAdapter(players)
            val recyclerView = containerView.findViewById<RecyclerView>(R.id.rv_players)
            recyclerView.layoutManager = LinearLayoutManager(containerView.context)
            recyclerView.adapter = playersAdapter
        }
    }
}