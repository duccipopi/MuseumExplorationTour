package br.duccipopi.met.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import br.duccipopi.met.R
import br.duccipopi.met.databinding.GalleryFragmentBinding
import br.duccipopi.met.viewmodel.GalleryViewModel

class GalleryFragment : Fragment() {

    private lateinit var binding: GalleryFragmentBinding
    private lateinit var viewModel: GalleryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.gallery_fragment, container, false)

        viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        binding.button11.setOnClickListener {
            findNavController().navigate(GalleryFragmentDirections.actionGalleryFragmentToArtworkFragment())
        }

        return binding.root

    }

}