package com.example.androidbestpractice.view.calenderView.ui

import android.widget.LinearLayout
import android.widget.Toast
import com.example.androidbestpractice.R
import com.example.androidbestpractice.base.BaseFragment
import com.example.androidbestpractice.databinding.FragmentCalenderBinding

class CalenderFragment : BaseFragment<FragmentCalenderBinding>() {
    override fun getLayout() = R.layout.fragment_calender

    override fun init() {
        val data = arrayListOf<PlaceReserved>().apply {
            add(PlaceReserved(1, "مکان 1", mutableListOf(1, 5, 9, 15, 18)))
            add(PlaceReserved(2, "مکان 2", mutableListOf(3, 5, 8, 16, 30)))
            add(PlaceReserved(3, "مکان 3", mutableListOf(5, 8, 14, 16, 31)))
            add(PlaceReserved(4, "مکان 4", mutableListOf(7, 10, 12, 14, 25)))
            add(PlaceReserved(5, "مکان 5", mutableListOf(7, 10, 12, 14, 25)))
            add(PlaceReserved(6, "مکان 6", mutableListOf(7, 10, 12, 14, 25)))
            add(PlaceReserved(7, "مکان 7", mutableListOf(7, 10, 12, 14, 25)))
            add(PlaceReserved(8, "مکان 8", mutableListOf(7, 10, 12, 14, 25)))
        }

        val allReserve = ArrayList<Int>()
        data.forEach {
            allReserve.addAll(it.reservedDay)
        }

        val minDay = findMin(allReserve)
        val maxDay = findMax(allReserve)

        val ll = LinearLayout(requireContext())
        ll.addView(DateItemCustomView(requireContext(), " ") {
        })

        for (j in minDay..maxDay) {
            ll.addView(DateItemCustomView(requireContext(), "day \n $j") {

            })
        }
        binding.items.addView(ll)
        data.forEach { place ->
            val ll = LinearLayout(requireContext())
            ll.addView(DateItemCustomView(requireContext(), place.PlaceName) {
            })
            for (j in minDay..maxDay) {
                val text = if (place.reservedDay.any { it == j }) {
                    "رزرو شده"
                } else {
                    "خالی"
                }
                ll.addView(DateItemCustomView(requireContext(), text) {
                    Toast.makeText(
                        requireContext(),
                        " ${place.PlaceName} \n شماره تاریخ $j",
                        Toast.LENGTH_SHORT
                    ).show()
                })
            }
            binding.items.addView(ll)
        }
    }

    private fun findMin(list: List<Int>): Int {
        return list.reduce { a: Int, b: Int -> a.coerceAtMost(b) }
    }

    private fun findMax(list: List<Int>): Int {
        return list.reduce { a: Int, b: Int -> a.coerceAtLeast(b) }
    }

    data class PlaceReserved(val id: Int, val PlaceName: String, val reservedDay: List<Int>)
}