package com.netcracker.dao.impl;

import com.netcracker.dao.ApartmentDao;
import com.netcracker.dao.mapper.ApartmentMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.DaoAccessExceptionBuilder;
import com.netcracker.models.Apartment;
import javassist.NotFoundException;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
@Log4j
public class ApartmentDaoImpl implements ApartmentDao {
    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ApartmentDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public List<Apartment> getAllApartments() throws DaoAccessException {
        try {
            List<Apartment> list = jdbcTemplate.query(GET_ALL_APARTMENTS, new ApartmentMapper());
            if (list.isEmpty()) {
                throw new NotFoundException(EXCEPTION_NO_APARTMENTS_WERE_FOUND);
            }
            return list;
        } catch (DataAccessException e) {
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_ALL_APARTMENTS)
                    .withCause(e.getCause())
                    .withErrorMessage(BigInteger.valueOf(73))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        } catch (NotFoundException e) {
            throw new DaoAccessExceptionBuilder()
                    .withMessage(e.getMessage())
                    .withErrorMessage(BigInteger.valueOf(76))
                    .build();
        }
    }

    @Override
    public Apartment getApartmentById(BigInteger apartmentId) throws DaoAccessException {
        try {
            return jdbcTemplate.queryForObject(GET_APARTMENT_BY_ID, new ApartmentMapper(), apartmentId);
        } catch (DataAccessException e) {
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_APARTMENT_BY_ACCOUNT_ID)
                    .withCause(e.getCause())
                    .withId(apartmentId)
                    .withErrorMessage(BigInteger.valueOf(73))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public boolean createApartment(Apartment apartment) throws DaoAccessException {
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
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_CREATE_APARTMENT)
                    .withCause(e.getCause())
                    .withErrorMessage(BigInteger.valueOf(71))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
        return true;
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
        } catch (DataAccessException e) {
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_UPDATE_APARTMENT)
                    .withCause(e.getCause())
                    .withId(apartment.getAccountId())
                    .withErrorMessage(BigInteger.valueOf(72))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    public Apartment getUniqueApartment(Apartment apartment) throws DaoAccessException {
        try {
            return jdbcTemplate.queryForObject(GET_UNIQUE_APARTMENT_BY_APT_NUM, new ApartmentMapper(),
                    apartment.getApartmentNumber());
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            e = new DaoAccessExceptionBuilder()
                    .withMessage("Unique Apartments failed")
                    .withCause(e.getCause())
                    .withId(apartment.getAccountId())
                    .withErrorMessage(BigInteger.valueOf(73))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Apartment getApartmentByApartmentNumber(int apartmentNumber) {
        try {
            return jdbcTemplate.queryForObject(GET_APARTMENT_BY_APARTMENT_NUMBER, new ApartmentMapper(), apartmentNumber);
        } catch (DataAccessException e) {
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_APARTMENT_BY_APARTMENT_NUMBER)
                    .withCause(e.getCause())
                    .withErrorMessage(BigInteger.valueOf(73))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<Apartment> getAllApartmentByFloor(List<Integer> floor) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("floor_list", floor);

        try {
            List<Apartment> list = namedParameterJdbcTemplate.query(GET_ALL_APARTMENTS_BY_FLOOR,
                    parameters, new ApartmentMapper());
            if (list.isEmpty()) {
                throw new NotFoundException(EXCEPTION_NO_APARTMENTS_WERE_FOUND);
            }
            return list;
        } catch (DataAccessException e) {
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_ALL_APARTMENTS_BY_FLOOR)
                    .withCause(e.getCause())
                    .withErrorMessage(BigInteger.valueOf(73))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        } catch (NotFoundException ex) {
            throw new DaoAccessExceptionBuilder()
                    .withMessage(ex.getMessage())
                    .withErrorMessage(BigInteger.valueOf(76))
                    .build();
        }
    }
}
