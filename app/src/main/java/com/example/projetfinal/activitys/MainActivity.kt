package com.example.projetfinal.activitys

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projetfinal.R
import com.example.projetfinal.di.DIApplication
import com.example.projetfinal.helpers.MemoTouchHelperCallback
import com.example.projetfinal.models.Memo
import com.example.projetfinal.repositories.MemoRepository
import com.example.projetfinal.viewmodels.MemoListAdapter
import com.example.projetfinal.viewmodels.MemoViewModel
import javax.inject.Inject
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    private lateinit var memoViewModel: MemoViewModel
    private lateinit var editTextMemo: EditText
    private lateinit var recyclerView: RecyclerView
    private var adapter: MemoListAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextMemo = findViewById(R.id.saisie_memo)
        recyclerView = findViewById<RecyclerView>(R.id.liste_memos)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter= MemoListAdapter(this);
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val itemTouchHelper = ItemTouchHelper(
            MemoTouchHelperCallback(this.adapter!!)
        )
        itemTouchHelper.attachToRecyclerView(recyclerView)

        memoViewModel = ViewModelProvider(this).get(MemoViewModel::class.java)
        memoViewModel.init(MemoRepository())

        memoViewModel.allMemos?.observe(this, Observer { memo ->
            // Update the cached copy of the words in the adapter.
            memo?.let { adapter!!.setMemos(it) }
        })
    }

    fun onClickBoutonValider(view: View?){
        val memo = Memo(editTextMemo.text.toString(),editTextMemo.text.toString())
        memoViewModel.insert(memo)

    }

    fun onRightSwipe(memo : Memo){
        memoViewModel.delete(memo)
    }


}