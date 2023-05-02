package uz.devapp.uzbegimdemo.screen.main.cart.checkout.order

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import uz.devapp.uzbegimdemo.R
import uz.devapp.uzbegimdemo.adapter.OrderAdapter
import uz.devapp.uzbegimdemo.adapter.OrderAdapterCallback
import uz.devapp.uzbegimdemo.data.model.ProductModel
import uz.devapp.uzbegimdemo.databinding.FragmentOrderDetailBinding

class OrderDetailFragment : Fragment() {
    lateinit var binding:FragmentOrderDetailBinding
    var ordersList= listOf(
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
        ProductModel(1),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentOrderDetailBinding.inflate(inflater,container,false)
        binding.back.setOnClickListener {
            requireActivity().findNavController(R.id.mainFragmentContainerView).popBackStack()
        }
        binding.rv.adapter=OrderAdapter(ordersList,object :OrderAdapterCallback{
            override fun onSelectCategory(item: ProductModel) {
                requireActivity().findNavController(R.id.mainFragmentContainerView)
                    .navigate(R.id.orderInformationFragment)
            }

            override fun onSelectText(item: ProductModel, holder: OrderAdapter.Vh) {
                copyText("47810")
                holder.binding.copyImage.setImageResource(R.drawable.done)
                binding.root.postDelayed({
                    holder.binding.copyImage.setImageResource(R.drawable.copy)
                },2000)
            }
        })
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = OrderDetailFragment()
    }

    fun copyText(text:String){
        val myClipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val myClip: ClipData = ClipData.newPlainText("Label", text)
        myClipboard.setPrimaryClip(myClip)
    }
}