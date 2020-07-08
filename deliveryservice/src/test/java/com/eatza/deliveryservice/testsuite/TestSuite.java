package com.eatza.deliveryservice.testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.eatza.deliveryservice.controller.DeliveryControllerTest;
import com.eatza.deliveryservice.service.DeliveryServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ DeliveryControllerTest.class, DeliveryServiceTest.class })
public class TestSuite {

}
