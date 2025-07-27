package com.pandi.medapp.service;

import com.pandi.medapp.model.Reminder;
import com.pandi.medapp.repo.ReminderRepo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class ReminderScheduler {
    private final ReminderRepo repo;
    private final List<Reminder> queue = new CopyOnWriteArrayList<>();
    public ReminderScheduler(ReminderRepo repo) {
        this.repo = repo;
    }
    @Scheduled(cron = "*/30 * * * * *")
    public void checkDue() {
        var now = ZonedDateTime.now(ZoneId.systemDefault());
        var today = now.getDayOfWeek();
        var timeNow = now.toLocalTime().withSecond(0).withNano(0);
        repo.findAll().forEach(r -> {
            if (r.getTime() == null || r.getDays() == null) return;
            Set<DayOfWeek> days = parseDays(r.getDays());
            if (days.contains(today) && r.getTime().equals(timeNow)) {
                queue.add(r);
            }
        });
    }
    public List<Map<String, Object>> popNotifications() {
        List<Reminder> copy = new ArrayList<>(queue);
        queue.clear();
        return copy.stream().map(r -> Map.of(
            "id", r.getId(),
            "name", r.getName(),
            "dosage", r.getDosage(),
            "notes", r.getNotes()
        )).collect(Collectors.toList());
    }
    private Set<DayOfWeek> parseDays(String s) {
        return Arrays.stream(s.split(","))
                .map(String::trim)
                .filter(v -> !v.isEmpty())
                .map(v -> DayOfWeek.valueOf(v.toUpperCase()))
                .collect(Collectors.toSet());
    }
}
