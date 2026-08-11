package com.micobank.account.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component("AuditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

    @Override
    public java.util.Optional<String> getCurrentAuditor() {
        // Return the current auditor (user) as an Optional
        return Optional.of("Account_MS"); // Replace with actual user retrieval logic
    }
}
