package io.luxus.adofai.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.luxus.adofai.R
import io.luxus.adofai.databinding.FragmentLevelListBinding
import io.luxus.adofai.presentation.view.adapter.LevelListAdapter
import io.luxus.adofai.presentation.viewmodel.LevelListViewModel
import io.luxus.animation.presentation.view.custom.listener.RecyclerItemClickListener

@AndroidEntryPoint
class FragmentLevelList : Fragment() {

    companion object {
        private val TAG = FragmentLevelList::class.simpleName
    }

    private val viewModel: LevelListViewModel by viewModels()

    private lateinit var levelListAdapter: LevelListAdapter
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        levelListAdapter = LevelListAdapter(viewModel.getLevelList())
        levelListAdapter.setHasStableIds(true)
        val binding = FragmentLevelListBinding.inflate(inflater, container, false)

        viewModel.init()
        viewModel.getLoadStatus().observe(viewLifecycleOwner, {
            if (it == null) return@observe
            when (it) {
                LevelListViewModel.LoadStatus.SUCCEED-> binding.progressBar.visibility = View.GONE
                LevelListViewModel.LoadStatus.LOADING-> binding.progressBar.visibility = View.VISIBLE
                LevelListViewModel.LoadStatus.FAILED-> Toast.makeText(
                    requireContext(), R.string.initial_load_failed,
                    Toast.LENGTH_LONG).show()
            }
        })


        recyclerView = binding.itemList
        recyclerView.adapter = levelListAdapter
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(20)
        //recyclerView.isDrawingCacheEnabled = true
        //recyclerView.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
        recyclerView.layoutManager = GridLayoutManager(context, 4)
        recyclerView.addOnItemTouchListener(RecyclerItemClickListener(requireContext(), object:
            RecyclerItemClickListener.OnItemClickListener.Builder() {
            override fun onItemClick(view: View, position: Int) {
                super.onItemClick(view, position)
                // TODO : move fragment
            }
        }))

        binding.lifecycleOwner = viewLifecycleOwner

        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onStart() {
        super.onStart()
        viewModel.load()
    }


}