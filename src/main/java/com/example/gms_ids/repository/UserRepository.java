package com.example.gms_ids.repository;

import com.example.gms_ids.table.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findUserByUserName(String userName);

    @Query("SELECT DISTINCT u FROM User u " +
           "LEFT JOIN CompanyProfile cp ON TO_CHAR(u.companyProfileId) = cp.id " +
           "WHERE (:userName IS NULL OR :userName = '' OR LOWER(u.userName) LIKE LOWER(CONCAT('%', :userName, '%'))) " +
           "AND (:email IS NULL OR :email = '' OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))) " +
           "AND (:phoneNo IS NULL OR :phoneNo = '' OR u.phoneNo LIKE CONCAT('%', :phoneNo, '%')) " +
           "AND (:departmentId IS NULL OR u.departmentId = :departmentId) " +
           "AND (:positionCode IS NULL OR u.positionCode = :positionCode) " +
           "AND (:companyName IS NULL OR :companyName = '' OR LOWER(cp.companyName) LIKE LOWER(CONCAT('%', :companyName, '%')))")
    Page<User> searchUsers(
            @Param("userName") String userName,
            @Param("email") String email,
            @Param("phoneNo") String phoneNo,
            @Param("departmentId") Long departmentId,
            @Param("positionCode") Integer positionCode,
            @Param("companyName") String companyName,
            Pageable pageable
    );
}
