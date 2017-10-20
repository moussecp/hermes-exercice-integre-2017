package com.hermes_ecs.java_exercise.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BuyerTest {

    public static final String FIRST_NAME = "Jean";
    public static final String LAST_NAME = "Dupond";
    public static final String BIRTH_LOCATION = "Paris";

    private Buyer firstBuyer;
    private Buyer secondBuyer;

    @Before
    public void setup() {
        firstBuyer = new Buyer(FIRST_NAME, LAST_NAME);
        secondBuyer = new Buyer(FIRST_NAME + "delta", LAST_NAME + "delta");
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullFirstNameThrowsAnException() {
        new Buyer(null, LAST_NAME, BIRTH_LOCATION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithEmptyFirstNameThrowsAnException() {
        new Buyer("", LAST_NAME, BIRTH_LOCATION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullLastNameThrowsAnException() {
        new Buyer(FIRST_NAME, null, BIRTH_LOCATION);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithEmptyLastNameThrowsAnException() {
        new Buyer(FIRST_NAME, "", BIRTH_LOCATION);
    }

    @Test
    public void constructorWithNullBirthLocationDoesNotThrowAnyException() {
        new Buyer(FIRST_NAME, LAST_NAME, null);
    }

    @Test
    public void constructorWithEmptyBirthLocationDoesNotThrowAnyException() {
        new Buyer(FIRST_NAME, LAST_NAME, "");
    }

    @Test
    public void equalsWithSameObjectWithoutIdReturnsTrue() {
        assertThat(firstBuyer.equals(firstBuyer), is(true));
    }

    @Test
    public void equalsWithDifferentObjectsWithoutIdWithSameContentReturnsTrue() {
        secondBuyer.setFirstName(firstBuyer.getFirstName());
        secondBuyer.setLastName(firstBuyer.getLastName());
        secondBuyer.setBirthLocation(firstBuyer.getBirthLocation());

        assertThat(firstBuyer.equals(secondBuyer), is(true));
    }

    @Test
    public void equalsWithDifferentObjectsWithDifferentContentButSameIdReturnsTrue() {
        firstBuyer.setId(1L);
        secondBuyer.setId(1L);

        assertThat(firstBuyer.equals(secondBuyer), is(true));
    }

}
