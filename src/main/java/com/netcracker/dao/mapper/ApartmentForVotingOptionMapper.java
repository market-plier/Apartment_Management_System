package com.netcracker.dao.mapper;

import com.netcracker.models.Apartment;
import com.netcracker.models.PojoBuilder.ApartmentBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartmentForVotingOptionMapper implements RowMapper<Apartment> {
    @Override
    public Apartment mapRow(ResultSet resultSet, int i) throws SQLException {
        Apartment apartment = new ApartmentBuilder()
                .withApartmentNumber(Integer.valueOf(resultSet.getString("apartment_number")))
                .withAccountId(new BigInteger(resultSet.getString("account_id")))
                .withFirstName(resultSet.getString("first_name"))
                .withLastName(resultSet.getString("last_name"))
                .build();
        return apartment;
    }
}
