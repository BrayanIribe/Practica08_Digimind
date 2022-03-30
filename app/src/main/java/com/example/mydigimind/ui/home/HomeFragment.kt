package com.example.mydigimind.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mydigimind.R

class HomeFragment : Fragment() {
    private lateinit var homeViewModel:HomeViewModel
    private var adapter: ReminderAdapter? = null
    private var board = Board()
    companion object{
        var tasks = ArrayList<Reminder>()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_home,container,false)
        homeViewModel.text.observe(viewLifecycleOwner, Observer{

        })

        val gridView = root.findViewById<GridView>(R.id.gridView)
        gridView.adapter = ReminderAdapter(context,board.Reminders)

        if (board.IsEmpty()) {
            fillTask()
            for (t in tasks){
                board.Add(t)
            }
        }




        return root
    }

    fun fillTask(){
        tasks.add(Reminder(arrayListOf("Tuesday"),"17:30","Reminder 1"))
        tasks.add(Reminder(arrayListOf("Monday","Tuesday"),"17:30","Reminder 2"))
        tasks.add(Reminder(arrayListOf("Wednesday"),"17:30","Reminder 3"))
        tasks.add(Reminder(arrayListOf("Wednesday"),"17:30","Reminder 4"))
        tasks.add(Reminder(arrayListOf("Friday"),"17:30","Reminder 5"))
        tasks.add(Reminder(arrayListOf("Wednesday"),"17:30","Reminder 6"))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var gridView = view.findViewById<GridView>(R.id.gridView)
        setFragmentResultListener("key") { _, bundle ->
            val result: Reminder = bundle.getSerializable("reminder") as Reminder
            board.Add(result)
            gridView.adapter = ReminderAdapter(context,board.Reminders)
        }

    }

}