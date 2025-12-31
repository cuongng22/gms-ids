package com.example.gms_ids.service;

import com.example.gms_ids.dto.request.SysParameterSearchRequest;
import com.example.gms_ids.dto.request.SysParameterRequest;
import com.example.gms_ids.repository.SysParameterRepository;
import com.example.gms_ids.table.SysParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SysParameterServiceImpl implements SysParameterService {
    private final SysParameterRepository sysParameterRepository;

    @Override
    public Page<SysParameter> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return sysParameterRepository.findAll(pageable);
    }

    @Override
    public Page<SysParameter> search(SysParameterSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        return sysParameterRepository.searchParameters(
                request.getParaCd(),
                request.getParaValue(),
                request.getParaFlg(),
                pageable
        );
    }

    @Override
    public SysParameter findById(String id) throws Exception {
        Optional<SysParameter> parameter = sysParameterRepository.findById(id);
        if (!parameter.isPresent()) {
            throw new Exception("Khong ton tai parameter");
        }
        return parameter.get();
    }

    @Override
    public SysParameter create(SysParameterRequest request) throws Exception {
        Optional<SysParameter> existing = sysParameterRepository.findByParaDomainAndParaCd(
                request.getParaDomain(), request.getParaCd());
        if (existing.isPresent()) {
            throw new Exception("Da ton tai parameter voi domain va code nay");
        }
        SysParameter parameter = new SysParameter();
        parameter.setParaDomain(request.getParaDomain());
        parameter.setParaCd(request.getParaCd());
        parameter.setParaValue(request.getParaValue());
        parameter.setDescription(request.getDescription());
        parameter.setActiveFlg(request.getActiveFlg() != null ? request.getActiveFlg() : 1);
        return sysParameterRepository.save(parameter);
    }

    @Override
    public SysParameter update(String id, SysParameterRequest request) throws Exception {
        Optional<SysParameter> parameterOpt = sysParameterRepository.findById(id);
        if (!parameterOpt.isPresent()) {
            throw new Exception("Khong ton tai parameter");
        }
        SysParameter parameter = parameterOpt.get();
        if (request.getParaDomain() != null) {
            parameter.setParaDomain(request.getParaDomain());
        }
        if (request.getParaCd() != null) {
            parameter.setParaCd(request.getParaCd());
        }
        if (request.getParaValue() != null) {
            parameter.setParaValue(request.getParaValue());
        }
        if (request.getDescription() != null) {
            parameter.setDescription(request.getDescription());
        }
        if (request.getActiveFlg() != null) {
            parameter.setActiveFlg(request.getActiveFlg());
        }
        return sysParameterRepository.save(parameter);
    }

    @Override
    public void delete(String id) throws Exception {
        Optional<SysParameter> parameter = sysParameterRepository.findById(id);
        if (!parameter.isPresent()) {
            throw new Exception("Khong ton tai parameter");
        }
        sysParameterRepository.deleteById(id);
    }

    @Override
    public void deleteMultiple(List<String> ids) throws Exception {
        if (ids == null || ids.isEmpty()) {
            throw new Exception("Danh sach id khong duoc rong");
        }
        List<SysParameter> parameters = sysParameterRepository.findAllById(ids);
        if (parameters.size() != ids.size()) {
            throw new Exception("Mot so parameter khong ton tai");
        }
        sysParameterRepository.deleteAllById(ids);
    }
}

