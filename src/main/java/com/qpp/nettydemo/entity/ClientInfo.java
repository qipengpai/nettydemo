package com.qpp.nettydemo.entity;

public class ClientInfo {
    private String clientid;

    private Integer connected;

    private Long mostsignbits;

    private Long leastsignbits;

    private String lastconnecteddate;

    public String getClientid() {
        return clientid;
    }

    public void setClientid(String clientid) {
        this.clientid = clientid == null ? null : clientid.trim();
    }

    public Integer getConnected() {
        return connected;
    }

    public void setConnected(Integer connected) {
        this.connected = connected;
    }

    public Long getMostsignbits() {
        return mostsignbits;
    }

    public void setMostsignbits(Long mostsignbits) {
        this.mostsignbits = mostsignbits;
    }

    public Long getLeastsignbits() {
        return leastsignbits;
    }

    public void setLeastsignbits(Long leastsignbits) {
        this.leastsignbits = leastsignbits;
    }

    public String getLastconnecteddate() {
        return lastconnecteddate;
    }

    public void setLastconnecteddate(String lastconnecteddate) {
        this.lastconnecteddate = lastconnecteddate == null ? null : lastconnecteddate.trim();
    }
}