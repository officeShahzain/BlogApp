package com.springboot.blogApp.AuditLogs;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Setter
@Getter
@EventListener(EnableJpaAuditing.class)
public class Auditing {
}
