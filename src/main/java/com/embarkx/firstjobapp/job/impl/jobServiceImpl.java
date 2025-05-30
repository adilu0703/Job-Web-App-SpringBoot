package com.embarkx.firstjobapp.job.impl;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.jobRepository;
import com.embarkx.firstjobapp.job.jobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class jobServiceImpl implements jobService {

    private static final Logger logger = LoggerFactory.getLogger(jobServiceImpl.class);

    jobRepository jobrepository;

    //private List<Job> jobs = new ArrayList<>();

    private Long nextId = 1L;

    public jobServiceImpl(jobRepository jobrepository)
    {
        this.jobrepository = jobrepository;
    }

    @Override
    public List<Job> findAll() {
//        return jobs;
       return jobrepository.findAll();
    }

    @Override
    public void createJob(Job job) {
       // job.setId(nextId++);
//        jobs.add(job);
        jobrepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
//        for (Job j : jobs){
//            if(j != null && j.getId().equals(id)){
//                return j;
//            }
//        }
//        return null;
        return jobrepository.findById(id).orElse(null);

    }

    @Override
    public Boolean delJobById(Long id) {
        try {
            jobrepository.deleteById( id );
            return true;
        } catch (Exception e){
            return false;
        }
//        for (Job j : jobs){
//            if(j != null && j.getId().equals(id)){
//                logger.info("Deleting following job: "+j);
//                jobs.remove(j);
//                logger.info("Job deleted");
//                return true;
//            }
//
//        }
//        return false;
    }

    @Override
    public Boolean updJobById(Long id, Job jobUpdated) {

        Optional<Job> jobOptional = jobrepository.findById(id);

        if (jobOptional.isPresent()) {
                Job job = jobOptional.get();
                job.setTitle(jobUpdated.getTitle());
                job.setDescription(jobUpdated.getDescription());
                job.setMinSalary(jobUpdated.getMinSalary());
                job.setMaxSalary(jobUpdated.getMaxSalary());
                job.setLocation(jobUpdated.getLocation());
                jobrepository.save(job);
                logger.info("Updated job: "+ job.toString());
                System.out.println(jobOptional.get().getTitle());
                return true;
        }
        logger.info("Job not found");
        return false;

//        for (Job j : jobs){
//            if(j != null && j.getId().equals(id)){
//                j.setTitle(updJob.getTitle());
//                j.setDescription(updJob.getDescription());
//                j.setMinSalary(updJob.getMinSalary());
//                j.setMaxSalary(updJob.getMaxSalary());
//                j.setLocation(updJob.getLocation());
//                logger.info("Updated job: "+ j.toString());
//                return true;
//            }
//        }
//        return false;
    }
}
