package uz.devapp.uzbegimdemo.screen.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {
    lateinit var binding:FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentOrdersBinding.inflate(inflater,container,false)
        (requireActivity() as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.GONE
        binding.back.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView).popBackStack()
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = OrdersFragment()
    }
}