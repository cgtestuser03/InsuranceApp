package com.capg.insurance.ui.view

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.RadioButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.capg.insurance.R
import com.capg.insurance.databinding.FragmentNewBinding
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import androidx.core.widget.*
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
open class FormFragment : Fragment() , View.OnClickListener, OnDateSetListener{

    private var _binding: FragmentNewBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val defaultButtonTintColor = "#DFDCDA"
    private val onFormValidButtonTintColor = "#2b81b2"
    private var errorMessage: String? = null
    private var isFormValid = false

    private val name = MutableStateFlow("")
    private val dob = MutableStateFlow("")
    private val phone = MutableStateFlow("")
    private val gender = MutableStateFlow(false)

    // Form validation
    private val formIsValid = combine(
        name,
        dob,
        phone,
        gender
    ) { name, dob, phone, gender ->
        _binding!!.txtErrorMessage.text = ""
        val nameIsValid = name.length > 3
        val dobIsValid = dob.isNotEmpty()
        val phoneIsValid = phone.length == 10
        val genderIsValid = gender
        errorMessage = when {
            nameIsValid.not() -> "Name not valid"
            dobIsValid.not() -> "Date of birth not valid"
            phoneIsValid.not() -> "Phone number not valid"
            genderIsValid.not() -> "Gender not selected"
            else -> null
        }
        errorMessage?.let {
            _binding!!.txtErrorMessage.text = it
        }
        nameIsValid and dobIsValid and phoneIsValid and genderIsValid
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding!!.radioFemale.setOnClickListener(this)
        _binding!!.radioMale.setOnClickListener(this)

        // OnTextChanged listener for Text fields
        binding.editName.doOnTextChanged { text, _, _, _ ->
            name.value = text.toString()
        }
        binding.editDate.doOnTextChanged { text, _, _, _ ->
            dob.value = text.toString()
        }
        binding.editPhone.doOnTextChanged { text, _, _, _ ->
            phone.value = text.toString()
        }

        // Date picker for Date of birth
        binding.editDate.setOnClickListener {
            val datePickerDialog = context?.let { it1 ->
                DatePickerDialog(
                    it1, this,
                    Calendar.getInstance()[Calendar.YEAR],
                    Calendar.getInstance()[Calendar.MONTH],
                    Calendar.getInstance()[Calendar.DAY_OF_MONTH]
                )
            }
            datePickerDialog!!.show()
        }

        // CoroutineScope for button enable after form validation
        lifecycleScope.launch {
            formIsValid.collect {
                _binding!!.btnQuote.apply {
                    backgroundTintList = ColorStateList.valueOf(
                        Color.parseColor(
                            if (it) onFormValidButtonTintColor else defaultButtonTintColor
                        )
                    )
                    isFormValid = it
                }
            }
        }

        // Form submit
        binding.btnQuote.setOnClickListener {
            if(isFormValid){
                findNavController().navigate(R.id.action_NewFormFragment_to_ListFragment)
            }
        }
    }

    // Checkbox onclick event
    override fun onClick(view: View?) {
        // Check if button currently checked
        val checked = (view as RadioButton).isChecked
        if (checked) {
            gender.value = checked
            var gender = view.text
        }else{
            gender.value = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDateSet(view: DatePicker?, y: Int, m: Int, d: Int) {

        // If done picking date
        val date: String = d.toString() + "/" + (m + 1) + "/" + y
        _binding!!.editDate.setText(date)
    }
}

