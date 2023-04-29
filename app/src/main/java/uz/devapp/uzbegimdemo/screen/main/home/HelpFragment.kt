package uz.devapp.uzbegimdemo.screen.main.home

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.permissionx.guolindev.PermissionX
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {
    lateinit var binding: FragmentHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHelpBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility =
            View.GONE
        binding.back.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView).popBackStack()
        }

        binding.phoneCall.setOnClickListener {
            PermissionX.init(requireActivity())
                .permissions(
                    Manifest.permission.CALL_PHONE,
                )
                .request { allGranted, grantedList, deniedList ->
                    val dialIntent = Intent(Intent.ACTION_DIAL)
                    dialIntent.data = Uri.parse("tel:" + "+998911175920")
                    startActivity(dialIntent)
                }
        }

        binding.telegram.setOnClickListener {
            val profile_telegram =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Mobiler007"))
            startActivity(profile_telegram)
        }

        binding.instagram.setOnClickListener {
            val profile_instagram =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/ibrohimjonnosiraliyev"))
            startActivity(profile_instagram)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = HelpFragment()
    }
}