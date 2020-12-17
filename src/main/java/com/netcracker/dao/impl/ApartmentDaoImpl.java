package com.netcracker.dao.impl;

import com.netcracker.dao.ApartmentDao;
import com.netcracker.dao.mapper.ApartmentMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
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
        try {
            return jdbcTemplate.query(GET_ALL_APARTMENTS, new ApartmentMapper());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_ALL_APARTMENTS, e.getCause());
        }
    }

    @Override
    public Apartment getApartmentById(BigInteger apartmentId) throws DaoAccessException {
        try {
            Apartment apartment = jdbcTemplate.queryForObject(GET_APARTMENT_BY_ID, new ApartmentMapper(), apartmentId);
            return apartment;
        } catch (
                DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_APARTMENT_BY_ACCOUNT_ID, e.getCause());
        }
    }

    @Override
    public void createApartment(Apartment apartment) throws DaoAccessException {
        try {
            jdbcTemplate.update(CREATE_APARTMENT_OBJECT);
            jdbcTemplate.update(CREATE_APARTMENT_ATTRIBUTES,
                    apartment.getEmail(),
                    apartment.getPassword(),
                    apartment.getFirstName(),
                    apartment.getLastName(),
                    apartment.getPhoneNumber(),
                    apartment.getApartmentNumber().toString(),
                    apartment.getSquareMetres().toString(),
                    apartment.getFloor().toString(),
                    apartment.getPeopleCount().toString());
        } catch (
                DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_CREATE_APARTMENT, e.getCause());
        }
    }

    @Override
    public void updateApartment(Apartment apartment) throws DaoAccessException {
        try {
            jdbcTemplate.update(UPDATE_APARTMENT,
                    apartment.getApartmentNumber(),
                    apartment.getSquareMetres(),
                    apartment.getFloor(),
                    apartment.getPeopleCount(),
                    apartment.getAccountId());
        } catch (
                DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_UPDATE_APARTMENT, e.getCause());
        }
    }
    public List<Apartment> getUniqueApartment(Apartment apartment) throws DaoAccessException {
        return  jdbcTemplate.query(GET_APARTMENT_BY_EMAIL_FLOOR_APT_NUM, new ApartmentMapper(),
                apartment.getFloor(),apartment.getApartmentNumber(),apartment.getEmail());
    }

}
