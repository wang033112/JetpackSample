

package com.jgo.architecture.data.manager;

/**
 * Create by jgo at 19/10/11
 */
public class NetState {

    private String responseCode;
    private boolean success = true;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
