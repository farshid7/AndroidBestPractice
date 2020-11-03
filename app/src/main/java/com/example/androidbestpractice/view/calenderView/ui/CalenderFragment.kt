package com.example.androidbestpractice.view.calenderView.ui

import android.app.AlertDialog
import android.widget.LinearLayout
import android.widget.Toast
import com.example.androidbestpractice.R
import com.example.androidbestpractice.base.BaseFragment
import com.example.androidbestpractice.databinding.FragmentCalenderBinding
import com.sardari.daterangepicker.utils.PersianCalendar


class CalenderFragment : BaseFragment<FragmentCalenderBinding>() {
    override fun getLayout() = R.layout.fragment_calender

    private val data = mutableListOf<PlaceReserved>()
    private var currentItemId=4
    override fun init() {
//        DatePickerDialog(requireContext()).apply {
//            selectionMode = DateRangeCalendarView.SelectionMode.Range
////datePickerDialog.setEnableTimePicker(true);
////datePickerDialog.setShowGregorianDate(true);
////datePickerDialog.setEnableTimePicker(true);
////datePickerDialog.setShowGregorianDate(true);
//            textSizeTitle = 10.0f
//            textSizeWeek = 12.0f
//            textSizeDate = 14.0f
//            setCanceledOnTouchOutside(true)
//
//            onSingleDateSelectedListener =
//                DatePickerDialog.OnSingleDateSelectedListener { selectedDay ->
//
//                }
//
//            onRangeDateSelectedListener =
//                DatePickerDialog.OnRangeDateSelectedListener { startDate: PersianCalendar, endDate: PersianCalendar ->
//
//                }
//            showDialog()
//        }
        initData()
        updateUi()
        binding.fab.setOnClickListener {
            currentItemId++
            data.add(
                PlaceReserved(
                    currentItemId,
                    "${currentItemId}آیتم تستی",
                    mutableListOf(
                    )
                )
            )
            updateUi()
        }
    }

    private fun initData() {
        data.add(
            PlaceReserved(
                1,
                "ویلا شمال",
                mutableListOf(
                    PersianCalendar(System.currentTimeMillis() + (86400000)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 2)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 3)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 4))
                )
            )
        )
        data.add(
            PlaceReserved(
                2,
                "ویلا تهران",
                mutableListOf(
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 2)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 3)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 5)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 20))
                )
            )
        )
        data.add(
            PlaceReserved(
                3,
                "سوییت کوچک ",
                mutableListOf(
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 2)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 3)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 5)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 7)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 8)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 10))
                )
            )
        )
        data.add(
            PlaceReserved(
                4,
                "استخر جنوب",
                mutableListOf(
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 2)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 3)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 5)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 20)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 22)),
                    PersianCalendar(System.currentTimeMillis() + (86400000 * 24))
                )
            )
        )

    }

    private fun updateUi() {
        if (binding.items.childCount > 0) binding.items.removeAllViews()
        val allReserve = ArrayList<PersianCalendar>()
        data.forEach {
            allReserve.addAll(it.reservedDay)
        }

        val minDay = findMin(allReserve)
        val maxDay = findMax(allReserve)

        val mainLay = LinearLayout(requireContext())
        mainLay.addView(DateItemCustomView(requireContext(), " ") {

        })
        var currentDate = minDay
        do {
            mainLay.addView(
                DateItemCustomView(
                    requireContext(),
                    "${currentDate.persianYear}/${currentDate.persianMonth + 1}/${currentDate.persianDay}"
                ) {

                })
            currentDate = addOneDay(currentDate)
        } while (!equal(currentDate, maxDay))
        mainLay.addView(
            DateItemCustomView(
                requireContext(),
                "${maxDay.persianYear}/${maxDay.persianMonth + 1}/${maxDay.persianDay}"
            ) {

            })

        binding.items.addView(mainLay)
        data.forEachIndexed {index, place ->
            val childLay = LinearLayout(requireContext())
            childLay.addView(DateItemCustomView(requireContext(), place.PlaceName) {
                var text = "تاریخ های رزرو شده:\n"
                place.reservedDay.forEachIndexed {indexReservedDay, reserverDay ->
                    text += "${reserverDay.persianYear}/${reserverDay.persianMonth + 1}/${reserverDay.persianDay} \n"
                }
                AlertDialog.Builder(requireContext()).apply {
                    setTitle(place.PlaceName)
                    setMessage(text)
                    setPositiveButton(
                        "رزرو رنج"
                    ) { _, _ ->
                        Toast.makeText(requireContext(), "انجام خواهد شد", Toast.LENGTH_SHORT)
                            .show()
                    }
                    setNegativeButton(
                        "رزرو تکی"
                    ) { _, _ ->
                        Toast.makeText(requireContext(), "انجام خواهد شد", Toast.LENGTH_SHORT)
                            .show()
                    }
                    setNeutralButton(
                        " حذف  ${place.PlaceName}"
                    ) { _, _ ->
                        data.remove(place)
                        updateUi()
                    }
                    show()
                }
            })
            currentDate = minDay
            do {
                val added = place.reservedDay.any { equal(it, currentDate) }
                val text = if (added) {
                    "رزرو شده"
                } else {
                    "خالی"
                }

                val textres = if (added) {
                    "حذف رزرو"
                } else {
                    "افزودم رزرو"
                }
                childLay.addView(DateItemCustomView(requireContext(), text) {
                    AlertDialog.Builder(requireContext()).apply {
                        setTitle("${place.PlaceName} در تاریخ ${currentDate.persianYear}/${currentDate.persianMonth + 1}/${currentDate.persianDay}")
                        setMessage(text)
                        setPositiveButton(
                            textres
                        ) { _, _ ->
                            Toast.makeText(requireContext(), "انجام خواهد شد", Toast.LENGTH_SHORT)
                                .show()
                        }
                        show()
                    }
                })
                currentDate = addOneDay(currentDate)
            } while (!equal(currentDate, maxDay))
            val text = if (place.reservedDay.any { equal(it, maxDay) }) {
                "رزرو شده"
            } else {
                "خالی"
            }
            childLay.addView(DateItemCustomView(requireContext(), text) {
                Toast.makeText(
                    requireContext(),
                    " ${place.PlaceName} \n شماره تاریخ ",
                    Toast.LENGTH_SHORT
                ).show()
            })
            binding.items.addView(childLay)
        }
    }


    private fun findMin(list: ArrayList<PersianCalendar>): PersianCalendar {
        return list.reduce { a: PersianCalendar, b: PersianCalendar -> a.coerceAtMost(b) }
    }

    private fun findMax(list: List<PersianCalendar>): PersianCalendar {
        return list.reduce { a: PersianCalendar, b: PersianCalendar -> a.coerceAtLeast(b) }
    }

    fun addOneDay(persianCalendar: PersianCalendar): PersianCalendar {
        return PersianCalendar(persianCalendar.timeInMillis + 86400000)
    }


    fun equal(persianCalendar1: PersianCalendar, persianCalendar2: PersianCalendar): Boolean {
        return persianCalendar1.persianDay == persianCalendar2.persianDay && persianCalendar1.persianMonth == persianCalendar2.persianMonth && persianCalendar1.persianYear == persianCalendar2.persianYear
    }


    data class PlaceReserved(
        val id: Int,
        val PlaceName: String,
        val reservedDay: MutableList<PersianCalendar>
    )
}