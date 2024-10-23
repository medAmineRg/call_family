package medev.callfamily.jobs;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import medev.callfamily.entity.Subscribers;
import medev.callfamily.exception.EmailSendingException;
import medev.callfamily.repository.SubscribeRepository;
import medev.callfamily.service.EmailService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Slf4j
public class MyJob implements Job {

    private final EmailService emailService;
    private final SubscribeRepository subscribeRepository;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            log.info("Starting email job...");

            List<Subscribers> allSubscribers = subscribeRepository.findAll();
            allSubscribers.forEach(subscriber -> {
                try {
                    emailService.sendSimpleEmail(
                            subscriber.getEmail(),
                            "Call Family Reminder",
                            "Hey, this is a reminder email to connect with your family."
                    );
                    log.info("Email sent to: {}", subscriber.getEmail());
                } catch (Exception e) {
                    log.error("Failed to send email to: {}", subscriber.getEmail(), e);
                }
            });

            log.info("Email job completed.");
        } catch (Exception e) {
            log.error("Job execution failed.", e);
            throw new JobExecutionException(e);
        }
    }
}
