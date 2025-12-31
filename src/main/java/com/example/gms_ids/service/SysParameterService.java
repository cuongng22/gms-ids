package com.example.gms_ids.service;

import com.example.gms_ids.dto.request.SysParameterSearchRequest;
import com.example.gms_ids.dto.request.SysParameterRequest;
import com.example.gms_ids.table.SysParameter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SysParameterService {
    Page<SysParameter> findAll(int page, int size);
    Page<SysParameter> search(SysParameterSearchRequest request);
    SysParameter findById(String id) throws Exception;
    SysParameter create(SysParameterRequest request) throws Exception;
    SysParameter update(String id, SysParameterRequest request) throws Exception;
    void delete(String id) throws Exception;
    void deleteMultiple(List<String> ids) throws Exception;
}

