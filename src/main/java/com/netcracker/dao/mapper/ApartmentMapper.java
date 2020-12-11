package com.netcracker.dao.mapper;

import com.netcracker.models.Apartment;
import com.netcracker.models.PojoBuilder.ApartmentBuilder;
import com.netcracker.models.Role;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartmentMapper implements RowMapper<Apartment> {
    @Override
    public Apartment mapRow(ResultSet resultSet, int i) throws SQLException {
        Apartment apartment = new ApartmentBuilder()
                .withApartmentNumber(Integer.valueOf(resultSet.getString("apartment_number")))
                .withSquareMeters(Integer.valueOf(resultSet.getString("square_metres")))
                .withFloor(Integer.valueOf(resultSet.getString("floor")))
                .withPeopleCount(Integer.valueOf(resultSet.getString("people_count")))
                .withAccountId(new BigInteger(resultSet.getString("account_id")))
                .withFirstName(resultSet.getString("first_name"))
                .withLastName(resultSet.getString("last_name"))
                .withEmail(resultSet.getString("email"))
                .withPassword(resultSet.getString("password"))
                .withPhoneNumber(resultSet.getString("phone_number"))
                .withRole(Role.valueOf(resultSet.getString("role_name")))
                .build();
        return apartment;
    }
}
