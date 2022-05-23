package com.sample.soccerfan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sample.soccerfan.model.League

class UCLFragment: Fragment() {

    private val viewModel: DetailsViewModel by viewModels()

    private lateinit var displayDetailsAdapter: DisplayDetailsAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ucl2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        displayDetailsAdapter = DisplayDetailsAdapter(this)
        viewPager = view.findViewById(R.id.vp_pager)
        viewPager.adapter = displayDetailsAdapter

        tabLayout = view.findViewById(R.id.tl_tabs)

        subscribeUi()
        viewModel.getLeague()
        viewModel.getDisplaySections()
    }

    private fun subscribeUi() {
        viewModel.liveDataDisplay.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {/* showLoading(result.isLoading) */}
                is Result.NetworkError -> {/* showError(result.message)*/}
                is Result.Success -> {
                    initTabLayout(result.value)
                }
            }
        }

        viewModel.liveDataLeagueInfo.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {/* showLoading(result.isLoading) */}
                is Result.NetworkError -> {/* showError(result.message)*/}
                is Result.Success -> {
                    initHeader(result.value)
                }
            }
        }
    }

    private fun initTabLayout(display: List<String>) {
        displayDetailsAdapter.displaySections = display
        displayDetailsAdapter.notifyDataSetChanged()
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = display[position].replaceFirstChar {it.uppercase()}
        }.attach()
    }

    private fun initHeader(league: League) {
        view?.findViewById<TextView>(R.id.tv_team_label)?.text = league.team.replaceFirstChar {it.uppercase()}
        view?.findViewById<TextView>(R.id.tv_team_status)?.text = league.status.replaceFirstChar {it.uppercase()}
        view?.findViewById<TextView>(R.id.tv_team_rounds)?.text = league.round.replaceFirstChar {it.uppercase()}
    }
}