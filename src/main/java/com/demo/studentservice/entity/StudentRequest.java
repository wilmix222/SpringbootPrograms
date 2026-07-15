package com.demo.studentservice.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class StudentRequest {

    @NotBlank
    private String studentId;

    @NotBlank
    private String studentName;

    @NotBlank
    private String grade;

    @NotBlank
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank
    private String schoolName;

    // getters & setters
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getSchoolName() { return schoolName; }
    public void setSchoolName(String schoolName) { this.schoolName = schoolName; }
}