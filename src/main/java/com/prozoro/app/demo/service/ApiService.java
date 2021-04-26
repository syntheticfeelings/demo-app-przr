package com.prozoro.app.demo.service;

import com.prozoro.app.demo.domain.SectionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
@Service
public class ApiService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${classifiers.apiUrl}")
    private String classifiersApiUrl;

    public Collection<SectionDto> getClassifiers() {
        return transformData(getDataFromApi());
    }

    private Map<String, String> getDataFromApi() {
        log.info("getting data from service ...");
        Map<String, String> apiResponse = restTemplate.getForObject(classifiersApiUrl, Map.class);
        log.info("getting data from service finished");
        return apiResponse;
    }

    private Collection<SectionDto> transformData(Map<String, String> data) {
        Map<String, SectionDto> sections = new TreeMap<>();
        log.info("transform data ...");
        data.forEach((code, description) -> {

            if (code.matches("\\d{0,2}[0]{6}..")) {
                SectionDto section = new SectionDto(code, description, "00");
                sections.put(code, section);
            }
            if (code.matches("...[0]{5}..") && !code.matches("\\d{0,2}[0]{6}..")) {
                SectionDto section = new SectionDto(code, description, code.substring(0, 2));
                sections.put(code, section);
            }
            if (code.matches(".([1-9]){3}[0]{4}..")) {
                SectionDto section = new SectionDto(code, description, code.substring(0, 3));
                sections.put(code, section);
            }
            if (code.matches(".([1-9]){4}[0]{3}..")) {
                SectionDto section = new SectionDto(code, description, code.substring(0, 4));
                sections.put(code, section);
            }
            if (code.matches(".([1-9]){5}[0]{2}..")) {
                SectionDto section = new SectionDto(code, description, code.substring(0, 5));
                sections.put(code, section);
            }
            if (code.matches(".([1-9]){6}[0]..")) {
                SectionDto section = new SectionDto(code, description, code.substring(0, 6));
                sections.put(code, section);
            }
            if (code.matches(".([1-9]){7}..")) {
                SectionDto section = new SectionDto(code, description, code.substring(0, 7));
                sections.put(code, section);
            }

        });
        log.info("transform data finished");
        return sections.values();
    }

}
