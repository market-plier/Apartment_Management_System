package com.netcracker.dao.impl;

import com.netcracker.dao.ApartmentDao;
import com.netcracker.dao.mapper.ApartmentMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public class ApartmentDaoImpl implements ApartmentDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ApartmentDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<Apartment> getAllApartments() throws DaoAccessException {
        return jdbcTemplate.query(GET_ALL_APARTMENTS, new ApartmentMapper());
    }

    @Override
    public Apartment getApartmentById(BigInteger apartmentId) throws DaoAccessException {
        Apartment apartment = (Apartment) jdbcTemplate.queryForObject(GET_APARTMENT_BY_ID, new ApartmentMapper(), apartmentId);
        return apartment;
    }

    @Override
    public void createApartment(Apartment apartment) throws DaoAccessException {
        jdbcTemplate.update(CREATE_APARTMENT_OBJECT);
        jdbcTemplate.update(CREATE_APARTMENT_ATTRIBUTES,
                apartment.getRole().getName(),
                apartment.getEmail(),
                apartment.getPassword(),
                apartment.getFirstName(),
                apartment.getLastName(),
                apartment.getPhoneNumber(),
                apartment.getApartmentNumber().toString(),
                apartment.getSquareMetres().toString(),
                apartment.getFloor().toString(),
                apartment.getPeopleCount().toString());
    }

    @Override
    public void updateApartment(Apartment apartment) throws DaoAccessException {
        jdbcTemplate.update(UPDATE_APARTMENT,
                apartment.getApartmentNumber(),
                apartment.getSquareMetres(),
                apartment.getFloor(),
                apartment.getPeopleCount(),
                apartment.getAccountId());
    }

}
