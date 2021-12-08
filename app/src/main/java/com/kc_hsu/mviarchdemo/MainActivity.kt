package com.kc_hsu.mviarchdemo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kc_hsu.mviarchdemo.data.ApiClient
import com.kc_hsu.mviarchdemo.data.Users
import com.kc_hsu.mviarchdemo.databinding.ActivityMainBinding
import com.kc_hsu.mviarchdemo.ui.MainAdapter
import com.kc_hsu.mviarchdemo.ui.MainState
import com.kc_hsu.mviarchdemo.ui.MainViewModel
import com.kc_hsu.mviarchdemo.ui.MainViewModelFactory
import com.kc_hsu.mviarchdemo.ui.intent.MainIntent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var mainViewModel: MainViewModel
    private var mainAdapter = MainAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupUI()
        setupViewModel()
        observeFromState()
    }

    private fun setupUI() {
        with(binding!!.recyclerView) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.HORIZONTAL))
        }

        binding!!.buttonFetchUser.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(ApiClient.apiService)
        )[MainViewModel::class.java]
    }

    private fun observeFromState() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        binding!!.apply {
                            buttonFetchUser.visibility = View.GONE
                            progressBar.visibility = View.VISIBLE
                        }
                    }
                    is MainState.Users -> {
                        binding!!.apply {
                            progressBar.visibility = View.GONE
                            buttonFetchUser.visibility = View.GONE
                        }
                        addList(it.users.data)
                    }
                    is MainState.Error -> {
                        binding!!.apply {
                            progressBar.visibility = View.GONE
                            buttonFetchUser.visibility = View.VISIBLE
                            Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

    private fun addList(users: List<Users.User>) {
        binding?.recyclerView?.visibility = View.VISIBLE
        mainAdapter.addData(users)
        mainAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}