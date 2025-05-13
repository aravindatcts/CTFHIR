package com.aravind.ctfhir.practitioner.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.aravind.ctfhir.model.PractitionerEntity;
import com.aravind.ctfhir.model.PractitionerIdentifierDetail;
import com.aravind.ctfhir.model.CodeableConceptDetail;
import com.aravind.ctfhir.model.Extension;
import com.aravind.ctfhir.model.Practitioner;

@Repository
public class PractitionerDaoImpl implements PractitionerDao {

    private final JdbcTemplate jdbcTemplate;

    public PractitionerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class PractitionerRowMapper implements RowMapper<Practitioner> {
        @Override
        @SuppressWarnings("unchecked") // For casting rs.getObject() to Map types
        public Practitioner mapRow(ResultSet rs, int rowNum) throws SQLException {
            Practitioner practitioner = new Practitioner();
            practitioner.setPractitionerId(rs.getString("practitioner_id"));
            practitioner.setPractitionerText(rs.getString("practitioner_text"));
            
            // Handle nullable boolean
            practitioner.setPractitionerActive((Boolean) rs.getObject("practitioner_active"));

            // Handle practitioner_identifier: MAP<String, STRUCT<...>>
            Map<String, Object> rawIdentifiersMap = (Map<String, Object>) rs.getObject("practitioner_identifier");
            if (rawIdentifiersMap != null) {
                Map<String, PractitionerIdentifierDetail> identifiers = new HashMap<>();
                for (Map.Entry<String, Object> entry : rawIdentifiersMap.entrySet()) {
                    Map<String, Object> structData = (Map<String, Object>) entry.getValue();
                    PractitionerIdentifierDetail detail = new PractitionerIdentifierDetail();
                    detail.setPractitionerId((String) structData.get("practitioner_id"));
                    detail.setPractitionerSystem((String) structData.get("practitioner_system"));
                    detail.setPractitionerUse((String) structData.get("practitioner_use"));
                    detail.setPractitionerValue((String) structData.get("practitioner_value"));
                    detail.setPractitionerTypeCode((String) structData.get("practitioner_type_code"));
                    detail.setPractitionerTypeDisplay((String) structData.get("practitioner_type_display"));
                    detail.setPractitionerAssinger((String) structData.get("practitioner_assinger")); // Note: 'assinger' as per schema
                    identifiers.put(entry.getKey(), detail);
                }
                practitioner.setPractitionerIdentifier(identifiers);
            }

            // Handle extension: MAP<String, STRUCT<...>>
            Map<String, Object> rawExtensionsMap = (Map<String, Object>) rs.getObject("extension");
            if (rawExtensionsMap != null) {
                Map<String, Extension> extensions = new HashMap<>();
                for (Map.Entry<String, Object> entry : rawExtensionsMap.entrySet()) {
                    Map<String, Object> structData = (Map<String, Object>) entry.getValue();
                    Extension extDetail = new Extension();
                    extDetail.setUrl((String) structData.get("url"));
                    extDetail.setKey((String) structData.get("key"));
                    extDetail.setSystem((String) structData.get("system"));
                    extDetail.setText((String) structData.get("text"));
                    extDetail.setValue((String) structData.get("value"));

                    // Handle nested codableConcept: MAP<String, STRUCT<...>>
                    Map<String, Object> rawCodeableConceptsMap = (Map<String, Object>) structData.get("codableConcept");
                    if (rawCodeableConceptsMap != null) {
                        Map<String, CodeableConceptDetail> codeableConcepts = new HashMap<>();
                        for (Map.Entry<String, Object> ccEntry : rawCodeableConceptsMap.entrySet()) {
                            Map<String, Object> ccStructData = (Map<String, Object>) ccEntry.getValue();
                            CodeableConceptDetail ccDetail = new CodeableConceptDetail();
                            ccDetail.setCode((String) ccStructData.get("code"));
                            ccDetail.setDisplay((String) ccStructData.get("display"));
                            codeableConcepts.put(ccEntry.getKey(), ccDetail);
                        }
                        extDetail.setCodeableConcept(codeableConcepts);
                    }
                    extensions.put(entry.getKey(), extDetail);
                }
                practitioner.setExtension(extensions);
            }

            return practitioner;
        }
    }
    
    private final PractitionerRowMapper rowMapper = new PractitionerRowMapper();

    @Override
    public Optional<Practitioner> findById(Long id) {
       String sql = "SELECT practitioner_id, practitioner_text, practitioner_identifier, practitioner_active, extension " +
                     "FROM abacus.practitioner WHERE practitioner_id = ?";
        try {
            Practitioner practitioner = jdbcTemplate.queryForObject(sql, new Object[]{id}, new PractitionerRowMapper());
            return Optional.ofNullable(practitioner);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Practitioner> findByParams(Map<String, Object> searchParams) {
        if (searchParams == null || searchParams.isEmpty()) {
            return new ArrayList<>();
        }

        StringBuilder sql = new StringBuilder("SELECT * FROM abacus.practitioner WHERE 1=1");
        List<Object> params = new ArrayList<>();

        for (Map.Entry<String, Object> entry : searchParams.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            
            if (value != null) {
                sql.append(" AND ").append(key).append(" = ?");
                params.add(value);
            }
        }

        return jdbcTemplate.query(sql.toString(), params.toArray(), rowMapper);
    }
}