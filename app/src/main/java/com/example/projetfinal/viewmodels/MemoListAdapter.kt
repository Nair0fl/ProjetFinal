package com.example.projetfinal.viewmodels

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.projetfinal.R
import com.example.projetfinal.activitys.DetailActivity
import com.example.projetfinal.activitys.MainActivity
import com.example.projetfinal.di.DIApplication
import com.example.projetfinal.models.Memo
import com.example.projetfinal.models.MemoDAO
import com.formationandroid.projectmemokotlin.fragment.DetailFragment
import java.util.*
import javax.inject.Inject


class MemoListAdapter constructor( private val app: AppCompatActivity
) : RecyclerView.Adapter<MemoListAdapter.MemoViewHolder>() {

    @Inject lateinit var memoDAO:MemoDAO
    private val inflater: LayoutInflater = LayoutInflater.from(app)
    private var memos = emptyList<Memo>() // Cached copy of words

    init{
        DIApplication.getAppComponent()?.inject(this)
    }
    inner class MemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView = itemView.findViewById(R.id.memo_intitule)
        init {
            itemView.setOnClickListener { view ->
                val memo: Memo = memos[adapterPosition]


                if (app.findViewById<View?>(R.id.large_detail) == null) {
                    val intent = Intent(view.context, DetailActivity::class.java)
                    intent.putExtra("detail", memo.content)
                    view.context.startActivity(intent)
                } else {
                    // fragment :
                    val fragment = DetailFragment()
                    val bundle = Bundle()
                    bundle.putString(DetailFragment.EXTRA_PARAM, memo.content)
                    fragment.arguments = bundle

                    // fragment manager :
                    val fragmentManager =
                        app.supportFragmentManager
                    // transaction :
                    val fragmentTransaction =
                        fragmentManager.beginTransaction()
                    fragmentTransaction.replace(R.id.large_detail, fragment, "detail")
                    fragmentTransaction.commit()
                }
            }
        }
    }

    fun onItemMove(positionDebut: Int, positionFin: Int): Boolean {
        Collections.swap(memos, positionDebut, positionFin)
        notifyItemMoved(positionDebut, positionFin)
        return true
    }

    // Appelé une fois à la suppression.
    fun onItemDismiss(position: Int) {

        if (position > -1) {
            memoDAO.delete(memos[position])
            memos.drop(position)

            notifyItemRemoved(position)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val itemView = inflater.inflate(R.layout.item_memo, parent, false)
        return MemoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        val current = memos[position]
        holder.wordItemView.text = current.content
    }

    internal fun setMemos(memos: List<Memo>) {
        this.memos = memos
        notifyDataSetChanged()
    }

    override fun getItemCount() = memos.size

}