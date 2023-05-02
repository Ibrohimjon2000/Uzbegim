package uz.devapp.uzbegimdemo.screen.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.databinding.FragmentMainBinding
import uz.devapp.uzbegimdemo.utils.PrefUtils

@AndroidEntryPoint
class MainFragment : Fragment() {
    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        val navHostFragment =
            childFragmentManager.findFragmentById(R.id.mainFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.bottomNavigationView.setupWithNavController(navController)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}