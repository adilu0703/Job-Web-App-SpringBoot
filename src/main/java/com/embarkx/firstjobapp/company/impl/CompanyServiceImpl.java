package com.embarkx.firstjobapp.company.impl;

import com.embarkx.firstjobapp.company.Company;
import com.embarkx.firstjobapp.company.CompanyRepository;
import com.embarkx.firstjobapp.company.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger( CompanyServiceImpl.class);

    CompanyRepository companyRepository;

    //private List<Job> jobs = new ArrayList<>();

    public CompanyServiceImpl(CompanyRepository companyRepository)
    {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void createCompany( Company company ) {
        companyRepository.save(company);
    }

    @Override
    public Company getCompById( Long id ) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean delCompany( Long id ) {
         try {
            companyRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean updCompById( Long id , Company compUpdated ) {

        Optional<Company> jobOptional = companyRepository.findById(id);

        if (jobOptional.isPresent()) {
            Company company = jobOptional.get();
            company.setName(compUpdated.getName());
            company.setDescription(compUpdated.getDescription());
            company.setJobs(compUpdated.getJobs());

            companyRepository.save(company);
            logger.info("Updated Company: "+ company.toString());
            return true;
        }
        logger.info("Company not found");
        return false;
    }


}
