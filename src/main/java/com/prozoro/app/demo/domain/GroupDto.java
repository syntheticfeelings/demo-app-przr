package com.prozoro.app.demo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GroupDto {
    private String id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sectionId;
    private String description;
}
