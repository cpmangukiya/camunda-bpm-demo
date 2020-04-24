package org.example;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.logging.Logger;


public class PostpaidProcessing implements JavaDelegate {

    private final Logger LOGGER = Logger.getLogger(PostpaidProcessing.class.getName());

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        LOGGER.info("Process postpaid request");

        String custId = (String) execution.getVariable("custId");
        Boolean wait = (Boolean) execution.getVariable("wait");

        // Call to external system

        if(wait == null){
            execution.setVariable("wait", true);
        } else {
            execution.setVariable("wait", false);
        }

        if(custId.contains("N")){
            execution.setVariable("approved", false);
            execution.setVariable("wait", false);
        } else {
            execution.setVariable("approved", true);
        }

        if(custId.contains("E")){
            throw new Exception("Error thrown in Postpaid Processing");
        }

    }

}
