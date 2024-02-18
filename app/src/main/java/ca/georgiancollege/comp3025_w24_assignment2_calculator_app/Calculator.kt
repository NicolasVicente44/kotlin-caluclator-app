package ca.georgiancollege.comp3025_w24_assignment2_calculator_app

import android.view.View
import ca.georgiancollege.comp3025_w24_assignment2_calculator_app.databinding.ActivityMainBinding


/**
 * The calculator class represents the calculator programmatically and its connection to the UI via the onclick listeners, performing calculations and outputting them to the results view
 */
class Calculator(binding: ActivityMainBinding)
{

    /**
     * calcualtor class instance variables
     */
    private var m_resultLabelValue: String
    private var m_binding: ActivityMainBinding
    private var m_lhs: String
    private var m_active_operation: String


    /**
     * instance variable initialization
     */
    init
    {
        this.m_binding = binding
        this.m_resultLabelValue = ""
        this.m_lhs = ""
        this.m_active_operation = ""

        initializeOnClickListeners()
    }

    /**
     * function to map the buttons from the ui to the funcitons associated with their functionality/to set teh onclick listeners for them
     */
    private fun initializeOnClickListeners() {

        //function/utility buttons
        this.m_binding.clearButton.setOnClickListener { view -> processExtraButtons(view) }
        this.m_binding.backspaceButton.setOnClickListener { view -> processExtraButtons(view) }
        this.m_binding.plusMinusButton.setOnClickListener { view -> processExtraButtons(view) }
        //operator buttons onclick listeners
        this.m_binding.additionButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.subtractionButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.divisionButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.multiplicationButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.equalsButton.setOnClickListener { view -> processOperatorButtons(view) }
        this.m_binding.percentButton.setOnClickListener { view -> processOperatorButtons(view) }
        //number buttons onclick listeners
        this.m_binding.zeroButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.oneButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.twoButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.threeButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.fourButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.fiveButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.sixButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.sevenButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.eightButton.setOnClickListener { view -> processNumberButtons(view) }
        this.m_binding.nineButton.setOnClickListener { view -> processNumberButtons(view) }
        //decimal button
        this.m_binding.decimalButton.setOnClickListener { view -> processNumberButtons(view) }


    }


    /**
     * function to handle what happen when the equals button is pressed based on the current operator, calling the appropriate function associated with that operator
     */
    private fun processEqualsButton() {
        if (m_lhs.isNotEmpty() && m_resultLabelValue.isNotEmpty() && m_active_operation.isNotEmpty()) {
            // Perform calculation based on the last operator selected
            when (m_active_operation) {
                "×" -> {
                    m_lhs = multiply(m_lhs, m_resultLabelValue)
                }
                "÷" -> {
                    if (m_resultLabelValue != "0") {
                        m_lhs = divide(m_lhs, m_resultLabelValue)
                    } else {
                        // handle division by zero error
                        m_lhs = "Err"
                    }
                }
                "+" -> {
                    m_lhs = add(m_lhs, m_resultLabelValue)
                }
                "-" -> {
                    m_lhs = subtract(m_lhs, m_resultLabelValue)
                }
                "%" -> {
                    m_lhs = calculatePercent(m_lhs, m_resultLabelValue, m_active_operation)
                }
            }
            // Update the result label
            m_binding.resultLabel.text = m_lhs
            m_resultLabelValue = ""
        }
    }

    /**
     * Processes the operator buttons when clicked.
     * @param view The clicked view.
     */
    private fun processOperatorButtons(view: View) {
        when (view.tag.toString()) {
            // if the clicked operator is multiplication, division, addition, or subtraction
            "×", "÷", "+", "-" -> {
                if (m_lhs.isEmpty() && m_resultLabelValue.isNotEmpty()) {
                    // if the left-hand side is empty and result label has a value,
                    // set the left-hand side to the result label value,
                    // clear the result label value, and set the active operation.
                    m_lhs = m_resultLabelValue
                    m_resultLabelValue = ""
                    m_active_operation = view.tag.toString()
                } else if (m_lhs.isNotEmpty() && m_resultLabelValue.isEmpty()) {
                    // if the left-hand side has a value and result label is empty,
                    // update the active operation.
                    m_active_operation = view.tag.toString()
                } else if (m_lhs.isNotEmpty() && m_resultLabelValue.isNotEmpty()) {
                    // if both left-hand side and result label have values,
                    // calculate the result based on the last operator selected,
                    // and update the active operation.
                    processEqualsButton()
                    m_active_operation = view.tag.toString()
                }
            }
            "%" -> {
                if (m_lhs.isNotEmpty() && m_resultLabelValue.isNotEmpty()) {
                    // Calculate percentage based on the previous operator
                    m_resultLabelValue =
                        calculatePercent(m_lhs, m_resultLabelValue, m_active_operation)
                    m_lhs = ""
                    m_active_operation = ""
                    m_binding.resultLabel.text = m_resultLabelValue
                }
            }
            // If the clicked operator is equals
            "equals" -> {
                // Process equals button
                processEqualsButton()
            }
        }
    }


    /**
     * function to handle the extra/utility buttons calling the appropriate functions or performing the correct action based on the button pressed
     */
    private fun processExtraButtons(view: View)
    {
        when(view.tag.toString())
        {
            "backspace" ->
            {

                this.m_resultLabelValue = this.m_resultLabelValue.dropLast(1)
                this.m_binding.resultLabel.text = this.m_resultLabelValue
                if(this.m_resultLabelValue.isEmpty() || this.m_resultLabelValue == "-")
                {
                    this.m_resultLabelValue = ""
                    this.m_binding.resultLabel.text = "0"
                }
            }
            "clear" ->
            {
                clear()
            }
            "+-" ->
            {
                // Handle plus-minus button
                if (m_resultLabelValue.isNotEmpty() && m_binding.resultLabel.text != "0") {
                    // Toggle sign of the result label value
                    m_resultLabelValue = if (m_resultLabelValue.startsWith("-")) {
                        m_resultLabelValue.substring(1)
                    } else {
                        "-$m_resultLabelValue"
                    }
                    m_binding.resultLabel.text = m_resultLabelValue
                }
            }
        }
    }


    /**
     * function to handle the processing of the number buttons being pressed, handles the case in which the button pressed is the decimal button, and prepends a 0 if required
     */
    private fun processNumberButtons(view: View)
    {
        when (view.tag.toString())
        {
            "." -> {
                if(!this.m_resultLabelValue.contains("."))
                {
                    if(this.m_resultLabelValue.isEmpty())
                    {
                        this.m_resultLabelValue = "0."
                    }
                    else
                    {
                        this.m_resultLabelValue += view.tag.toString()
                    }
                }
            }
            else -> {
                this.m_resultLabelValue += view.tag.toString()
            }
        }

        this.m_binding.resultLabel.text = this.m_resultLabelValue
    }

    /**
     * This function clears the in-memory values for the LHS and the RHS
     *
     * @return [Unit]
     */
    private fun clear():Unit
    {
        this.m_resultLabelValue = ""
        this.m_lhs = ""
        this.m_active_operation = ""
        this.m_binding.resultLabel.text = "0"
    }

    /**
     * This function subtracts the rhs from the lhs and returns a string representation of the result
     *
     * @param lhs [String]
     * @param rhs [String]
     * @return [String]
     */
    private fun subtract(lhs: String, rhs: String): String
    {
        var LHS = lhs
        var RHS = rhs

        if(LHS.isEmpty())
        {
            LHS = "0"
        }

        if(RHS.isEmpty())
        {
            RHS = "0"
        }

        if(LHS.contains(".") || RHS.contains("."))
        {
            return (LHS.toFloat() - RHS.toFloat()).toString()
        }
        return (LHS.toInt() - RHS.toInt()).toString()
    }

    /**
     * This function adds the lhs to the rhs and returns a string representation of the result
     *
     * @param lhs [String]
     * @param rhs [String]
     * @return [String]
     */
    private fun add(lhs: String, rhs: String): String
    {
        var LHS = lhs
        var RHS = rhs

        if(LHS.isEmpty())
        {
            LHS = "0"
        }

        if(RHS.isEmpty())
        {
            RHS = "0"
        }

        if(LHS.contains(".") || RHS.contains("."))
        {
            return (LHS.toFloat() + RHS.toFloat()).toString()
        }
        return (LHS.toInt() + RHS.toInt()).toString()
    }



    /**
     * This function multiplies the lhs to the rhs and returns a string representation of the result
     *
     * @param lhs [String]
     * @param rhs [String]
     * @return [String]
     */
    private fun multiply(lhs: String, rhs: String): String
    {
        var LHS = lhs
        var RHS = rhs

        if(LHS.isEmpty())
        {
            LHS = "0"
        }

        if(RHS.isEmpty())
        {
            RHS = "0"
        }

        if(LHS.contains(".") || RHS.contains("."))
        {
            return (LHS.toFloat() * RHS.toFloat()).toString()
        }
        return (LHS.toInt() * RHS.toInt()).toString()
    }


    /**
     * This function divides the lhs to the rhs and returns a string representation of the result
     *
     * @param lhs [String]
     * @param rhs [String]
     * @return [String]
     */
    private fun divide(lhs: String, rhs: String): String
    {
        var LHS = lhs
        var RHS = rhs

        if(LHS.isEmpty())
        {
            LHS = "0"
        }

        if(RHS.isEmpty())
        {
            RHS = "0"
        }

        if(LHS.contains(".") || RHS.contains("."))
        {
            return (LHS.toFloat() / RHS.toFloat()).toString()
        }
        return (LHS.toInt() / RHS.toInt()).toString()
    }


    /**
     * percent calculation function that determines how to apply percent based on the operator used within the same expression, ie dividing by a percent or adding a percent
     */
    private fun calculatePercent(lhs: String, rhs: String, operation: String): String {
        val value = lhs.toFloatOrNull() ?: return "Err"
        val percent = rhs.toFloatOrNull() ?: return "Err"

        // perform calculation based on the previous operator
        return when (operation) {
            "+" -> (value + value * percent / 100).toString()
            "-" -> (value - value * percent / 100).toString()
            "×" -> (value * (1 + percent / 100)).toString()
            "÷" -> if (percent != 0f) (value / (1 + percent / 100)).toString() else "Err"
            else -> "Err"
        }
    }

}