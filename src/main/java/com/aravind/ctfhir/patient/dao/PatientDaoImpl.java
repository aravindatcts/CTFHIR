package com.aravind.ctfhir.patient.dao;

import java.util.List;
import java.util.Optional;

import com.aravind.ctfhir.model.PatientEntity;

//@Repository // Marks this class as a Spring repository
public class PatientDaoImpl implements PatientDao {

    @Override
    public Optional<PatientEntity> findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<PatientEntity> findByLastName(String lastName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByLastName'");
    }

    // private final JdbcTemplate jdbcTemplate;

    // @Autowired // Inject JdbcTemplate
    // public PatientDaoImpl(JdbcTemplate jdbcTemplate) {
    //     this.jdbcTemplate = jdbcTemplate;
    // }

    // // RowMapper to map a database row to a PatientEntity object
    // private static final class PatientRowMapper implements RowMapper<PatientEntity> {
    //     @Override
    //     public PatientEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
    //         PatientEntity patient = new PatientEntity();
    //         patient.setId(rs.getLong("id"));
    //         patient.setSystemIdentifier(rs.getString("system_identifier"));
    //         patient.setValueIdentifier(rs.getString("value_identifier"));
    //         patient.setFirstName(rs.getString("first_name"));
    //         patient.setLastName(rs.getString("last_name"));
    //         patient.setGender(rs.getString("gender"));
    //         patient.setBirthDate(rs.getDate("birth_date")); // Maps SQL DATE to java.util.Date
    //         patient.setIsActive(rs.getBoolean("is_active"));
    //         patient.setStreetAddress(rs.getString("street_address"));
    //         patient.setCity(rs.getString("city"));
    //         patient.setState(rs.getString("state"));
    //         patient.setZipCode(rs.getString("zip_code"));
    //         patient.setPhoneNumber(rs.getString("phone_number"));
    //         patient.setEmailAddress(rs.getString("email_address"));
    //         // Map other fields as needed
    //         return patient;
    //     }
    // }

    // private final PatientRowMapper rowMapper = new PatientRowMapper();

    // @Override
    // public Optional<PatientEntity> findById(Long id) {
    //     String sql = "SELECT id, system_identifier, value_identifier, first_name, last_name, gender, 
    //  birth_date, is_active, street_address, city, state, zip_code, phone_number, email_address FROM patients WHERE id = ?";
    //     try {
    //         // queryForObject is used for single results, throws EmptyResultDataAccessException if none found
    //         PatientEntity patient = jdbcTemplate.queryForObject(sql, new Object[]{id}, rowMapper);
    //         return Optional.ofNullable(patient);
    //     } catch (org.springframework.dao.EmptyResultDataAccessException e) {
    //         // Handle case where no patient is found with the given ID
    //         return Optional.empty();
    //     }
    // }

    // @Override
    // public List<PatientEntity> findByLastName(String lastName) {
    //     String sql = "SELECT id, system_identifier, value_identifier, first_name, last_name, gender, birth_date, is_active, street_address, city, state, zip_code, phone_number, email_address FROM patients WHERE last_name = ?";
    //     // query is used for lists of results
    //     return jdbcTemplate.query(sql, new Object[]{lastName}, rowMapper);
    // }

    // Implement other methods from the interface here
}
