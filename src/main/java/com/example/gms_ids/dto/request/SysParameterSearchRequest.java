package com.example.gms_ids.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SysParameterSearchRequest {
    String paraCd;
    String paraValue;
    Integer paraFlg;
    int page = 0;
    int size = 10;
}

