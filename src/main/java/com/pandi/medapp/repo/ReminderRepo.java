package com.pandi.medapp.repo;

import com.pandi.medapp.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepo extends JpaRepository<Reminder, Long> { }
