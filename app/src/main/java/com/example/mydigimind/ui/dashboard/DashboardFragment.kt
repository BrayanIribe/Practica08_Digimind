package com.example.mydigimind.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mydigimind.R
import com.example.mydigimind.ui.home.Reminder
import com.example.mydigimind.TimePickerFragment

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var btnSetTime : Button
    private lateinit var btnSave : Button
    private lateinit var inputRemember : EditText
    private lateinit var txtPickedHour : TextView
    private lateinit var checkMonday : CheckBox
    private lateinit var checkTuesday : CheckBox
    private lateinit var checkWednesday : CheckBox
    private lateinit var checkThursday : CheckBox
    private lateinit var checkFriday : CheckBox
    private lateinit var checkSaturday : CheckBox
    private lateinit var checkSunday : CheckBox

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard,container,false)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSetTime = view.findViewById(R.id.btnSetTime)
        btnSave = view.findViewById(R.id.btnSave)
        inputRemember = view.findViewById(R.id.inputRemember)
        txtPickedHour = view.findViewById(R.id.txtHour)
        checkMonday = view.findViewById(R.id.checkMonday)
        checkTuesday = view.findViewById(R.id.checkTuesday)
        checkWednesday = view.findViewById(R.id.checkWednesday)
        checkThursday = view.findViewById(R.id.checkThursday)
        checkFriday = view.findViewById(R.id.checkFriday)
        checkSaturday = view.findViewById(R.id.checkSaturday)
        checkSunday = view.findViewById(R.id.checkSunday)

        btnSetTime.setOnClickListener {
            val timePicker = TimePickerFragment{
                txtPickedHour.text = it
            }
            timePicker.show(parentFragmentManager,"time")
        }

        btnSave.setOnClickListener {
            val bundle = Bundle()
            var rememberTxt = inputRemember.text
            var timeTxt = txtPickedHour.text.toString()
            var daysList = ArrayList<String>()

            if(checkMonday.isChecked) {
                daysList.add("Mon")
            }

            if(checkTuesday.isChecked) {
                daysList.add("Tue")
            }

            if(checkWednesday.isChecked) {
                daysList.add("Wed")
            }

            if(checkThursday.isChecked) {
                daysList.add("Thu")
            }

            if(checkFriday.isChecked) {
                daysList.add("Fri")
            }
            if(checkSaturday.isChecked) {
                daysList.add("Sat")
            }
            if(checkSunday.isChecked){
                daysList.add("Sun")
            }

            // 7 = All week days
            if(daysList.size == 7) {
                daysList.clear()
                daysList.add("Everyday")
            }

            val daysTxt = daysList.joinToString(",")
            bundle.putSerializable("reminder", Reminder(daysTxt,timeTxt,rememberTxt))
            setFragmentResult("key",bundle)
            resetForm()

        }
    }

    private fun resetForm(){
        txtPickedHour.text = ""
        inputRemember.setText("")
        checkMonday.isChecked = false
        checkTuesday.isChecked = false
        checkWednesday.isChecked = false
        checkThursday.isChecked = false
        checkFriday.isChecked = false
        checkSaturday.isChecked = false
        checkSunday.isChecked = false
    }

}