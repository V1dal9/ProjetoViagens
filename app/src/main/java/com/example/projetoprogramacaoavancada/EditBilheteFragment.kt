package com.example.projetoprogramacaoavancada

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.projetoprogramacaoavancada.databinding.FragmentEditBilheteBinding


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class EditBilheteFragment : Fragment() {
    private var _binding: FragmentEditBilheteBinding? = null

    private val binding get() = _binding!!
    private var infoViagemBilhete:  InfoViagemBilhete? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentEditBilheteBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        const val ID_LOADER_BILHETE = 0
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}