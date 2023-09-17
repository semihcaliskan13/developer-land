package com.depo.deposervice.dto.request.requirement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequirementUpdateRequest {
    private String id;
    private String name;
    private String version;
}
