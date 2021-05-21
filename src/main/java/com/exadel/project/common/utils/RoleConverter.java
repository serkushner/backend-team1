package com.exadel.project.common.utils;

import com.exadel.project.administrator.entity.Role;
import com.exadel.project.administrator.repository.AdministratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final AdministratorRepository administratorRepository;

    @Override
    public Collection<GrantedAuthority> convert(final Jwt jwt) {

        Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");

        Collection<GrantedAuthority> returnValue = new ArrayList<>();
        if (realmAccess == null || realmAccess.isEmpty()) {
            List<Role> roleList = new ArrayList<>(administratorRepository.findAdministratorByEmail(jwt.getClaims().get("email").toString()).getRoles());
            returnValue =  roleList
                    .stream().map(roleName -> "ROLE_" + roleName)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());

        } else {
            returnValue = ((List<String>) realmAccess.get("roles"))
                    .stream().map(roleName -> "ROLE_" + roleName)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
        return returnValue;
    }
}
