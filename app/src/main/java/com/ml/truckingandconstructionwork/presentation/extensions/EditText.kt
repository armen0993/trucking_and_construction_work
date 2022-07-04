package com.ml.truckingandconstructionwork.presentation.extensions

import android.widget.EditText
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.parser.PhoneNumberUnderscoreSlotsParser
import ru.tinkoff.decoro.watchers.MaskFormatWatcher

fun EditText.mask(typeMask:String,text: EditText) {
    val slots =
        PhoneNumberUnderscoreSlotsParser().parseSlots(typeMask)
    val maskInput = MaskImpl.createTerminated(slots)
    val formatWatcher = MaskFormatWatcher(maskInput)
    formatWatcher.installOn(text)
}
