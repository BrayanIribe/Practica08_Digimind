package com.example.mydigimind.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.mydigimind.R


class ReminderAdapter: BaseAdapter {
    var reminder = ArrayList<Reminder>()
    var ctx: Context? = null

    constructor(ctx: Context?, reminder: ArrayList<Reminder>){
        this.ctx = ctx
        this.reminder = reminder
    }
    override fun getCount(): Int {
        return reminder.size
    }

    override fun getItem(p0: Int): Any {
        return reminder[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var reminder = reminder[p0]
        var inflator = ctx!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflator.inflate(R.layout.reminders, null)
        var txtDays = view.findViewById<TextView>(R.id.txtDays)
        var txtReminder = view.findViewById<TextView>(R.id.txtReminder)
        var txtTime = view.findViewById<TextView>(R.id.txtTime)
        txtDays.text = reminder.days.toString()
        txtReminder.text = reminder.reminder.toString()
        txtTime.text = reminder.time.toString()
        return view
    }

}