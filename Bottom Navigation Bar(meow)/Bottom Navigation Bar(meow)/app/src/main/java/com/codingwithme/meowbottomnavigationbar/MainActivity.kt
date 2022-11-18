package com.codingwithme.meowbottomnavigationbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // First make sure to add the meow dependency in your module level gradle file,
        // jcentre() to your project path gradle and in your gradle.properties android.useAndroidX
        //android.enableJetifier should be equals to true

        // a plugin was added to directly call views(apply plugin: 'kotlin-android-extensions')
        bottomNavigation.show(0)
        bottomNavigation.add(MeowBottomNavigation.Model(0,R.drawable.ic_home))
        bottomNavigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_chat))
        bottomNavigation.add(MeowBottomNavigation.Model(2,R.drawable.ic_user))

        bottomNavigation.setOnClickMenuListener {
            when(it.id){
                0 -> {
                    replaceFragment(HomeFragment())
                }
                1 -> {
                    replaceFragment(ChatFragment())
                }
                2 -> {
                    replaceFragment(UserFragment())
                }
                else -> {
                    replaceFragment(HomeFragment())
                }
            }
        }
    }

    // as its named to facilitate change in fragment
    private fun replaceFragment(fragment: androidx.fragment.app.Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
            fragmentTransition.replace(R.id.fragmentContainer,fragment)
                .addToBackStack(androidx.fragment.app.Fragment::class.java.simpleName).commit()
    }

}