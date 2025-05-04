package com.aravind.ctfhir.patient.service;

import java.util.List;
import java.util.Map;

import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Patient;

import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.param.StringParam;



public interface PatientService {

    MethodOutcome createPatient(Patient thePatient);

    List<Patient> findPatientsByName(StringParam theFamilyName);

    List<Patient> findPatientsUsingArbitraryCtriteria(Map<String, Object> searchParams);
    Patient readPatient(IdType theId);

    MethodOutcome updatePatient(IdType theId, Patient thePatient);

}

