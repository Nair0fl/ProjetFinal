package com.example.projetfinal.activitys


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.projetfinal.R
import com.formationandroid.projectmemokotlin.fragment.DetailFragment
import kotlinx.android.synthetic.main.fragment_detail.view.*

//Permet d'avoir le détail d'un mémo sur lequel on a cliqué (vue verticale portrait uniquement)
class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val intituleMemo = intent.getStringExtra("detail")

        // fragment :
        val fragment = DetailFragment()
        val bundle = Bundle()
        bundle.putString(DetailFragment.EXTRA_PARAM, intituleMemo)
        fragment.arguments = bundle

        // fragment manager :
        val fragmentManager = supportFragmentManager
        // transaction :
        val fragmentTransaction =
            fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.large_detail, fragment, "exemple2")
        fragmentTransaction.commit()
    }

    //Permet d'afficher dans un toast; A voir plus tard
    fun afficheNote(v: View) {
        Toast.makeText(this, v.txt.text, Toast.LENGTH_SHORT).show()
    }
}