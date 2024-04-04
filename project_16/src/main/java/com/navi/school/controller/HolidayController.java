package com.navi.school.controller;

import com.navi.school.entity.Holiday;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class HolidayController {

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



        List<Holiday> holidays = Arrays.asList(
                new Holiday("Jan 1", "New Years Day", Holiday.Type.FESTIVAL),
                new Holiday("Oct 31", "Halloween", Holiday.Type.FESTIVAL),
                new Holiday("Nov 24", "Thanksgiving Day", Holiday.Type.FESTIVAL),
                new Holiday("Dec 25", "Christmas", Holiday.Type.FESTIVAL),
                new Holiday("Jan 17","Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
                new Holiday("Jul 4", "Independence Day", Holiday.Type.FEDERAL),
                new Holiday("sep 5", "Labour Day", Holiday.Type.FEDERAL),
                new Holiday("Nov 11", "Veterans Day", Holiday.Type.FEDERAL)
        );

        Holiday.Type[] types = Holiday.Type.values();
        for(Holiday.Type type : types){
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }

        return "holidays.html";
    }
}
