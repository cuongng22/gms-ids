package com.example.gms_ids.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeleteRequest {
    String id;
    List<String> ids;
}
