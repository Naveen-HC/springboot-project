package com.navi.school.repository;

import com.navi.school.entity.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findByStatus(String status);

    @Transactional
    @Modifying
    @Query("update Contact c set status = :status where contactId = :id")
    int updatedStatusBYId(@Param("status") String status, int id);

    @Transactional
    @Modifying
    int updateStatus(String status, int id);

    Page<Contact>  (String status);
}
