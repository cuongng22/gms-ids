package com.example.gms_ids.repository;

import com.example.gms_ids.table.UserAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuditLogRepository extends JpaRepository<UserAuditLog, Long> {
}




