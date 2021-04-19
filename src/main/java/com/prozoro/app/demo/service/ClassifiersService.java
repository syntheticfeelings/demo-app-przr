package com.prozoro.app.demo.service;

import com.prozoro.app.demo.domain.GroupDto;
import com.prozoro.app.demo.domain.SectionDto;
import com.prozoro.app.demo.repository.ClassifiersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ClassifiersService {

    @Value("${classifiers.batchsize:100}")
    private Integer batchSize;

    @Autowired
    private ClassifiersRepository repository;

    @Autowired
    private ApiService apiService;

    @Transactional
    public void updateClassifiersData() {
        Collection<SectionDto> classifiers = apiService.getClassifiers();
        batchSectionUpdate(classifiers);
        batchGroupUpdate(classifiers);
    }

    @Transactional
    public List<SectionDto> findAllSectionDto(){
        return repository.findAllSectionDto();
    }

    @Transactional
    public SectionDto findSectionDto(String sectionId){
        SectionDto sectionDto = repository.findSectionDto(sectionId);
        return  sectionDto;
    }

    @Transactional
    public List<GroupDto> findGroupDto(String sectionId){
        return  repository.findGroupDto(sectionId);
    }

    private void batchSectionUpdate(Collection<SectionDto> classifiers) {
        log.info("update section ...");
        repository.batchSectionUpdate(classifiers, batchSize);
        log.info("update section finished ...");
    }

    private void batchGroupUpdate(Collection<SectionDto> classifiers) {
        log.info("update group ...");
        List<GroupDto> groups = classifiers.stream()
                .flatMap(section -> section.getGroups().stream())
                .collect(Collectors.toList());

        repository.batchGroupUpdate(groups, batchSize);
        log.info("update group finished...");
    }

}