

//COMP3025-W24-Assignment1-Calculator-App-Interface-MainActivity.kt
//Nicolas Vicente
//200539594
//February 4, 2024
//This is the user interface section of the calculator app assignment and features the UI of the app which is designed to be practical featuring all the number buttons in the middle-right of the screen for right thumb usage and the operators on the left sid for left thumb usage, with less used button up top, along with basic button functionality, designed with the help of figma. \
//I revised the original design and idea slightly to better fit the android development environment and different user devices and orientation, such as the button sizes and positioning, as well as the font, going from fa_free5 to fa_solid900
//The activity main xml and style and dimens files handle the ui for the app.
//This design is the second design I came up with, I decided that it was more user friendly and better suited for mobile usage while the other one was better suited for pc usage due to its square buttons
//The program functionality was developed through the framework of the ICEs previously completed
//The program has been updated at various stages of development during major changes such as major ui changes, functionality addition, and completion
//Version 1.0




//imports and package statement
package ca.georgiancollege.comp3025_w24_assignment2_calculator_app
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import ca.georgiancollege.comp3025_w24_assignment2_calculator_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //view binding initialization
    private lateinit var binding: ActivityMainBinding

    //set the result label text view to nothing initially
    private var resultLabelValue: String = ""
    //on create method
    override fun onCreate(savedInstanceState: Bundle?) {
        //view binding setup
        binding = ActivityMainBinding.inflate(layoutInflater)


        super.onCreate(savedInstanceState)
        //set the content view to use view binding
        setContentView(binding.root)

        //call the onclick listeners for the calculator buttons which handles the proper function routing based on the button
        initializeOnClickListeners()
    }



    //function that contains the onclick listeners for all of teh buttons and calls the appropriate process function based on what button is clicked
    private fun initializeOnClickListeners() {
        //function/utility buttons
        binding.clearButton.setOnClickListener { view -> processExtraButtons(view) }
        binding.percentButton.setOnClickListener { view -> processOperatorButtons(view) }
        binding.backspaceButton.setOnClickListener { view -> processExtraButtons(view) }
        binding.plusMinusButton.setOnClickListener { view -> processExtraButtons(view) }
        //operator buttons onclick listeners
        binding.additionButton.setOnClickListener { view -> processOperatorButtons(view) }
        binding.subtractionButton.setOnClickListener { view -> processOperatorButtons(view) }
        binding.divisionButton.setOnClickListener { view -> processOperatorButtons(view) }
        binding.multiplicatonButton.setOnClickListener { view -> processOperatorButtons(view) }
        binding.equalsButton.setOnClickListener { view -> processOperatorButtons(view) }
        //number buttons onclick listeners
        binding.zeroButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.oneButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.twoButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.threeButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.fourButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.fiveButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.sixButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.sevenButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.eightButton.setOnClickListener { view -> processNumberButtons(view) }
        binding.nineButton.setOnClickListener { view -> processNumberButtons(view) }
        //decimal button
        binding.decimalButton.setOnClickListener { view -> processNumberButtons(view) }
    }


    //function to process the operator buttons based on what operator button is clicked
    private fun processOperatorButtons(view: View)
    {
        //log that the operator button was clicked
        Log.i("Operator Button", view.tag.toString())
        //append the value based on the button clicked to the result label variable
        resultLabelValue += view.tag.toString()
        //set the textview result to the appended value
        binding.resultLabel.text = resultLabelValue
    }


    //function to handle the extra/utility buttons based on which one was clicked using a when statement
    private fun processExtraButtons(view: View)
    {
        //when clear button is clicked, clear the entire result label text view
        when (view.tag.toString()) {
            "clear" -> {
                resultLabelValue = ""
            }
            //when backspace button is clicked, if result label is not empty, remove one value from it
            "backspace" -> {
                if (resultLabelValue.isNotEmpty()) {
                    resultLabelValue = resultLabelValue.substring(0, resultLabelValue.length - 1)
                }
            }


            //when plus minus is clicked, if its already negative, remove negative sign, else prepend negative sign
            "+-" -> {
                resultLabelValue = if (resultLabelValue.startsWith("-")) {
                    resultLabelValue.substring(1)
                } else {
                    "-$resultLabelValue"
                }
            }
        }


        //log and reset the text of the result label
        Log.i("extra buttons", view.tag.toString())
        binding.resultLabel.text = resultLabelValue
    }


    //function to handle the number buttons
    private fun processNumberButtons(view: View)
    {
        //check if number pressed is decimal, if it doesn't already have a decimal, add one
        if (view.tag.toString() == ".") {
            if (!resultLabelValue.contains(".")) {
                resultLabelValue += view.tag.toString()
            }
        } else {
            resultLabelValue += view.tag.toString()
        }


        //log and set the result label text view appropriately
        Log.i("number buttons", view.tag.toString())
        binding.resultLabel.text = resultLabelValue
    }
}