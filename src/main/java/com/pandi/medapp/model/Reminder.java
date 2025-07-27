package com.pandi.medapp.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class Reminder {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dosage;
    private LocalTime time;
    private String days;
    private String notes;
    public Reminder() {}
    public Reminder(String name, String dosage, LocalTime time, String days, String notes) {
        this.name = name;
        this.dosage = dosage;
        this.time = time;
        this.days = days;
        this.notes = notes;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }
    public String getDays() { return days; }
    public void setDays(String days) { this.days = days; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
