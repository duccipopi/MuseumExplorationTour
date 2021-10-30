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

class ArtworkFragment : Fragment() {

    private lateinit var binding: ArtworkFragmentBinding

    private val args: ArtworkFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.artwork_fragment, container, false)
        binding.lifecycleOwner = this

        binding.artwork = args.artwork

        return binding.root
    }

}