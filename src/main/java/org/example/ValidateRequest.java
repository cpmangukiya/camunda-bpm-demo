package org.example;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;


public class ValidateRequest implements JavaDelegate {

    private final Logger LOGGER = Logger.getLogger(ValidateRequest.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        LOGGER.info("Validate request");

        String custId = (String) execution.getVariable("custId");
        String msisdn = (String) execution.getVariable("msisdn");
        String protype = (String) execution.getVariable("protype");

        if(custId == null || msisdn == null || protype == null){
            LOGGER.info(custId+ " "+ msisdn + " " + protype);
            LOGGER.info("Missing details. Validation Failed.");
            execution.setVariable("valid", false);
        } else {
            LOGGER.info("Validation successful");
            execution.setVariable("valid", true);
        }

    }

}
