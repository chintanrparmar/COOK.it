package it.cook

import android.app.NotificationManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import it.cook.receiver.AlarmHandler
import it.cook.utils.sendNotification
import it.cook.viewmodel.NavDrawerState
import kotlinx.android.synthetic.main.menu_layout.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var container: View
    private lateinit var navDrawerState: NavDrawerState
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        container = findViewById(R.id.motionLayout1)
        val drawerLayout = findViewById<DrawerLayout>(R.id.motionLayout)
        val prefTv = findViewById<TextView>(R.id.prefTv)
        val homeTv = findViewById<TextView>(R.id.homeTv)

        prefTv.setOnClickListener { goToPrefPage() }
        homeTv.setOnClickListener { goToHomePage() }
        aboutTv.setOnClickListener { goToAboutPage() }

        drawerLayout.setScrimColor(resources.getColor(android.R.color.transparent))

        navDrawerState = ViewModelProvider(this@MainActivity).get(NavDrawerState::class.java)
        navDrawerState.isOpen.observe(this, Observer {
            if (it) {
                drawerLayout.openDrawer(GravityCompat.START)
                changeState(container)
            } else {
                drawerLayout.closeDrawer(GravityCompat.START)
            }
        })



      /*  val notificationManager = ContextCompat.getSystemService(
            this,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(
            "getText(R.string.time_to_cook).toString()",
            this
        )
*/

    }

    private fun goToAboutPage() {
        findNavController(this, R.id.nav_host_fragment).navigate(R.id.aboutFragment)
        navDrawerState.updateNavDrawer(false)
    }
    private fun goToPrefPage() {
        findNavController(this, R.id.nav_host_fragment).navigate(R.id.preferencesFragment)
        navDrawerState.updateNavDrawer(false)
    }

    private fun goToHomePage() {
        findNavController(this, R.id.nav_host_fragment).navigate(R.id.homeFragment)
        navDrawerState.updateNavDrawer(false)
    }

    private fun changeState(v: View?) {
        val motionLayout = container as? MotionLayout ?: return
//        if (motionLayout.progress > 0.5f) {
        motionLayout.transitionToEnd()
//        } else {
//            motionLayout.transitionToEnd()
//        }
    }
}