package com.example.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.registration.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {

    companion object {
        const val KEY_PIZZA = "KEY_PIZZA"
    }

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val pizza = intent.getSerializableExtra(KEY_PIZZA) as? Pizza

        pizza?.let {
            binding.title.text = it.title
            binding.pizzaImage.setImageResource(it.imageRes)
           // val details = "Ingredients: ${it.description}\nPrice: ${it.price}"
            binding.pizzaDetails.text = it.description
            binding.button.text="В корзину за ${it.price}"
        }
    }
}
