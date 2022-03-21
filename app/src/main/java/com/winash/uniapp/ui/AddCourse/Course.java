package com.winash.uniapp.ui.AddCourse;

public class Course {
    public String coursename;
    public String department;
    public String duration;
    public String outcome;
    public String syllabus;

    public Course() {
        this.coursename = null;
        this.department = null;
        this.duration = null;
        this.outcome = null;
        this.syllabus = null;
        this.campus = null;
        this.q10 = null;
        this.q12 = null;
        this.ug = null;
        this.pg = null;
    }

    public String campus;
    public Integer q10;
    public Integer q12;
    public Double ug;
    public Double pg;
    Course(String campus,String coursename,
            String department,
            String duration,
            String outcome,
           Double pg,
           Integer q10,
           Integer q12,
            String syllabus,
           Double ug
            ){
        this.coursename=coursename;
        this.department=department;
        this.duration=duration;
        this.outcome=outcome;
        this.syllabus=syllabus;
        this.campus=campus;
        this.q10=q10;
        this.q12=q12;
        this.ug=ug;
        this.pg=pg;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getDepartment() {
        return department;
    }

    public String getDuration() {
        return duration;
    }

    public String getOutcome() {
        return outcome;
    }

    public String getSyllabus() {
        return syllabus;
    }

    public String getCampus() {
        return campus;
    }

    public Integer getQ10() {
        return q10;
    }

    public Integer getQ12() {
        return q12;
    }

    public Double getUg() {
        return ug;
    }

    public Double getPg() {
        return pg;
    }
}
