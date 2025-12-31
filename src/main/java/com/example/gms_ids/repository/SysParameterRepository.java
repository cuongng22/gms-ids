package com.example.gms_ids.repository;

import com.example.gms_ids.table.SysParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SysParameterRepository extends JpaRepository<SysParameter, String> {
    Optional<SysParameter> findByParaDomainAndParaCd(String paraDomain, String paraCd);

    @Query("SELECT p FROM SysParameter p " +
           "WHERE (:paraCd IS NULL OR :paraCd = '' OR LOWER(p.paraCd) LIKE LOWER(CONCAT('%', :paraCd, '%'))) " +
           "AND (:paraValue IS NULL OR :paraValue = '' OR LOWER(p.paraValue) LIKE LOWER(CONCAT('%', :paraValue, '%'))) " +
           "AND (:paraFlg IS NULL OR p.activeFlg = :paraFlg)")
    Page<SysParameter> searchParameters(
            @Param("paraCd") String paraCd,
            @Param("paraValue") String paraValue,
            @Param("paraFlg") Integer paraFlg,
            Pageable pageable
    );
}

