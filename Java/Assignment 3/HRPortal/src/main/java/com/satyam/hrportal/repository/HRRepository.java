package com.satyam.hrportal.repository;

import com.satyam.hrportal.model.HR;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HRRepository extends JpaRepository<HR, Long> {
    HR findByEmail(String email);
}
