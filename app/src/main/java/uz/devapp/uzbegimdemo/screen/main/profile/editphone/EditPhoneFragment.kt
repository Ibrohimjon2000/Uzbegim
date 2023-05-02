package uz.devapp.uzbegimdemo.screen.main.profile.editphone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import uz.devapp.uzbegimdemo.databinding.FragmentEditPhoneBinding

class EditPhoneFragment : BottomSheetDialogFragment() {
    lateinit var binding:FragmentEditPhoneBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentEditPhoneBinding.inflate(inflater,container,false)

        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance() = EditPhoneFragment()
    }
}