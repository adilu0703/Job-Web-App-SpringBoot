package com.embarkx.firstjobapp.job;

import org.springframework.data.jpa.repository.JpaRepository;

public interface jobRepository extends JpaRepository<Job, Long> {
}
