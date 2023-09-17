package com.depo.deposervice.dto.request.requirement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequirementCreateRequest {

    private String name;
    private String version;
}
