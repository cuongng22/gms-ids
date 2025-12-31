package com.example.gms_ids.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {
    String userName;
    String companyName;
    String email;
    String phoneNo;
    Long departmentId;
    Integer positionCode;
    int page = 0;
    int size = 10;
}


