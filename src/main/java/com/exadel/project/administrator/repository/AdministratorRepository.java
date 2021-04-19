package com.exadel.project.administrator.repository;

import com.exadel.project.administrator.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AdministratorRepository extends JpaRepository<Administrator, Long>, JpaSpecificationExecutor<Administrator> {

    Administrator findAdministratorByEmail(String email);

    Administrator findAdministratorById(Long id);
}
