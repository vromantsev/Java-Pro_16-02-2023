package ua.hillel.hw.task26.test;

import ua.hillel.hw.task26.annotations.AfterSuite;
import ua.hillel.hw.task26.annotations.BeforeSuite;
import ua.hillel.hw.task26.annotations.Order;
import ua.hillel.hw.task26.annotations.Test;

import java.util.logging.Logger;

public class TestClass {

    private static final Logger LOGGER = Logger.getLogger(TestClass.class.getName());

    @BeforeSuite
    public void setup() {
        LOGGER.info("@BeforeSuite is invoked");
    }

    @Order(priority = 2)
    @Test
    public void test1() {
        LOGGER.info("test 1 invoked");
    }

    @Order(priority = 1)
    @Test
    public void test2() {
        LOGGER.info("test 2 invoked");
    }

    @Order(priority = 2)
    @Test
    public void test3() {
        LOGGER.info("test 3 invoked");
    }

    @AfterSuite
    public void tearDown() {
        LOGGER.info("@AfterSuite is invoked");
    }
}
