package uz.devapp.uzbegimdemo.auth

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.data.repository.sealed.DataResult
import uz.devapp.uzbegimdemo.databinding.FragmentEntranceNumberBinding
import uz.devapp.uzbegimdemo.utils.Constants

@AndroidEntryPoint
class EntranceNumberFragment : Fragment() {
    lateinit var binding: FragmentEntranceNumberBinding
    private val viewModel: AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEntranceNumberBinding.inflate(inflater, container, false)

        viewModel.authLiveData.observe(requireActivity()) {
            when (it) {
                is DataResult.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    binding.btnConfirm.visibility = View.VISIBLE
                    binding.btnLoad.visibility = View.GONE
                }
                is DataResult.LoadingHide -> {
                    binding.btnConfirm.visibility = View.VISIBLE
                    binding.btnLoad.visibility = View.GONE
                }
                is DataResult.LoadingShow -> {
                    binding.btnConfirm.visibility = View.GONE
                    binding.btnLoad.visibility = View.VISIBLE
                }
                is DataResult.Success -> {
                    val bundle = Bundle()
                    bundle.putString(Constants.EXTRA_DATA, binding.edPhone.text.toString())
                    requireActivity().findNavController(R.id.fragmentContainerView)
                        .navigate(
                            R.id.action_entranceNumberFragment_to_phoneConfirmationFragment,
                            bundle
                        )
                }
            }
        }

        binding.btnConfirm.setOnClickListener {
            if (binding.edPhone.text.toString().length == 11 && binding.checkbox.isChecked) {
                viewModel.login(binding.edPhone.text.toString())
            }
        }

        binding.toolbar.setNavigationOnClickListener {
            requireActivity().findNavController(R.id.fragmentContainerView).popBackStack()
        }

        binding.tvSkipping.setOnClickListener {
            requireActivity().findNavController(R.id.fragmentContainerView)
                .navigate(R.id.action_entranceNumberFragment_to_mainFragment)
        }

        binding.edPhone.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                if (p0!!.length == 11 && binding.checkbox.isChecked) {
                    binding.btnConfirm.setBackgroundColor(Color.parseColor("#C30019"))
                } else {
                    binding.btnConfirm.setBackgroundColor(Color.parseColor("#C8C8C8"))
                }
            }
        })

        binding.checkbox.setOnClickListener {
            if (binding.edPhone.text.toString().length == 11 && binding.checkbox.isChecked) {
                binding.btnConfirm.setBackgroundColor(Color.parseColor("#C30019"))
            } else {
                binding.btnConfirm.setBackgroundColor(Color.parseColor("#C8C8C8"))
            }
        }

        binding.tvHelp.setOnClickListener {
            val profile_telegram =
                Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Mobiler007"))
            startActivity(profile_telegram)
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = EntranceNumberFragment()
    }

    override fun onResume() {
        super.onResume()
        if (binding.edPhone.text.toString().length == 11 && binding.checkbox.isChecked) {
            binding.btnConfirm.setBackgroundColor(Color.parseColor("#C30019"))
        } else {
            binding.btnConfirm.setBackgroundColor(Color.parseColor("#C8C8C8"))
        }
    }
}