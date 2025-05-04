package com.aravind.ctfhir.patient.service;

import java.util.List;

import org.hl7.fhir.r4.model.IdType;

import org.hl7.fhir.r4.model.Patient;

import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.api.MethodOutcome;



public interface PatientService {

    MethodOutcome createPatient(Patient thePatient);

    List<Patient> findPatientsByName(StringParam theFamilyName);

    List<Patient> findPatientsUsingArbitraryCtriteria();

    Patient readPatient(IdType theId);

    MethodOutcome updatePatient(IdType theId, Patient thePatient);

}

