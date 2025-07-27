package com.pandi.medapp.web;

import com.pandi.medapp.model.Reminder;
import com.pandi.medapp.repo.ReminderRepo;
import com.pandi.medapp.service.ReminderScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Controller
public class ReminderController {
    private final ReminderRepo repo;
    private final ReminderScheduler scheduler;
    public ReminderController(ReminderRepo repo, ReminderScheduler scheduler) {
        this.repo = repo;
        this.scheduler = scheduler;
    }
    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("reminders", repo.findAll());
        return "reminders";
    }
    @PostMapping("/add")
    public String add(@RequestParam String name, @RequestParam String dosage,
                      @RequestParam String time, @RequestParam(required = false) List<String> days,
                      @RequestParam(required = false) String notes) {
        String daysStr = days == null ? "" : String.join(",", days);
        repo.save(new Reminder(name, dosage, LocalTime.parse(time), daysStr, notes));
        return "redirect:/";
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/";
    }
    @ResponseBody
    @GetMapping("/api/notifications")
    public List<Map<String,Object>> notifications() {
        return scheduler.popNotifications();
    }
}
