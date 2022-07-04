package com.jade.app.salon.api.exception;

/**
 * @Author: Josiah Adetayo
 * @Email: j.adetayo@bcs.org.uk, josiah.adetayo@cwg-plc.com
 * @Date: 6/4/22
 */
public class SalonException extends RuntimeException {

    private Object payload;

    public SalonException(String message) {
        super(message);
    }

    public SalonException(Object payload) {
        this.payload = payload;
    }

    public Object getPayload() {
        return payload;
    }
}
