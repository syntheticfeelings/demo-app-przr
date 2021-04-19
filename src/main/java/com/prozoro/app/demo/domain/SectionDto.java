package com.prozoro.app.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class SectionDto {
    private String id;
    private String description;
    @JsonInclude(Include.NON_NULL)
    private List<GroupDto> groups;
}
