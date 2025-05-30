package com.embarkx.firstjobapp.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // returns in json format
@RequestMapping("/companies")  // this is the base url

public class CompanyController {

    @Autowired
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping()   //to send a http response against this data
    public ResponseEntity<List<Company>> findAllComp() {
        return new ResponseEntity<>(companyService.findAllCompanies(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String>  createComp(@RequestBody Company company){
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created success", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompById( @PathVariable Long id){
        Company company = companyService.getCompById( id );
        if(company != null)
        {
            return new ResponseEntity<>(company, HttpStatus.OK);
        }
        return new ResponseEntity<>(company, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompById(@PathVariable Long id){
        Boolean deleted = companyService.delCompany( id );
        if(deleted)
        {
            return new ResponseEntity<>("Company deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Company not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompById(@PathVariable Long id, @RequestBody Company company){
        Boolean updated = companyService.updCompById( id,company );
        if(updated)
        {
            return new ResponseEntity<>("Company updated", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Company not updated", HttpStatus.NOT_FOUND);
        }
    }
}
