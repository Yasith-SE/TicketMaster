package edu.icet.service;

import edu.icet.model.entity.AuditLogEntity;
import edu.icet.repository.AuditRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {

    private final AuditRepository auditLogRepository;

    @AfterThrowing(pointcut = "@annotation(edu.icet.service.AuditFailure)", throwing = "ex")
    public void logFailure(JoinPoint joinPoint, Throwable ex) {
        Object[] args = joinPoint.getArgs();
        Long userId = (args.length > 1 && args[1] instanceof Long) ? (Long) args[1] : null;

        AuditLogEntity log = AuditLogEntity.builder()
                .action("FAILURE")
                .userId(userId)
                .details(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();

        auditLogRepository.save(log);
    }
}