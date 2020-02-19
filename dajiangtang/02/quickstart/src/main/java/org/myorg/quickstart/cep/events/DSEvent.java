package org.myorg.quickstart.cep.events;

public class DSEvent {
    // 季度
    private String nsqx;

    public DSEvent(String nsqx, double zsl, int uuid) {
        this.nsqx = nsqx;
        this.zsl = zsl;
        this.uuid = uuid;
    }

    public String getNsqx() {
        return nsqx;
    }

    public void setNsqx(String nsqx) {
        this.nsqx = nsqx;
    }

    public double getZsl() {
        return zsl;
    }

    public void setZsl(double zsl) {
        this.zsl = zsl;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    // 金额
    private double zsl;
    // uuid
    private int uuid;
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DSEvent) {
            DSEvent dsEvent = (DSEvent) obj;
            return dsEvent.canEquals(this) && uuid == dsEvent.uuid;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return uuid;
    }

    public boolean canEquals(Object obj) {
        return obj instanceof DSEvent;
    }

}
