package com.netcracker.dao.impl;

import com.netcracker.dao.ApartmentDao;
import com.netcracker.dao.mapper.ApartmentMapper;
import com.netcracker.models.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public class ApartmentDaoImpl implements ApartmentDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Apartment> getAllApartments() {
        return jdbcTemplate.query(GET_ALL_APARTMENTS, new ApartmentMapper());
    }

    @Override
    public Apartment getApartmentById(BigInteger apartmentId) {
        Apartment apartment = (Apartment) jdbcTemplate.queryForObject(GET_APARTMENT_BY_ID, new ApartmentMapper(), apartmentId);
        return apartment;
    }

    @Override
    public void createApartment(Apartment apartment) {
        jdbcTemplate.update(CREATE_APARTMENT,
                apartment.getRole(),
                apartment.getEmail(),
                apartment.getPassword(),
                apartment.getFirstName(),
                apartment.getLastName(),
                apartment.getPhoneNumber(),
                apartment.getApartmentNumber(),
                apartment.getSquareMetres(),
                apartment.getFloor(),
                apartment.getPeopleCount());
    }

    @Override
    public void updateApartment(Apartment apartment) {
        jdbcTemplate.update(UPDATE_APARTMENT,
                apartment.getApartmentNumber(),
                apartment.getSquareMetres(),
                apartment.getFloor(),
                apartment.getPeopleCount(),
                apartment.getAccountId());
    }

}
