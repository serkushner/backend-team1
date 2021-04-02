package com.exadel.project.skill.service;

import com.exadel.project.common.service.BaseService;
import com.exadel.project.skill.entity.Skill;
import com.exadel.project.skill.repository.SkillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class SkillService extends BaseService<Skill, SkillRepository> {
    public List<Skill> getSkillsByIds(List<Long> ids){
        return getRepository().findByIdIn(ids);
    }
}