package edu.northwestu.intc3283.datasourcestarter.controller;

import edu.northwestu.intc3283.datasourcestarter.repository.DonorsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/reports")
public class ReportsController {

    private final DonorsRepository donorsRepository;

    public ReportsController(DonorsRepository donorsRepository) {
        this.donorsRepository = donorsRepository;
    }

    @GetMapping("/weekly-donations")
    public String weeklyDonationReport(Model model){
        LocalDate startDate = LocalDate.now().minusWeeks(1);
        LocalDate endDate = LocalDate.now();

        model.addAttribute("donatedByWeek", this.donorsRepository
                .weeklyDonationReport(startDate, endDate));



        return "donors/weekly-donations";

    }

    @GetMapping("/janice-report")
    public String janiceReport(Model model){
        LocalDate startDate = LocalDate.now().minusMonths(LocalDate.now().getDayOfMonth());
        model.addAttribute("firstDonation", this.donorsRepository
                .janiceQuery(startDate));

        return "donors/janice-report";
    }

    @GetMapping("/larry-report")
    public String larryReport(Model model){
        model.addAttribute("larry", this.donorsRepository
                .larryQuery());

        return "donors/larry-report";
    }

    @GetMapping("/jennifer-report")
    public String jenniferReport(Model model){
        model.addAttribute("jenniferWeekly", this.donorsRepository
                .JenniferQueryWeekly());

        model.addAttribute("jenniferMonthly", this.donorsRepository
                .JenniferQueryMonthly());

        model.addAttribute("jenniferTop5", this.donorsRepository
                .JenniferQueryTop5());

        return "donors/jennifer-report";
    }
}
