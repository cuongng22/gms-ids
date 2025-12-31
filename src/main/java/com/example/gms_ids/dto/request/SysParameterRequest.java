package com.example.gms_ids.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysParameterRequest {
    String id;
    String paraDomain;
    String paraCd;
    String paraValue;
    String description;
    Integer activeFlg;
}

