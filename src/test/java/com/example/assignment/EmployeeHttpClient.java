package com.example.assignment;

import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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

    public Employee getContents(int id) {
        return restTemplate.getForEntity(thingsEndpoint()+"/"+id, Employee.class).getBody();
    }

    public List<Employee> getContents() {
        return (List<Employee>) restTemplate.getForObject(thingsEndpoint(),List.class);
    }

    public void delete(int id){
         restTemplate.delete(thingsEndpoint()+"/"+id);
    }


}