package com.example.mydigimind.ui.home

import java.io.Serializable

class Board: Serializable {
    var Reminders = ArrayList<Reminder>()

    fun Add(p:Reminder):Boolean{
        return Reminders.add(p)
    }

}