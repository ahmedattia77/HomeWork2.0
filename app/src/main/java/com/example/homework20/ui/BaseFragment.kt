package com.example.homework20.ui
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>
    (private val bindingInflater: (layoutInflater:LayoutInflater) -> VB) : Fragment() {

    protected lateinit var navController: NavController

    private var _binding: VB? = null

    protected val binding get() = _binding as VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        if (_binding == null)
            throw java.lang.IllegalArgumentException("Binding can not be null")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    protected abstract fun setUp()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navController = findNavController()
    }

    protected fun Any.toast(context: Context, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(context, this.toString(), duration).apply { show() }

}