package com.exadel.project.skill.service;

import com.exadel.project.skill.entity.Skill;
import com.exadel.project.skill.repository.SkillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SkillService {
    private SkillRepository skillRepository;

    public List<Skill> getAllSkills(){
        return skillRepository.findAll();
    }

    public List<Skill> getSkillsByIds(Set<Long> ids){
        return ids.stream()
                .map(this::getSkillById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Skill getSkillById(Long id){
        return skillRepository.findById(id).orElse(null);
    }
}
