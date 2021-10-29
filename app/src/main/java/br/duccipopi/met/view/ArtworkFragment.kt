package br.duccipopi.met.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.duccipopi.met.R
import br.duccipopi.met.databinding.ArtworkFragmentBinding
import br.duccipopi.met.viewmodel.ArtworkViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ArtworkFragment : Fragment() {

    private lateinit var binding: ArtworkFragmentBinding
    private val viewModel: ArtworkViewModel by viewModel()

    private val args: ArtworkFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.artwork_fragment, container, false)
        binding.lifecycleOwner = this

        binding.artwork = args.artwork

        binding.artworkImage.setOnClickListener {
            binding.artworkInfo.visibility = View.VISIBLE
            it.visibility = View.INVISIBLE

        }

        binding.artworkInfo.setOnClickListener {
            binding.artworkImage.visibility = View.VISIBLE
            it.visibility = View.INVISIBLE
        }


        return binding.root
    }

}