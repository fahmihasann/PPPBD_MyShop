package com.example.my_shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.my_shop.databinding.FragmentCheckoutBinding

class CheckoutFragment : Fragment() {

    private var _binding: FragmentCheckoutBinding? = null

    private val binding: FragmentCheckoutBinding
        get() = _binding!!

    private val args: CheckoutFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCheckoutBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // Menampilkan nama produk
        binding.txtProductName.text = args.productName

        // Membuka halaman pemilihan alamat
        binding.edtAddress.setOnClickListener {
            val action =
                CheckoutFragmentDirections
                    .actionCheckoutFragmentToAddressFragment()

            findNavController().navigate(action)
        }

        findNavController().currentBackStackEntry
            ?.savedStateHandle?.let { handle ->
                handle.getLiveData<String>("address")
                    .observe(viewLifecycleOwner) { res ->
                        binding.edtAddress.setText(res)
                    }
            }

        binding.btnDone.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
