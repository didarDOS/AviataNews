package com.example.aviatanews

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.aviatanews.databinding.ActivityMainBinding
import com.example.aviatanews.ui.fragments.*

import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    private val fragmentManager = supportFragmentManager

    /**Fragments*/
    private val homeRootFragment = HomeRootFragment()
    private val accountFragment = AccountFragment()
    private val everyThingNewsFragment = EverythingNewsFragment()
    private val bookmarksFragment = BookmarksFragment()
    private var active: Fragment = homeRootFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        bindingViews()

    }
    /**
     * Called on first creation and when restoring state.
     */

    private fun bindingViews(){

        fragmentManager.beginTransaction().add(R.id.main_container, accountFragment).hide(accountFragment).commit()
        fragmentManager.beginTransaction().add(R.id.main_container, everyThingNewsFragment).hide(everyThingNewsFragment).commit()
        fragmentManager.beginTransaction().add(R.id.main_container, bookmarksFragment).hide(bookmarksFragment).commit()
        fragmentManager.beginTransaction().add(R.id.main_container, homeRootFragment).commit()

        binding.bottomNav.setOnItemSelectedListener { item ->
            when(item.itemId){
                R.id.tab_home -> {
                    fragmentManager.beginTransaction().hide(active).show(homeRootFragment).commit()
                    active = homeRootFragment
                }
                R.id.tab_everything -> {
                    fragmentManager.beginTransaction().hide(active).show(everyThingNewsFragment).commit()
                    active = everyThingNewsFragment
                }
                R.id.tab_bookmarks -> {
                    fragmentManager.beginTransaction().hide(active).show(bookmarksFragment).commit()
                    active = bookmarksFragment
                }
                R.id.tab_account -> {
                    fragmentManager.beginTransaction().hide(active).show(accountFragment).commit()
                    active = accountFragment
                }
            }
            true
        }
//        binding.bottomNav.setupWithNavController(navController)

        fragmentManager.addOnBackStackChangedListener {
            val current = fragmentManager.findFragmentById(R.id.main_container)

        }
    }





}