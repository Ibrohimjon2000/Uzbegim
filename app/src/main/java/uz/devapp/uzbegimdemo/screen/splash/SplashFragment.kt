package uz.devapp.uzbegimdemo.screen.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.data.model.EventModel
import uz.devapp.uzbegimdemo.databinding.FragmentSplashBinding
import uz.devapp.uzbegimdemo.utils.Constants
import uz.devapp.uzbegimdemo.utils.PrefUtils

@AndroidEntryPoint
class SplashFragment : Fragment() {
    lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        binding.root.postDelayed({
            if (!EventBus.getDefault().isRegistered(this)) {
                EventBus.getDefault().register(this)
            }

            if (PrefUtils.getToken().isEmpty()) {
                requireActivity().findNavController(R.id.fragmentContainerView)
                    .navigate(R.id.action_splashFragment_to_languageFragment)
            } else {
                requireActivity().findNavController(R.id.fragmentContainerView)
                    .navigate(R.id.action_splashFragment_to_mainFragment)
            }
        }, 2000)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = SplashFragment()
    }

    override fun onDestroy() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
        super.onDestroy()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: EventModel) {
        if (event.command == Constants.EVENT_CLEAR_TOKEN) {

        }
    }
}