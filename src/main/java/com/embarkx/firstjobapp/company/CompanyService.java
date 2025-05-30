package com.embarkx.firstjobapp.company;

import java.util.List;

public interface CompanyService {

    List<Company> findAllCompanies();

    void createCompany(Company job);

    Company getCompById(Long id);

    Boolean delCompany(Long id);

    Boolean updCompById(Long id, Company company);

}
