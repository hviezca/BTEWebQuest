package com.btewebquest.model;

import java.util.Map;

/**
 * Model for returning a response with validation status and any errors.
 *
 * @version 1.0
 * @author sfradet
 */
public class ValidatedResponse {

    private boolean validated;
    private Map<String, String> errorMessages;

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
