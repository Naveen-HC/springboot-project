package com.navi.school.rowmapper;


import com.navi.school.entity.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper {


    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

        Contact contact = new Contact();
        contact.setContactId(rs.getInt("contact_id"));
        contact.setName(rs.getNString("name"));
        contact.setEmail(rs.getString("email"));
        contact.setMobileNum(rs.getString("mobile_num"));
        contact.setSubject(rs.getString("subject"));
        contact.setMessage(rs.getString("message"));
        contact.setStatus(rs.getString("status"));
        contact.setCreatedBy(rs.getString("created_by"));
        contact.setUpdatedBy(rs.getString("updated_by"));
        contact.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        if(rs.getTimestamp("updated_at") != null){
            contact.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        }
        return contact;
    }
}
