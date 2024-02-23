package com.example.registration

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.registration.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PizzaAdapter
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSearchView()
        setupRecyclerView()
    }

    private fun setupSearchView() {
        searchView = binding.search
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false // Let the search view handle the default behavior of query submission
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText?.lowercase(Locale.getDefault()))
                return true
            }
        })
    }

    private fun setupRecyclerView() {
        adapter = PizzaAdapter { pizza ->
            handlePizzaClick(pizza)
        }
        binding.listMy.adapter = adapter
        adapter.setData(PizzaData.list) // Assuming PizzaData.list is your dataset
    }

    private fun handlePizzaClick(pizza: Pizza) {
        val intent = Intent(this, MainActivity2::class.java).apply {
            putExtra(MainActivity2.KEY_PIZZA, pizza)
        }
        startActivity(intent)
    }

    private fun filterList(query: String?) {
        query?.let {
            val filteredList = PizzaData.list.filter { pizza ->
                pizza.title.lowercase(Locale.getDefault()).contains(query)
            }
            if (filteredList.isEmpty()) {
                binding.listMy.visibility = View.GONE

                showNoDataFragment()
            } else {
                binding.listMy.visibility = View.VISIBLE
                adapter.setFilteredList(ArrayList(filteredList))
                removeNoDataFragment()
            }
        }
    }

    private fun removeNoDataFragment() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment is NoDataFragment) {
            supportFragmentManager.beginTransaction()
                .remove(fragment)
                .commit()
        }
    }
    private fun showNoDataFragment() {
        val fragment = NoDataFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}

