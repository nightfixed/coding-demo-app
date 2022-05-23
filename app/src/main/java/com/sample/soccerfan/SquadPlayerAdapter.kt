package com.sample.soccerfan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.soccerfan.model.Player

class SquadPlayerAdapter(private val players: List<Player>) : RecyclerView.Adapter<SquadPlayerAdapter.PlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val containerView = LayoutInflater.from(parent.context).inflate(R.layout.item_squad_player, parent, false)
        return PlayerViewHolder(containerView)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(players[position])
    }

    override fun getItemCount(): Int = players.size

    inner class PlayerViewHolder(private val containerView: View): RecyclerView.ViewHolder(containerView) {
         fun bind(player: Player) {
            containerView.findViewById<TextView>(R.id.tv_player_name).text = player.name
            containerView.findViewById<TextView>(R.id.tv_player_country).text = player.country
            containerView.findViewById<TextView>(R.id.tv_player_score).text = player.score
        }
    }
}