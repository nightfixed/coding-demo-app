package com.sample.soccerfan

import com.sample.soccerfan.model.League
import com.sample.soccerfan.model.Player
import com.sample.soccerfan.model.Squad
import kotlinx.coroutines.flow.flow

class Repository {

    suspend fun getDisplayCategories() = flow<List<String>> {
       val result = mutableListOf<String>()

        with(result){
            add("overview")
            add("matches")
            add("groups")
            add("stats")
            add("squad")
        }

        emit(result)
    }

    suspend fun getSquad() = flow<Squad> {
        val result = mutableMapOf<String, List<Player>>()

        result["Goalkeepers"] = dummyGoalKeepers()
        result["Defenders"] = dummyGoalKeepers()
        result["Midfildiers"] = dummyGoalKeepers()
        result["Forwards"] = dummyGoalKeepers()
        result["Coach"] = dummyCoach()

        val squad = Squad(squad = result)
        emit(squad)
    }

    suspend fun getLeague() = flow<League> {
        val result = League(league = "UCL", team = "Barcelona", status = "playing", round = "Round 1 of 16")
        emit(result)
    }

    private fun dummyGoalKeepers(): List<Player> = ArrayList<Player>().apply {
        add(Player(profilePicUrl = "https://api.lorem.space/image/face?w=150&h=150", name = "Random player 1", country = "Some country", score = "27"))
        add(Player(profilePicUrl = "https://api.lorem.space/image/face?w=150&h=150", name = "Random player 20", country = "Another country", score = "20"))
        add(Player(profilePicUrl = "https://api.lorem.space/image/face?w=150&h=150", name = "Random player 0", country = "New country", score = "18"))
    }

    private fun dummyCoach(): List<Player> = ArrayList<Player>().apply {
        add(Player(profilePicUrl = "https://api.lorem.space/image/face?w=150&h=150", name = "Random Coach", country = "Some country", score = "27"))
    }
}