package com.embarkx.firstjobapp.job;

import java.util.List;

public interface jobService {

    List<Job> findAll();

    void createJob(Job job);

    Job getJobById(Long id);

     Boolean delJobById(Long id);

    Boolean updJobById(Long id, Job job);
}
