package br.duccipopi.met.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.duccipopi.met.R
import br.duccipopi.met.databinding.DepartmentFragmentBinding
import br.duccipopi.met.viewmodel.DepartmentAdapter
import br.duccipopi.met.viewmodel.DepartmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DepartmentFragment : Fragment() {

    private lateinit var binding: DepartmentFragmentBinding
    private val viewModel: DepartmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.department_fragment, container, false)

        binding.lifecycleOwner = this

        viewModel.loadDepartments()

        viewModel.loading.observe(viewLifecycleOwner, { loading ->
            if (!loading) {
                binding.departmentList.adapter = DepartmentAdapter(viewLifecycleOwner, viewModel.departments, {
                    findNavController().navigate(DepartmentFragmentDirections.actionDepartmentFragmentToGalleryFragment(it))
                })
                binding.departmentList.layoutManager = LinearLayoutManager(requireContext())
            }
        })


        return binding.root
    }

}