package com.example.registration

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.databinding.ItemsBinding

class PizzaAdapter(
    private val onPizzaClick: (Pizza) -> Unit
) : RecyclerView.Adapter<PizzaAdapter.ViewHolder>() {
    private var pizzaList: ArrayList<Pizza> = ArrayList()

    fun setData(pizzas: ArrayList<Pizza>) {
        pizzaList.clear()
        pizzaList.addAll(pizzas)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pizza: Pizza) {
            with(binding) {
                titles.text = pizza.title
                image.setImageResource(pizza.imageRes)
                price.text = pizza.price
                description.text = pizza.description

                root.setOnClickListener {
                    onPizzaClick(pizza)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return pizzaList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pizza = pizzaList[position]
        holder.bind(pizza)
    }
    fun setFilteredList(list: List<Pizza>){
        this.pizzaList =ArrayList(list)
        notifyDataSetChanged()
    }
}

