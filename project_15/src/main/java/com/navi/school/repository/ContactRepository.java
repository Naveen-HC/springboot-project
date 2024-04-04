package com.navi.school.repository;

import com.navi.school.entity.Contact;
import com.navi.school.rowmapper.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ContactRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveContact(Contact contact){

        String sql = "INSERT INTO contact_details(name,mobile_num,email,subject,message,status,created_by,created_at)"+
                "VALUES(?,?,?,?,?,?,?,?)";
        int n = jdbcTemplate.update(sql, contact.getName(),contact.getMobileNum(),contact.getEmail(),contact.getSubject()
                ,contact.getMessage(),contact.getStatus(),contact.getCreatedBy(),contact.getCreatedAt());
        return n;
    }

    public List<Contact> getContactMessages(String open) {
        String sql = "SELECT * FROM contact_details WHERE status = ?";
        List<Contact> contacts = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, open);
            }
        }, new ContactRowMapper());
        return contacts;
    }

}
