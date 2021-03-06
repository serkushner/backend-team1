package com.exadel.project.skill.repository;

import com.exadel.project.skill.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill, Long>, JpaSpecificationExecutor<Skill> {
    List<Skill> findByIdIn(List<Long> ids);

    Optional<Skill> findSkillByName(String name);
}