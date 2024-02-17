package ca.georgiancollege.comp3025_w24_assignment2_calculator_app

import android.view.View
import ca.georgiancollege.comp3025_w24_assignment2_calculator_app.databinding.ActivityMainBinding




class Calculator(binding: ActivityMainBinding)
{
    private var m_resultLabelValue: String
    private var m_binding: ActivityMainBinding
    private var m_lhs: String
    private var m_active_operation: String

    init
    {
        this.m_binding = binding
        this.m_resultLabelValue = ""
        this.m_lhs = ""
        this.m_active_operation = ""

        initializeOnClickListeners()
    }

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

    private fun processOperatorButtons(view: View)
    {
        if(m_lhs.isEmpty() && this.m_resultLabelValue.isNotEmpty())
        {
            this.m_lhs = this.m_resultLabelValue
            this.m_resultLabelValue = ""
            this.m_active_operation = view.tag.toString()
        }
        else if(this.m_lhs.isNotEmpty() && this.m_resultLabelValue.isEmpty())
        {
            this.m_active_operation = view.tag.toString()
        }
        else if(this.m_lhs.isNotEmpty() && this.m_resultLabelValue.isNotEmpty())
        {
            // compute the result based ont the last operator selected
            when(this.m_active_operation) {
                "multiply" -> {

                }

                "divide" -> {

                }

                "+" -> {
                    this.m_lhs = add(this.m_lhs, this.m_resultLabelValue)
                    this.m_resultLabelValue = ""
                    this.m_binding.resultLabel.text = this.m_lhs;
                }

                "-" -> {
                    this.m_lhs = subtract(this.m_lhs, this.m_resultLabelValue)
                    this.m_resultLabelValue = ""
                    this.m_binding.resultLabel.text = this.m_lhs;
                }

                "equals" -> {

                }
            }
            // update the last operation
            this.m_active_operation = view.tag.toString()
        }

    }

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
                if(this.m_resultLabelValue.isNotEmpty())
                {
                    if(this.m_resultLabelValue.contains("-"))
                    {
                        this.m_resultLabelValue = this.m_resultLabelValue.removePrefix("-")
                    }
                    else
                    {
                        this.m_resultLabelValue = "-" + this.m_resultLabelValue
                    }
                    this.m_binding.resultLabel.text = this.m_resultLabelValue
                }
            }
        }
    }

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


}