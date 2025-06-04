package com.aravind.ctfhir.insuranceplan.dao;

import com.aravind.ctfhir.model.InsurancePlanEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class InsurancePlanDaoImpl implements InsurancePlanDao {

    private final JdbcTemplate jdbcTemplate;

    public InsurancePlanDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<InsurancePlanEntity> findById(Long id) {
        try {
            String sql = "SELECT * FROM insurance_plan WHERE id = ?";
            InsurancePlanEntity entity = jdbcTemplate.queryForObject(sql, new InsurancePlanRowMapper(), id);
            return Optional.ofNullable(entity);
        } catch (Exception e) {
            System.err.println("Error finding InsurancePlan by ID: " + id + ", Error: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<InsurancePlanEntity> findByParams(Map<String, Object> searchParams) {
        if (searchParams == null || searchParams.isEmpty()) {
            return Collections.emptyList();
        }

        StringBuilder sql = new StringBuilder("SELECT * FROM insurance_plan WHERE 1=1");
        List<Object> params = new ArrayList<>();

        // Add search conditions based on parameters
        if (searchParams.containsKey("name")) {
            sql.append(" AND name LIKE ?");
            params.add("%" + searchParams.get("name") + "%");
        }

        if (searchParams.containsKey("status")) {
            sql.append(" AND active = ?");
            params.add(searchParams.get("status").equals("active"));
        }

        if (searchParams.containsKey("type")) {
            sql.append(" AND plan_type_code = ?");
            params.add(searchParams.get("type"));
        }

        if (searchParams.containsKey("ownedBy")) {
            sql.append(" AND owned_by_id = ?");
            params.add(searchParams.get("ownedBy"));
        }

        if (searchParams.containsKey("administeredBy")) {
            sql.append(" AND administered_by_id = ?");
            params.add(searchParams.get("administeredBy"));
        }

        try {
            return jdbcTemplate.query(sql.toString(), new InsurancePlanRowMapper(), params.toArray());
        } catch (Exception e) {
            System.err.println("Error searching InsurancePlans: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    private static class InsurancePlanRowMapper implements RowMapper<InsurancePlanEntity> {
        @Override
        public InsurancePlanEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
            InsurancePlanEntity entity = new InsurancePlanEntity();
            entity.setId(rs.getLong("id"));
            entity.setActive(rs.getBoolean("active"));
            entity.setName(rs.getString("name"));
            
            // Map other fields
            entity.setPlanTypeSystem(rs.getString("plan_type_system"));
            entity.setPlanTypeCode(rs.getString("plan_type_code"));
            entity.setPlanTypeDisplay(rs.getString("plan_type_display"));
            
            // Handle Date fields
            Timestamp periodStart = rs.getTimestamp("period_start");
            if (periodStart != null) {
                entity.setPeriodStart(new java.util.Date(periodStart.getTime()));
            }
            
            Timestamp periodEnd = rs.getTimestamp("period_end");
            if (periodEnd != null) {
                entity.setPeriodEnd(new java.util.Date(periodEnd.getTime()));
            }
            
            entity.setOwnedById(rs.getLong("owned_by_id"));
            entity.setAdministeredById(rs.getLong("administered_by_id"));
            
            entity.setPlanDetailIdentifierValue(rs.getString("plan_detail_identifier_value"));
            entity.setPlanNetworkId(rs.getString("plan_network_id"));
            
            entity.setCoverageBenefitTypeSystem(rs.getString("coverage_benefit_type_system"));
            entity.setCoverageBenefitTypeCode(rs.getString("coverage_benefit_type_code"));
            entity.setCoverageBenefitRequirement(rs.getString("coverage_benefit_requirement"));
            
            return entity;
        }
    }
}