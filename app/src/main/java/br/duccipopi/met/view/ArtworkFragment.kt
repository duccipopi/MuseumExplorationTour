package br.duccipopi.met.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import br.duccipopi.met.R
import br.duccipopi.met.databinding.ArtworkFragmentBinding
import br.duccipopi.met.viewmodel.ArtworkViewModel

class ArtworkFragment : Fragment() {

    private lateinit var binding: ArtworkFragmentBinding
    private lateinit var viewModel: ArtworkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.artwork_fragment, container, false)
        viewModel = ViewModelProvider(this).get(ArtworkViewModel::class.java)

        return binding.root
    }

}