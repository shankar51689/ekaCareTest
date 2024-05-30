package com.ms.ekacaretest

import android.annotation.SuppressLint
import com.ms.ekacaretest.database.PersonDetails
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ms.ekacaretest.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PersonViewModel
    private lateinit var mAdapter: PersonListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setViewModel()
        setClickListener()
        setAdapter()
        viewModel.getPersonList()
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application))[PersonViewModel::class.java]

        with(viewModel){
            liveDataPersonDetailsList.observe(this@MainActivity, personListObserver)
        }
    }

    private fun setClickListener() {

        with(binding) {

            btnSubmit.setOnClickListener {
                if (isReadyToGo()) {

                    viewModel.insertData(
                        PersonDetails(
                            name = etName.text.toString(),
                            age = etAge.text.toString(),
                            address = etAddress.text.toString(),
                            dob = etDob.text.toString()
                        )
                    )

                    hideKeyboard(this@MainActivity)

                    Toast.makeText(this@MainActivity, "Data successfully Insert", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this@MainActivity, "Please Fill all the Field!", Toast.LENGTH_SHORT).show()
                }
            }

            etDob.setOnClickListener {
                showDatePickerDialog()
            }
        }
    }

    private fun setAdapter() {
        mAdapter = PersonListAdapter(emptyList())
        binding.rv.apply {
            adapter         = mAdapter
            layoutManager   = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }


    private val personListObserver = Observer<List<PersonDetails>> { data ->
        cleanFields()
        mAdapter.updateData(data)
        mAdapter.notifyDataSetChanged()
    }

    private fun showDatePickerDialog() {


        val calendar    = Calendar.getInstance()
        val year        = calendar.get(Calendar.YEAR)
        val month       = calendar.get(Calendar.MONTH)
        val day         = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "${selectedDay}/${selectedMonth + 1}/${selectedYear}"
                binding.etDob.setText(selectedDate)
            },
            year, month, day)

        datePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
        datePickerDialog.show()
    }

    fun getPreviousDateYMD(days: Int): String {
        val cal = Calendar.getInstance()
        cal.time = Date()
        cal.add(Calendar.DATE, -days)
        val date = cal.time
        @SuppressLint("SimpleDateFormat") val df =
            SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
        return df.format(date)
    }

    private fun cleanFields() {
        with(binding) {
            etName.setText("")
            etAge.setText("")
            etAddress.setText("")
            etDob.setText("")
        }
    }

    private fun isReadyToGo():Boolean {
        with(binding) {
            if (etName.text?.isEmpty() == true) {
                return false
            } else if (etAge.text?.isEmpty() == true) {
                return false
            } else if (etDob.text?.isEmpty() == true) {
                return false
            } else if (etAddress.text?.isEmpty() == true) {
                return false
            } else {
                return true
            }
        }
    }

}