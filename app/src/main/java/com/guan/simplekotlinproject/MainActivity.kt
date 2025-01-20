package com.guan.simplekotlinproject

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.guan.simplekotlinproject.databinding.ActivityMainBinding
import com.guan.simplekotlinproject.ui.intent.Mahasiswa
import com.guan.simplekotlinproject.ui.intent.MoveActivityWithObject
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mainBinding: ActivityMainBinding

    private lateinit var profileCircleImageView: CircleImageView
    private var profileImage = "https://instagram.fcgk38-1.fna.fbcdn.net/v/t51.2885-19/471325229_9007815245977555_8986731224578737442_n.jpg?_nc_ht=instagram.fcgk38-1.fna.fbcdn.net&_nc_cat=107&_nc_ohc=crHLqr0nKHAQ7kNvgGXqPGX&_nc_gid=c5a9bc28a5974321abce3f63fc10c3a3&edm=AP4sbd4BAAAA&ccb=7-5&oh=00_AYCey17a4wGAFtOq-5fnz5SVndOIsHDNPhfqPgxBoOvaDQ&oe=677C4906&_nc_sid=7a9f4b"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        // Set the ActionBar
        setSupportActionBar(mainBinding.appBarMain.toolbar)

        mainBinding.appBarMain.fab.setOnClickListener { view ->
            // Menggunakan Intent untuk membuka browser dengan URL yang diinginkan
            val url = "https://github.com/guanshiyin28/"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        val drawerLayout: DrawerLayout = mainBinding.drawerLayout
        val navView: NavigationView = mainBinding.navView

        profileCircleImageView = navView.getHeaderView(0).findViewById(R.id.imageView)
        Glide.with(this).load(profileImage).into(profileCircleImageView)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_coffe, R.id.btn_move_activity_with_object, R.id.nav_github, R.id.nav_instagram, R.id.nav_x, R.id.nav_email
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                navController.navigate(R.id.nav_home)
            }
            R.id.nav_coffe -> {
                val navController = findNavController(R.id.nav_host_fragment_content_main)
                navController.navigate(R.id.nav_coffe)
            }
            R.id.btn_move_activity_with_object -> {
                val objectmhs = Mahasiswa("Muhammad Agisna Yudiansyah", "TI.23.B.2", 19, "guanshiyinnn@gmail.com", "Java")
                val moveIntentWithObject = Intent(this, MoveActivityWithObject::class.java)
                moveIntentWithObject.putExtra(MoveActivityWithObject.EXTRA_MAHASISWA, objectmhs)
                startActivity(moveIntentWithObject)
            }
            R.id.nav_github -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/guanshiyin28"))
                startActivity(intent)
            }
            R.id.nav_instagram -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/guanshiyin_"))
                startActivity(intent)
            }
            R.id.nav_x -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://x.com/guanss_"))
                startActivity(intent)
            }
            R.id.nav_email -> {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:guanshiyinnn@gmail.com")
                }
                startActivity(intent)
            }
        }

        mainBinding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}