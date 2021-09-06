package com.example.assignment;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.List;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class EmployeeHttpClient {

    private final String SERVER_URL = "http://localhost";
    private final String THINGS_ENDPOINT = "/employees";

    @LocalServerPort
    private int port;
    private final RestTemplate restTemplate = new RestTemplate();

    private String thingsEndpoint() {
        return SERVER_URL + ":" + port + THINGS_ENDPOINT;
    }

    public Employee put(final Employee employee) {
        return restTemplate.postForEntity(thingsEndpoint(), employee, Employee.class).getBody();
    }

    public ResponseEntity<Employee> getContents(int id) {
        return restTemplate.getForEntity(thingsEndpoint()+"/"+id, Employee.class);
    }

    public List<Employee> getContents() {

        ResponseEntity<List<Employee>> rateResponse =
                restTemplate.exchange(thingsEndpoint(),
                        HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {});

        return  rateResponse.getBody();
    }

    public void put(final Employee employee,int id) {
         restTemplate.put(thingsEndpoint()+"/"+id, employee);
    }

    public void delete(int id){
         restTemplate.delete(thingsEndpoint()+"/"+id);
    }



}