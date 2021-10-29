package br.duccipopi.met.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import br.duccipopi.met.R
import br.duccipopi.met.databinding.GalleryFragmentBinding
import br.duccipopi.met.util.adapter.GenericAdapter
import br.duccipopi.met.viewmodel.GalleryViewModel
import org.koin.android.ext.android.inject

class GalleryFragment : Fragment() {

    private lateinit var binding: GalleryFragmentBinding
    private val viewModel: GalleryViewModel by inject()

    val args: GalleryFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.gallery_fragment, container, false)
        binding.lifecycleOwner = this

        val departmentId = args.departmentId

        viewModel.loadArtworksForDepartment(departmentId)

        viewModel.loading.observe(viewLifecycleOwner, { loading ->
            if (!loading) {
                binding.galleryList.adapter =
                    GenericAdapter(viewLifecycleOwner, viewModel.artworks, {
                        findNavController().navigate(
                            GalleryFragmentDirections.actionGalleryFragmentToArtworkFragment(it.id))
                    })
                binding.galleryList.layoutManager = GridLayoutManager(requireContext(), 2)
            }
        })

        return binding.root

    }

}