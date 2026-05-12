package com.helloworld.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.helloworld.app.R
import com.helloworld.app.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MainViewModel
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        
        setupObservers()
        setupClickListeners()
        loadImage()
    }
    
    private fun setupObservers() {
        viewModel.greetingText.observe(viewLifecycleOwner) { text ->
            binding.tvGreeting.text = text
        }
        
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        
        viewModel.clickCount.observe(viewLifecycleOwner) { count ->
            binding.btnClickMe.text = "Click Me! ($count times)"
        }
        
        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                viewModel.clearError()
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.btnClickMe.setOnClickListener {
            viewModel.onButtonClicked()
        }
        
        binding.btnReset.setOnClickListener {
            // Simple reset without animation for GitHub build
            viewModel.onButtonClicked() // Just add click
        }
    }
    
    private fun loadImage() {
        Glide.with(this)
            .load(R.drawable.ic_launcher_foreground)
            .circleCrop()
            .into(binding.ivIcon)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}