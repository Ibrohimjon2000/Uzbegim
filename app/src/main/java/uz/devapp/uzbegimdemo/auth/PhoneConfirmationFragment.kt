package uz.devapp.uzbegimdemo.auth

import android.os.Bundle
import android.os.CountDownTimer
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
import uz.devapp.uzbegimdemo.databinding.FragmentPhoneConfirmationBinding
import uz.devapp.uzbegimdemo.utils.Constants
import uz.devapp.uzbegimdemo.utils.PrefUtils
import java.util.*

private const val ARG_PARAM1 = Constants.EXTRA_DATA

@AndroidEntryPoint
class PhoneConfirmationFragment : Fragment() {
    lateinit var binding: FragmentPhoneConfirmationBinding
    private val viewModel: AuthViewModel by viewModels()
    private var param1: String? = null
    private var mTimeLeftInMillis: Long = 120000
    private var mEndTime: Long = 0
    private var mCountDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
        }
        startTimer()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPhoneConfirmationBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().findNavController(R.id.fragmentContainerView).popBackStack()
        }

        binding.tvPhone.setText(param1.toString())

        viewModel.confirmLiveData.observe(requireActivity()) {
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
                    PrefUtils.setToken(it.result.token)
                    requireActivity().findNavController(R.id.fragmentContainerView)
                        .navigate(R.id.action_phoneConfirmationFragment_to_mainFragment)
                }
            }
        }

        binding.btnConfirm.setOnClickListener {
            if (binding.otpView.text!!.isNotEmpty()) {
                viewModel.confirm("", param1.toString(), binding.otpView.text.toString())
            }
        }

        binding.tvSendSms.setOnClickListener {
            mTimeLeftInMillis=120000
            startTimer()
            binding.tvSendSms.visibility=View.GONE
        }

        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) = PhoneConfirmationFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
            }
        }
    }

    private fun startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis

        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                binding.tvSendSms.visibility=View.VISIBLE
            }
        }.start()
    }

    private fun updateCountDownText() {
        val minutes = mTimeLeftInMillis / 1000 / 60
        val seconds = mTimeLeftInMillis / 1000 % 60
        val timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        binding.tvTime.text = (timeLeftFormatted)

    }
}