package com.embarkx.firstjobapp.job.impl;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.jobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class jobServiceImpl implements jobService {

    private static final Logger logger = LoggerFactory.getLogger(jobServiceImpl.class);

    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
    }

    @Override
    public Job getJobById(Long id) {
        for (Job j : jobs){
            if(j != null && j.getId().equals(id)){
                return j;
            }
        }
        return null;
    }

    @Override
    public Boolean delJobById(Long id) {
        for (Job j : jobs){
            if(j != null && j.getId().equals(id)){
                logger.info("Deleting following job: "+j);
                jobs.remove(j);
                logger.info("Job deleted");
                return true;
            }

        }
        return false;
    }

    @Override
    public Boolean updJobById(Long id, Job updJob) {
        for (Job j : jobs){
            if(j != null && j.getId().equals(id)){
                j.setTitle(updJob.getTitle());
                j.setDescription(updJob.getDescription());
                j.setMinSalary(updJob.getMinSalary());
                j.setMaxSalary(updJob.getMaxSalary());
                j.setLocation(updJob.getLocation());
                logger.info("Updated job: "+ j.toString());
                return true;
            }
        }
        return false;
    }
}
