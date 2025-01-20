package com.guan.simplekotlinproject.ui.intent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.guan.simplekotlinproject.R
import com.guan.simplekotlinproject.databinding.ActivityMoveWithObjectBinding

class MoveActivityWithObject : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMoveWithObjectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoveWithObjectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the ActionBar
        setSupportActionBar(binding.toolbar)

        // Setup navigation drawer
        val drawerLayout = binding.drawerLayout
        val navView = binding.navView

        // AppBarConfiguration to handle navigation UI
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home, R.id.nav_coffe, R.id.btn_move_activity_with_object, R.id.nav_github, R.id.nav_instagram, R.id.nav_x, R.id.nav_email),
            drawerLayout
        )

        // Assuming you have a Mahasiswa object passed via intent
        val mahasiswa = intent.getParcelableExtra<Mahasiswa>(EXTRA_MAHASISWA)
        binding.tvObjectReceived.text = mahasiswa?.toString() ?: "No data received"
    }

    override fun onSupportNavigateUp(): Boolean {
        // You can handle drawer navigation manually here if needed
        return super.onSupportNavigateUp()
    }

    companion object {
        const val EXTRA_MAHASISWA = "extra_mahasiswa"
    }
}