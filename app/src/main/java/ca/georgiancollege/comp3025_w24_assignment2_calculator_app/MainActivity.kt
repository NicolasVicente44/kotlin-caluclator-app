//COMP3025-W24-Assignment2-Calculator-App-MainActivity.kt
//Nicolas Vicente
//200539594
//February 11, 2024

package ca.georgiancollege.comp3025_w24_assignment2_calculator_app


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ca.georgiancollege.comp3025_w24_assignment2_calculator_app.databinding.ActivityMainBinding
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var calculator: Calculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the calculator property with a new instance of the Calculator class,
        // passing the binding object as a parameter
        calculator = Calculator(binding)
    }
}
