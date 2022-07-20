package com.ml.truckingandconstructionwork.presentation.utils

import android.app.TimePickerDialog
import android.content.Context
import android.widget.TimePicker
import com.ml.truckingandconstructionwork.R
import java.lang.reflect.Field
import java.text.DateFormat
import java.util.Calendar


class RangeTimePickerDialog(
    context: Context?,
    callBack: OnTimeSetListener?,
    hourOfDay: Int,
    minute: Int,
    is24HourView: Boolean
) :
    TimePickerDialog(context, R.style.my_dialog_date, callBack, hourOfDay, minute, is24HourView) {
    private var minHour = -1
    private var minMinute = -1
    private var maxHour = 25
    private var maxMinute = 25
    private var currentHour = 0
    private var currentMinute = 0
    private val calendar = Calendar.getInstance()
    private val dateFormat: DateFormat

    fun setMin(hour: Int, minute: Int) {
        minHour = hour
        minMinute = minute

        val min = Calendar.getInstance()
        val existing = Calendar.getInstance()

        min[Calendar.HOUR_OF_DAY] = minHour
        min[Calendar.MINUTE] = minMinute

        existing[Calendar.HOUR_OF_DAY] = currentHour
        existing[Calendar.MINUTE] = currentMinute

        if (existing.before(min)) {
            currentHour = minHour
            currentMinute = minMinute
            updateTime(currentHour, currentMinute)
        }
    }

    fun setMax(hour: Int, minute: Int) {
        maxHour = hour
        maxMinute = minute

        val max = Calendar.getInstance()
        val existing = Calendar.getInstance()

        max[Calendar.HOUR_OF_DAY] = maxHour
        max[Calendar.MINUTE] = maxMinute

        existing[Calendar.HOUR_OF_DAY] = currentHour
        existing[Calendar.MINUTE] = currentMinute

        if (existing.after(max)) {
            currentHour = maxHour
            currentMinute = maxMinute
            updateTime(currentHour, currentMinute)
        }
    }

    override fun onTimeChanged(view: TimePicker, hourOfDay: Int, minute: Int) {
        var validTime = true
        if (hourOfDay < minHour || (hourOfDay == minHour && minute < minMinute)) {
            validTime = false
        }
        if (hourOfDay > maxHour || (hourOfDay == maxHour && minute > maxMinute)) {
            validTime = false
        }
        if (validTime) {
            currentHour = hourOfDay
            currentMinute = minute
        }
        updateTime(currentHour, currentMinute)
        updateDialogTitle(currentHour, currentMinute)
    }

    private fun updateDialogTitle(hourOfDay: Int, minute: Int) {
        calendar[Calendar.HOUR_OF_DAY] = hourOfDay
        calendar[Calendar.MINUTE] = minute
        val title = dateFormat.format(calendar.time)
        setTitle(title)
    }

    init {
        currentHour = hourOfDay
        currentMinute = minute
        dateFormat = DateFormat.getTimeInstance(DateFormat.SHORT)
        try {
            val superclass: Class<*> = RangeTimePickerDialog::class.java
            val mTimePickerField: Field = superclass.getDeclaredField("mTimePicker")
            mTimePickerField.isAccessible = true
            val mTimePicker = mTimePickerField.get(this) as TimePicker
            mTimePicker.setOnTimeChangedListener(this)
        } catch (e: NoSuchFieldException) {
        } catch (e: IllegalArgumentException) {
        } catch (e: IllegalAccessException) {
        }
    }
}