package com.exadel.project.skill.service;

import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.skill.entity.Skill;
import com.exadel.project.skill.repository.SkillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class SkillService {
    private SkillRepository skillRepository;

    public List<Skill> getAllSkills(){
        return skillRepository.findAll();
    }

    public List<Skill> getSkillsByIds(List<Long> ids){
        return skillRepository.findByIdIn(ids);
    }

    public Skill getSkillById(Long id) throws EntityNotFoundException {
        Optional<Skill> optionalSkill = skillRepository.findById(id);
        if (optionalSkill.isPresent()){
            return optionalSkill.get();
        }else {
            throw new EntityNotFoundException();
        }
    }
}
