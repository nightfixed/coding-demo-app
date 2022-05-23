package com.sample.soccerfan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.soccerfan.model.Squad

class SquadFragment: Fragment() {

    private val viewModel: SquadViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var squadAdapter: SquadAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_squad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupList(view)
        subscribeUi()

        viewModel.getSquad()
    }

    private fun setupList(parent: View) {
        squadAdapter = SquadAdapter()
        recyclerView = parent.findViewById(R.id.rv_squad)
        val manager =  LinearLayoutManager(requireContext())
        recyclerView.layoutManager = manager
        recyclerView.adapter = squadAdapter
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), manager.orientation))
    }

    private fun subscribeUi() {
        viewModel.liveDataSquad.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {/* showLoading(result.isLoading) */}
                is Result.NetworkError -> {/* showError(result.message)*/}
                is Result.Success -> {
                    initSquadView(result.value)
                }
            }
        }
    }

    private fun initSquadView(squad: Squad) {
        squadAdapter.containerObject = squad
        squadAdapter.notifyDataSetChanged()
    }
}