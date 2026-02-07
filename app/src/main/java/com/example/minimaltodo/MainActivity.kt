package com.example.minimaltodo

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.minimaltodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tasks = mutableListOf<String>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            tasks
        )
        binding.taskList.adapter = adapter

        binding.addButton.setOnClickListener { addTask() }
        binding.taskInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addTask()
                true
            } else {
                false
            }
        }

        binding.taskList.setOnItemLongClickListener { _, _, position, _ ->
            tasks.removeAt(position)
            adapter.notifyDataSetChanged()
            true
        }
    }

    private fun addTask() {
        val task = binding.taskInput.text.toString().trim()
        if (task.isNotEmpty()) {
            tasks.add(task)
            adapter.notifyDataSetChanged()
            binding.taskInput.text?.clear()
        }
    }
}
