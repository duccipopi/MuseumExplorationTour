package br.duccipopi.met.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import br.duccipopi.met.R
import br.duccipopi.met.databinding.DepartmentFragmentBinding
import br.duccipopi.met.viewmodel.DepartmentViewModel

class DepartmentFragment : Fragment() {

    private lateinit var binding: DepartmentFragmentBinding
    private lateinit var viewModel: DepartmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.department_fragment, container, false)

        viewModel = ViewModelProvider(this).get(DepartmentViewModel::class.java)

        binding.button.setOnClickListener {
            findNavController().navigate(DepartmentFragmentDirections.actionDepartmentFragmentToGalleryFragment())
        }


        return binding.root
    }

}