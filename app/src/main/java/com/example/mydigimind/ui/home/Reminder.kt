package com.example.mydigimind.ui.home

import android.text.Editable
import java.io.Serializable

data class Reminder(var days:ArrayList<String>, var time:String, var reminder: String):Serializable