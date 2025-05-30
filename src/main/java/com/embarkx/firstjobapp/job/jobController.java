package com.embarkx.firstjobapp.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController  // returns in json format
@RequestMapping("/jobs")  // this is the base url
public class jobController {

    @Autowired
    private jobService jobservice;

    public jobController(jobService jobservice) {
        this.jobservice = jobservice;
    }


    @GetMapping()   //to send a http response against this data
    public ResponseEntity<List<Job>> findAll() {
        return new ResponseEntity<>(jobservice.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String>  createJob(@RequestBody Job job){
        jobservice.createJob(job);
        return new ResponseEntity<>("Job added success", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job = jobservice.getJobById(id);
        if(job != null)
        {
            return new ResponseEntity<>(job, HttpStatus.OK);
        }
        return new ResponseEntity<>(job, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long id){
        Boolean deleted = jobservice.delJobById(id);
        if(deleted)
        {
            return new ResponseEntity<>("Job deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("job not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job job){
        Boolean updated = jobservice.updJobById(id, job);
        if(updated)
        {
            return new ResponseEntity<>("Job updated", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("job not updated", HttpStatus.NOT_FOUND);
        }
    }
}
