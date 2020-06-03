package it.cook.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.edit
import androidx.navigation.fragment.findNavController
import it.cook.R
import it.cook.databinding.FragmentPreferencesBinding
import it.cook.receiver.AlarmHandler
import it.cook.viewmodel.MainViewModel
import it.cook.viewmodel.NavDrawerState
import java.util.*
import kotlin.math.min

class PreferencesFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences

    private var _binding: FragmentPreferencesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPreferencesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setNavigationOnClickListener { findNavController().navigateUp() }
        binding.doneBt.setOnClickListener { findNavController().navigateUp() }
        sharedPreferences =
            activity?.getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE)!!


        val breakFastStatus = sharedPreferences.getString(getString(R.string.breakfast), "OFF")
        val lunchStatus = sharedPreferences.getString(getString(R.string.lunch), "OFF")
        val dinnerStatus = sharedPreferences.getString(getString(R.string.dinner), "OFF")

        if (breakFastStatus.equals("ON")) {
            binding.breakFastSwitch.isChecked = true
        }
        if (lunchStatus.equals("ON")) {
            binding.lunchSwitch.isChecked = true
        }
        if (dinnerStatus.equals("ON")) {
            binding.dinnerSwitch.isChecked = true
        }

        binding.breakFastSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                sharedPreferences.edit {
                    putString(
                        getString(R.string.breakfast),
                        "ON"
                    )
                }
                setAlarm(8, 30)

            } else {
                sharedPreferences.edit {
                    putString(
                        getString(R.string.breakfast),
                        "OFF"
                    )
                }
            }
        }
        binding.lunchSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                sharedPreferences.edit {
                    putString(
                        getString(R.string.lunch),
                        "ON"
                    )
                }
                setAlarm(12, 30)

            } else {
                sharedPreferences.edit {
                    putString(
                        getString(R.string.lunch),
                        "OFF"
                    )
                }

            }
        }
        binding.dinnerSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                sharedPreferences.edit {
                    putString(
                        getString(R.string.dinner),
                        "ON"
                    )
                }
                setAlarm(21, 30)

            } else {
                sharedPreferences.edit {
                    putString(
                        getString(R.string.dinner),
                        "OFF"
                    )
                }

            }
        }
    }

    private fun setAlarm(hours: Int, mins: Int) {
        val alarmHandler = AlarmHandler()
        val nowTime = Calendar.getInstance()
        val setTime = nowTime.clone() as Calendar
        setTime[Calendar.HOUR_OF_DAY] = hours
        setTime[Calendar.MINUTE] = mins
        setTime[Calendar.SECOND] = 0
        setTime[Calendar.MILLISECOND] = 0
        activity?.let { alarmHandler.setAlarm(it, setTime) }
    }
}