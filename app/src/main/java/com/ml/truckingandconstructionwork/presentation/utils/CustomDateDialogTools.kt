package com.ml.truckingandconstructionwork.presentation.utils

import android.app.DatePickerDialog
import android.content.Context
import android.widget.EditText
import com.ml.truckingandconstructionwork.R
import java.text.SimpleDateFormat
import java.util.*

object CustomDateDialogTools {
    private lateinit var calendar: Calendar
    private lateinit var currentFormatDate: String


    fun createDateDialog(context: Context, formatDate: String, editDateText: EditText) {
        calendar = Calendar.getInstance()
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, monthOfYear)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView(formatDate, editDateText)
            }
        val dialog = DatePickerDialog(
            context, R.style.my_dialog_theme, dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        dialog.show()
    }

    //  myFormat = "dd.MM.yyyy"
    private fun updateDateInView(formatDate: String, editDateText: EditText) {
        val simpleDateFormat = SimpleDateFormat(formatDate, Locale.US)
        currentFormatDate = simpleDateFormat.format(calendar.time)
        editDateText.setText(currentFormatDate)
    }
}