package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT = "Tasks: email once a day";

    @Autowired
    private SimpleEmailService emailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

    @Scheduled(cron = "0 0 10 * * *")
 //   @Scheduled(fixedDelay = 100000000)
    public void sendInformationMail() {
        long size = 1; // taskRepository.count();
        String word = (size == 1)?" task.":" tasks.";
        emailService.send(new Mail(adminConfig.getAdminMail(), SUBJECT, "Currently in database you have " + size + word));
    }
}
