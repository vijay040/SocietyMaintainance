package com.mmcs.societymaintainance.model;

import java.io.Serializable;

/**
 * Created by Lenovo on 24-10-2018.
 */

public class ComplaintModel implements Serializable {
    private String date;
    private String title;
    private String c_description;
    private String complain_id;
    private String department;
    public boolean isVisible = false;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getC_description() {
        return c_description;
    }

    public void setC_description(String c_description) {
        this.c_description = c_description;
    }

    public String getComplain_id() {
        return complain_id;
    }

    public void setComplain_id(String complain_id) {
        this.complain_id = complain_id;
    }
}
