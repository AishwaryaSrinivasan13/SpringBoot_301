package com.eatza.deliveryservice.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.eatza.deliveryservice.model.DeliverItem;
import com.eatza.deliveryservice.service.deliveryservice.DeliveryServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DeliveryController.class)
public class DeliveryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DeliveryServiceImpl deliveryService;

	@Autowired
	private ObjectMapper objectMapper;

	private static final long EXPIRATIONTIME = 900000;
	String jwt = "";
	String invalidJwt = "";

	@Before
	public void setup() {
		jwt = "Bearer " + Jwts.builder().setSubject("user").claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey")
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME)).compact();
	}

//	@Test
//	public void logOrder() throws Exception {
//		DeliverItem deliverItem = new DeliverItem(1L, 1L, 1L, "YET TO DELIVER");
//		DeliverItemDto deliverItemDto = new DeliverItemDto(1L, 1L, 1L);
//		deliverItem.setStatus("YET TO DELIVER");
//		when(deliveryService.logOrder(any(DeliverItemDto.class))).thenReturn("Order successfully logged");
//		RequestBuilder request = MockMvcRequestBuilders.post("/log").contentType(MediaType.APPLICATION_JSON)
//				.content(objectMapper.writeValueAsString(deliverItemDto));
//		mockMvc.perform(request).andExpect(status().is(200)).andReturn();
//	}

	@Test
	public void deliver() throws Exception {
		when(deliveryService.deliverItems(anyLong()))
				.thenReturn(new DeliverItem(1L, 1L, 1L, 1L, "DELIVERED", java.time.LocalDateTime.now()));
		RequestBuilder request = MockMvcRequestBuilders.put("/deliver/close/1?")
				.accept(MediaType.APPLICATION_JSON_VALUE).header(HttpHeaders.AUTHORIZATION, jwt);
		mockMvc.perform(request).andExpect(status().is(200)).andReturn();

	}

	@AfterClass
	public static void end() {
		System.out.println("\nDelivery controller test cases executed");
	}

}
