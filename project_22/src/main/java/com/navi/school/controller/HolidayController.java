package com.navi.school.controller;

import com.navi.school.entity.Holiday;
import com.navi.school.repository.HolidaysRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
public class HolidayController {

    private final HolidaysRepository holidaysRepository;

    @Autowired
    public HolidayController(HolidaysRepository holidaysRepository) {
        this.holidaysRepository = holidaysRepository;
    }

    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display, Model model){

        if(display != null){
            if(display.equalsIgnoreCase("federal")){
                model.addAttribute("federal", true);
            } else if (display.equalsIgnoreCase("festival")) {
                model.addAttribute("festival", true);
            } else  {
                model.addAttribute("federal", true);
                model.addAttribute("festival", true);
            }
        }

        List<Holiday> holidays = StreamSupport.stream((holidaysRepository.findAll()).spliterator(), false)
                .toList();

        Holiday.Type[] types = Holiday.Type.values();
        for(Holiday.Type type : types){
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }

        return "holidays.html";
    }
}
