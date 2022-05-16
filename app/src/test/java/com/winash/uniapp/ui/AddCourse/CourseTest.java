package com.winash.uniapp.ui.AddCourse;

import androidx.appcompat.app.ActionBar;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CourseTest extends TestCase {
    Course course =new Course("Coimbatore","Cloud Computing","CSE","6 months","CO","7","80","80","None","45","13-05-2022");
    @Test
    public void testGetCoursename() {
        Assert.assertEquals("Cloud Computing",course.getCoursename());
    }
    @Test
    public void testNotGetCoursename() {
        Assert.assertNotEquals("cloud computing",course.getCoursename());
    }
    @Test
    public void testGetCampusname() {
        Assert.assertEquals("Coimbatore",course.getCampus());
    }
    @Test
    public void testNotGetCampusname() {
        Assert.assertNotEquals("coimbatore",course.getCampus());
    }
    @Test
    public void testGetDepartment() {
        Assert.assertEquals("CSE",course.getDepartment());
    }
    @Test
    public void testNotGetDepartment() {
        Assert.assertNotEquals("Cse",course.getDepartment());
    }
    @Test
    public void testGetDuration() {
        Assert.assertEquals("6 months",course.getDuration());
    }
    @Test
    public void testNotGetDuration() {
        Assert.assertNotEquals("6_months",course.getDuration());
    }
    @Test
    public void testGetOutcome() {
        Assert.assertEquals("CO",course.getOutcome());
    }
    @Test
    public void testNotGetOutcome() {
        Assert.assertNotEquals("C O",course.getOutcome());
    }
    @Test
    public void testGetPG() {
        Assert.assertEquals("7",course.getPg());
    }
    @Test
    public void testNotGetPG() {
        Assert.assertNotEquals("7.0",course.getPg());
    }
    @Test
    public void testGetQ10() {
        Assert.assertEquals("80",course.getQ10());
    }
    @Test
    public void testNotGetQ10() {
        Assert.assertNotEquals("81",course.getQ10());
    }
    @Test
    public void testGetQ12() {
        Assert.assertEquals("80",course.getQ12());
    }
    @Test
    public void testNotGetQ12() {
        Assert.assertNotEquals("79",course.getQ12());
    }
    @Test
    public void testGetUG() {
        Assert.assertEquals("45",course.getUg());
    }
    @Test
    public void testNotGetUG() {
        Assert.assertNotEquals("450",course.getUg());
    }
    @Test
    public void testGetSyllabus() {
        Assert.assertEquals("None",course.getSyllabus());
    }
    @Test
    public void testNotGetSyllabus() {
        Assert.assertNotEquals("Null",course.getSyllabus());
    }
    @Test
    public void testGetDeadline() {
        Assert.assertEquals("13-05-2022",course.getDeadline());
    }
    @Test
    public void testNotGetDeadline() {
        Assert.assertNotEquals("05-13-2022",course.getDeadline());
    }
}