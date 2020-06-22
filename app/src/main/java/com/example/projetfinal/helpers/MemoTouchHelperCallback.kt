package com.example.projetfinal.helpers

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.projetfinal.activitys.MainActivity
import com.example.projetfinal.viewmodels.MemoListAdapter


class MemoTouchHelperCallback(private var adapter: MemoListAdapter) : ItemTouchHelper.Callback() {


    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlagsRight = ItemTouchHelper.RIGHT
        return makeMovementFlags(
            dragFlagsRight,
            dragFlagsRight
        )
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        adapter.onItemDismiss(viewHolder.adapterPosition)
        adapter.notifyDataSetChanged()

    }

}