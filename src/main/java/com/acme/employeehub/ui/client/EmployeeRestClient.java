package com.acme.employeehub.ui.client;

import com.acme.employeehub.ui.dto.CountryEmployeeCountDto;
import com.acme.employeehub.ui.dto.EmployeeDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.restclient.autoconfigure.RestClientSsl;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class EmployeeRestClient {
    private final RestClient restClient;

    public EmployeeRestClient(RestClient.Builder restClientBuilder, RestClientSsl ssl,
                              @Value("${employee.hub.service.base-url}") String baseUrl,
                              @Value("${employee.hub.service.use-ssl:false}") boolean useSsl) {
        RestClient.Builder builder = restClientBuilder.baseUrl(baseUrl);
        if (useSsl) {
            builder.apply(ssl.fromBundle("employee-hub-service"));
        }
        this.restClient = builder.build();
    }

    public List<EmployeeDto> getAllEmployees(int pageNumber, int pageSize) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/employees")
                        .queryParam("pageNumber", pageNumber)
                        .queryParam("pageSize", pageSize)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public List<EmployeeDto> getHighestPaidEmployee() {
        return restClient.get()
                .uri("/api/employees/highest-paid")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public List<CountryEmployeeCountDto> getCountryCounts() {
        return restClient.get()
                .uri("/api/employees/country-counts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
