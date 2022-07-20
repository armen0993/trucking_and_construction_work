package com.ml.truckingandconstructionwork.presentation.utils

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointBackward
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import com.ml.truckingandconstructionwork.R
import com.ml.truckingandconstructionwork.presentation.utils.Constants.FORMAT_DATE
import com.ml.truckingandconstructionwork.presentation.utils.Constants.TAG_DATE_PICKER
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.TimeZone
import java.util.Calendar


object MaterialDateDialog {

    private var currentSelectedDate: Long? = null

    @RequiresApi(Build.VERSION_CODES.O)
    fun showDateDialogStartTrip(fragmentManager: FragmentManager, editText: EditText) {
        val today = MaterialDatePicker.todayInUtcMilliseconds()

        val constrainBuilder =
            CalendarConstraints.Builder()
                .setValidator(DateValidatorPointForward.from(today))

        MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date start trip")
            .setTheme(R.style.Material_Date_Picker)
            .setCalendarConstraints(constrainBuilder.build())
            .build().apply {
                addOnPositiveButtonClickListener { dateInMillis ->
                    onDateSelected(dateInMillis, editText)

                }
            }.show(fragmentManager, TAG_DATE_PICKER)


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onDateSelected(dateTimeStampInMillis: Long, editText: EditText) {
        currentSelectedDate = dateTimeStampInMillis
        val dateTime: LocalDateTime = LocalDateTime.ofInstant(
            Instant.ofEpochMilli(
                currentSelectedDate!!
            ), ZoneId.systemDefault()
        )
        val dateAsFormattedText: String = dateTime.format(DateTimeFormatter.ofPattern(FORMAT_DATE))
        editText.setText(dateAsFormattedText)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun showDateValid18Year(fragmentManager: FragmentManager, editText: EditText) {

        val yearEndDateOfBirth = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        yearEndDateOfBirth.add(Calendar.YEAR, -19)
        yearEndDateOfBirth.add(Calendar.DATE, 1)
        yearEndDateOfBirth.add(Calendar.MONTH, 1)
        val yearStartDateOfBirth = yearEndDateOfBirth.clone() as Calendar
        yearStartDateOfBirth.add(Calendar.YEAR, -100)

        val constrainBuilder =
            CalendarConstraints.Builder()
                .apply {
                    setValidator(DateValidatorPointBackward.before(yearEndDateOfBirth.timeInMillis))
                    setStart(yearStartDateOfBirth.timeInMillis)
                    setEnd(yearEndDateOfBirth.timeInMillis)
                    setOpenAt(yearEndDateOfBirth.timeInMillis)
                }

        MaterialDatePicker.Builder.datePicker().apply {
            setTitleText("Select your date of birth ")
            setSelection(yearEndDateOfBirth.timeInMillis)
            setTheme(R.style.Material_Date_Picker)
            setCalendarConstraints(constrainBuilder.build())
            build().apply {
                addOnPositiveButtonClickListener { dateInMillis ->
                    onDateSelected(dateInMillis, editText)
                }
            }.show(fragmentManager, TAG_DATE_PICKER)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun createTimePickerDialog(
        context: Context,
        formatTime: String,
        editText: EditText,
        currentDate: String
    ) {
        val calendar = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
            calendar.apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
            }

            editText.setText(SimpleDateFormat(formatTime).format(calendar.time))
        }

        val timPickerStartTrip =
            RangeTimePickerDialog(
                context,
                timeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )

        val formatToday = SimpleDateFormat(FORMAT_DATE).format(calendar.timeInMillis)

        when (currentDate == formatToday) {
            true -> {
                timPickerStartTrip.setMin(
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE) + 30
                )
                timPickerStartTrip.show()
            }
            else -> {
                timPickerStartTrip.show()
            }
        }
    }
}
