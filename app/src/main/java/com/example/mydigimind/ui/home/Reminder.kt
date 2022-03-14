package com.example.mydigimind.ui.home

import android.text.Editable
import java.io.Serializable

data class Reminder(var days:String, var time:String, var reminder: Editable):Serializable