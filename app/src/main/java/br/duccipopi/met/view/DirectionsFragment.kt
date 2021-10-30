package br.duccipopi.met.view

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import br.duccipopi.met.MyApp
import br.duccipopi.met.R
import br.duccipopi.met.databinding.DirectionsFragmentBinding
import br.duccipopi.met.viewmodel.DirectionsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DirectionsFragment : Fragment() {

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 0xCAFE
    }

    private lateinit var binding: DirectionsFragmentBinding
    private val viewModel: DirectionsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.directions_fragment, container, false)

        checkAndRequestPermissionIfNotGranted()

        if (!viewModel.googlePlayServiceAvailable) {
            binding.metAddressDistanceTitle.visibility = View.INVISIBLE
            binding.metAddressDistance.visibility = View.VISIBLE
        }

        viewModel.lastDistance.observe(viewLifecycleOwner, {
            val km = it / 1000
            val miles = it * 0.000621371

            binding.metAddressDistance.text = getString(R.string.my_distance_pattern, km, miles)
        })

        binding.getDirectionsButton.setOnClickListener {
            val intent = viewModel.getIntentMapsForDirections()

            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                startActivity(intent)
            }
        }

        return binding.root
    }

    private fun checkAndRequestPermissionIfNotGranted() {
        if (requireActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            viewModel.updateLocation(requireActivity())
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (permissions.contains(Manifest.permission.ACCESS_COARSE_LOCATION)
                && grantResults[permissions.indexOf(Manifest.permission.ACCESS_COARSE_LOCATION)] == PackageManager.PERMISSION_GRANTED
            ) {
                viewModel.updateLocation(requireActivity())
            }
        }
    }

}