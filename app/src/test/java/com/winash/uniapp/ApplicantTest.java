package com.winash.uniapp;

import junit.framework.TestCase;

import org.junit.Assert;

public class ApplicantTest extends TestCase {
    Applicant applicant=new Applicant("Abc","Def","AbcDef@gmail.com","123456789",true,"A123");
    public void testGetFname() {
        Assert.assertEquals("Abc",applicant.getFname());
    }
    public void testIsBlack() {
        Assert.assertTrue(applicant.isBlack());
    }

    public void testGetLname() {
        assertEquals("Def",applicant.getLname());
    }

    public void testGetEmail() {
        assertEquals("AbcDef@gmail.com",applicant.getEmail());
    }

    public void testGetPhone() {
        assertEquals("123456789",applicant.getPhone());
    }

    public void testGetApplicantid() {
        assertEquals("A123",applicant.getApplicantid());
    }
}