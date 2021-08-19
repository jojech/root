package com.jojech.root;

import java.util.Objects;

public class Vagabond {
    private boolean rf;
    private boolean vb;
    private String vbName;

    public Vagabond(boolean rf, boolean vb, String vbName) {
        this.rf = rf;
        this.vb = vb;
        this.vbName = vbName;
    }
    public Vagabond(String vbName) {
        this.rf = false;
        this.vb = false;
        this.vbName = vbName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vagabond vagabond = (Vagabond) o;
        return rf == vagabond.rf && vb == vagabond.vb && Objects.equals(vbName, vagabond.vbName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rf, vb, vbName);
    }

    public boolean isRf() {
        return rf;
    }

    public void setRf(boolean rf) {
        this.rf = rf;
    }

    public boolean isVb() {
        return vb;
    }

    public void setVb(boolean vb) {
        this.vb = vb;
    }

    public String getVbName() {
        return vbName;
    }

    public void setVbName(String vbName) {
        this.vbName = vbName;
    }
}
