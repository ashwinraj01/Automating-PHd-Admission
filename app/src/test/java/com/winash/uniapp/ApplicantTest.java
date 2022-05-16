package com.winash.uniapp;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class ApplicantTest extends TestCase {
    Applicant applicant=new Applicant("Abc","Def","AbcDef@gmail.com","123456789",true,"A123");
    @Test
    public void testGetFname() {
        Assert.assertEquals("Abc",applicant.getFname());
    }
    @Test
    public void testNotGetFname() {
        Assert.assertNotEquals("abc",applicant.getFname());
    }
    @Test
    public void testIsBlack() {
        Assert.assertTrue(applicant.isBlack());
    }
    @Test
    public void testNotIsBlack() {
        Assert.assertFalse(!applicant.isBlack());
    }
    @Test
    public void testGetLname() {
        assertEquals("Def",applicant.getLname());
    }
    @Test
    public void testNotGetLname() {
        Assert.assertNotEquals("def",applicant.getLname());
    }
    @Test
    public void testGetEmail() {
        assertEquals("AbcDef@gmail.com",applicant.getEmail());
    }
    @Test
    public void testGetPhone() {
        assertEquals("123456789",applicant.getPhone());
    }
    @Test
    public void testNotGetPhone() {
        Assert.assertNotEquals("987654321",applicant.getPhone());
    }
    @Test
    public void testGetApplicantid() {
        assertEquals("A123",applicant.getApplicantid());
    }
    @Test
    public void testNotGetApplicantid() {
        Assert.assertNotEquals("A_123",applicant.getApplicantid());
    }
}