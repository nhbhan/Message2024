package com.hannhb.message.core.ui.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.hannhb.message.databinding.CoreActivityBinding

abstract class NavigationActivity : AppCompatActivity() {

    abstract fun getNavGraphId(): Int

    open fun startDestinationId(): Int? = null

    open fun startDestinationArgs(): Bundle? = null

    lateinit var binding: CoreActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CoreActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavGraph()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupNavGraph() {
        val navHostFragment = supportFragmentManager.findFragmentById(com.hannhb.message.R.id.nav_host_fragment) as NavHostFragment
        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(getNavGraphId())

        startDestinationId()?.also { navGraph.setStartDestination(it)}
        navHostFragment.navController.setGraph(navGraph, startDestinationArgs())
    }
}
