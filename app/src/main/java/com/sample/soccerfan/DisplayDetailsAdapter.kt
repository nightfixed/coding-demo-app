package com.sample.soccerfan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DisplayDetailsAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    var displaySections: List<String>? = null

    override fun getItemCount(): Int = displaySections?.size ?: 0

    override fun createFragment(position: Int): Fragment {
        return if (position == itemCount - 1) {
            SquadFragment()
        } else {
            val fragment = DemoObjectFragment()
            fragment.arguments = Bundle().apply {
                // Our object is just an integer :-P
                putString(ARG_SECTION_TITLE, displaySections?.get(position))
            }
            fragment
        }
    }
}

private const val ARG_SECTION_TITLE = "section_title"

class DemoObjectFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_collection_object, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_SECTION_TITLE) }?.apply {
            val textView: TextView = view.findViewById(R.id.text1)
            textView.text = getString(ARG_SECTION_TITLE)
        }
    }
}