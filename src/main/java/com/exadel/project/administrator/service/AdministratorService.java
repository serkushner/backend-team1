package com.exadel.project.administrator.service;

import com.exadel.project.administrator.dto.AdministratorDto;
import com.exadel.project.administrator.dto.ChangeRoleDto;
import com.exadel.project.administrator.entity.Administrator;
import com.exadel.project.administrator.mapper.AdministratorMapper;
import com.exadel.project.administrator.repository.AdministratorRepository;
import com.exadel.project.administrator.repository.RoleRepository;
import com.exadel.project.administrator.validator.AdministratorValidator;
import com.exadel.project.common.exception.EntityAlreadyExistsException;
import com.exadel.project.common.exception.EntityNotFoundException;
import com.exadel.project.common.service.rsql.RsqlSpecification;
import com.exadel.project.common.service.BaseService;
import com.exadel.project.configurations.KeycloakConfigProperties;
import com.exadel.project.trainee.entity.Trainee;
import com.exadel.project.trainee.repository.TraineeRepository;
import com.exadel.project.trainee.service.TraineeService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdministratorService extends BaseService<Administrator, AdministratorRepository> {

    private final AdministratorMapper administratorMapper;
    private final AdministratorValidator administratorValidator;
    private final PasswordEncoder passwordEncoder;
    private final AdministratorRepository administratorRepository;
    private final TraineeRepository traineeRepository;
    private final TraineeService traineeService;
    private final KeycloakConfigProperties keycloakConfigProperties;
    private final RoleRepository roleRepository;

    {
        defaultSortingField = "surname";
        defaultSortingDirection = "asc";
    }

    @Override
    public RsqlSpecification getRsqlSpecification() {
        throw new UnsupportedOperationException();
    }

    public List<AdministratorDto> findBySpecification(String search, String sortFields) {
        Sort sort = getSort(sortFields);
        return super.findBySpecifications(search, sort)
                .stream()
                .map(administratorMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public AdministratorDto getAdministratorByLogin(String login) throws EntityNotFoundException {
        return administratorMapper.entityToDto(findAdministratorByLogin(login));
    }

    public AdministratorDto getById(Long id) throws EntityNotFoundException {
        return administratorMapper.entityToDto(super.getEntityById(id));
    }

    public AdministratorDto addAdministrator(AdministratorDto administratorDto) throws EntityAlreadyExistsException {
        administratorValidator.checkAdministratorAlreadyExists(administratorDto);
        Administrator administrator = administratorMapper.dtoToEntity(administratorDto);
        administrator.getRoles().add(roleRepository.findByRoleName("ADMIN"));
        administratorRepository.save(administrator);
        return administratorMapper.entityToDto(administrator);
    }

    public AdministratorDto updateAdministrator(Long id, AdministratorDto administratorDto) throws EntityNotFoundException {
        Administrator administrator = getEntityById(id);
        administratorMapper.updateAdministrator(administratorDto, administrator);
        administratorRepository.save(administrator);
        return administratorMapper.entityToDto(administrator);
    }

    public AdministratorDto changeAdministratorRole(Long id, ChangeRoleDto role) throws EntityNotFoundException {
        Administrator existingAdministrator = getEntityById(id);
        existingAdministrator.getRoles().add(roleRepository.findByRoleName(role.getRole().toUpperCase()));
        administratorRepository.save(existingAdministrator);
        return administratorMapper.entityToDto(existingAdministrator);
    }

    public void deleteAdministratorById(Long id) throws EntityNotFoundException {
        administratorRepository.delete(getEntityById(id));
    }

    public AdministratorDto addTraineeToAdministrator(Long administratorId, Long traineeId) {
        Trainee trainee = traineeService.getEntityById(traineeId);
        Administrator administrator = getEntityById(administratorId);
        trainee.setAdministrator(administrator);
        traineeRepository.save(trainee);
        return administratorMapper.entityToDto(administrator);
    }

    public boolean verifyAdministratorPassword(String login, String password) {
        if (passwordEncoder.matches(password, findAdministratorByLogin(login).getPassword())) {
            return true;
        }
        return false;
    }

    public String redirectToKeycloak (String username, String password) throws IOException, IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        String url = keycloakConfigProperties.getAuthServerUrl() + "/realms/" + keycloakConfigProperties.getRealm() + "/protocol/openid-connect/token";

        List nameValuePairs = new ArrayList();
        nameValuePairs.add(new BasicNameValuePair("client_id", keycloakConfigProperties.getClientId()));
        nameValuePairs.add(new BasicNameValuePair("username", username));
        nameValuePairs.add(new BasicNameValuePair("grant_type", keycloakConfigProperties.getGrantType()));
        nameValuePairs.add(new BasicNameValuePair("password", password));
        nameValuePairs.add(new BasicNameValuePair("client_secret", keycloakConfigProperties.getClientSecret()));
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, StandardCharsets.UTF_8));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();

        String result = EntityUtils.toString(entity);
        httpClient.close();
        return result;
    }

    private Administrator findAdministratorByLogin(String login) throws EntityNotFoundException {
        return Optional.ofNullable(administratorRepository.findAdministratorByLogin(login))
                .orElseThrow(() -> new EntityNotFoundException());
    }

}
