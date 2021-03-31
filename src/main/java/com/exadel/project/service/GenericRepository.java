package com.exadel.project.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

@Component
@NoRepositoryBean
public interface GenericRepository<T, U> extends JpaRepository<T, U> {
}
