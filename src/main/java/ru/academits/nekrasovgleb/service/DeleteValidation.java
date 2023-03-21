package ru.academits.nekrasovgleb.service;

public class DeleteValidation {
    private boolean valid;
    private String error;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}