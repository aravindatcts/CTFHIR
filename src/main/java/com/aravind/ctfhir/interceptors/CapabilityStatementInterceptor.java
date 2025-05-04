package com.aravind.ctfhir.interceptors;

import ca.uhn.fhir.interceptor.api.Hook;
import ca.uhn.fhir.interceptor.api.Interceptor;
import ca.uhn.fhir.interceptor.api.Pointcut;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.instance.model.api.IBaseConformance;
import org.hl7.fhir.r4.model.CapabilityStatement;
import org.springframework.stereotype.Component; 

 

@Interceptor
@Component
public class CapabilityStatementInterceptor {

   @Hook(Pointcut.SERVER_CAPABILITY_STATEMENT_GENERATED)
   public void customize(IBaseConformance theCapabilityStatement) {

      // Cast to the appropriate version
      CapabilityStatement capabilityStmt = (CapabilityStatement) theCapabilityStatement;

      // Customize the CapabilityStatement as desired
      // Now use the specific capabilityStmt object
      capabilityStmt
      .setPublisher("Aravind")
      .setName("CTFHIR Server")      
      .setVersion("1.0")
      .setDateElement(new DateTimeType("2023-10-01T00:00:00Z")) // Set the date to the current date
      .setDescription("FHIR Server is developed for one of our clients usage.  This FHIR Server connects with SAM Databricks tables");
 

   }

}