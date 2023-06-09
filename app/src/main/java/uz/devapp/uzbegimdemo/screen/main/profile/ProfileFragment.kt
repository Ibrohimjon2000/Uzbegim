package uz.devapp.uzbegimdemo.screen.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.databinding.FragmentProfileBinding
import uz.devapp.uzbegimdemo.screen.main.profile.editphone.EditPhoneFragment
import uz.devapp.uzbegimdemo.utils.PrefUtils

class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.apply {
            (requireActivity() as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
                View.VISIBLE

            history.setOnClickListener {
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.ordersFragment)
            }
            address.setOnClickListener {
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.myAddressFragment)
            }
            language.setOnClickListener {
                requireActivity().findNavController(R.id.fragmentContainerView)
                    .navigate(R.id.languageFragment)
            }
            phone.setOnClickListener {
                val fragment = EditPhoneFragment.newInstance()
                fragment.show(requireActivity().supportFragmentManager, fragment.tag)
            }
            help.setOnClickListener {
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.helpFragment)
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}