package com.exadel.project.skill.service;

import com.exadel.project.common.service.BaseService;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.skill.entity.Skill;
import com.exadel.project.skill.repository.SkillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SkillService extends BaseService<Skill, SkillRepository> {

    private final SkillRepository skillRepository;

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public List<Skill> getSkillsByIds(List<Long> ids){
        return skillRepository.findByIdIn(ids);
    }

    public Skill getByName(String name) {
        return skillRepository.findSkillByName(name).orElseGet(() -> addByName(name));
    }

    public List<String> getSkillsNames() {
        return findBySpecifications(null, getSort("name")).stream().map(Skill::getName).collect(Collectors.toList());
    }

    public Skill addByName(String name) {
        Skill skill = new Skill();
        skill.setName(name);
        return skillRepository.save(skill);
    }
}