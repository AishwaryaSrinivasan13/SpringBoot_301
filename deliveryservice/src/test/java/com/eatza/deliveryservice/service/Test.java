package com.eatza.deliveryservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.eatza.deliveryservice.dto.DeliverItemDto;
import com.eatza.deliveryservice.exception.DeliveryException;
import com.eatza.deliveryservice.exception.ItemDeliveredException;
import com.eatza.deliveryservice.model.DeliverItem;
import com.eatza.deliveryservice.repository.DeliveryRepository;
import com.eatza.deliveryservice.service.deliveryservice.DeliveryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class Test {

	@Mock
	DeliveryRepository deliveryRepository;

	@InjectMocks
	DeliveryServiceImpl deliveryServiceImpl;

	DeliverItem order = new DeliverItem();
	DeliverItem delivered = new DeliverItem();

	@org.junit.Test
	public void logOrder() throws DeliveryException {
		DeliverItemDto deliverItemDto = new DeliverItemDto(1L, 1L, 1L);
		order.setOrderId(1L);
		order.setRestaurantId(1L);
		order.setCustomerId(1L);
		order.setStatus("YET TO DELIVER");
		order.setUpdatedTime(java.time.LocalDateTime.now());
		System.out.println("ORDER\n" + order);
		when(deliveryRepository.save(any(DeliverItem.class))).thenReturn(order);
		assertEquals("Order successfully logged", deliveryServiceImpl.logOrder(deliverItemDto));
	}

	@org.junit.Test
	public void deliverOrder_success() throws DeliveryException, ItemDeliveredException {
		order.setOrderId(1L);
		order.setRestaurantId(1L);
		order.setCustomerId(1L);
		order.setStatus("YET TO DELIVER");
		order.setUpdatedTime(java.time.LocalDateTime.now());
		lenient().when(deliveryRepository.getDeliverableById(anyLong())).thenReturn(order);
		delivered.setOrderId(1L);
		delivered.setRestaurantId(1L);
		delivered.setCustomerId(1L);
		delivered.setStatus("DELIVERED");
		delivered.setUpdatedTime(java.time.LocalDateTime.now());

		when(deliveryRepository.save(any(DeliverItem.class))).thenReturn(delivered);
		DeliverItem returnedItem = deliveryRepository.save(delivered);
		assertEquals("DELIVERED", returnedItem.getStatus());
		assertNotNull(deliveryServiceImpl.deliverItems(anyLong()));

	}

	@org.junit.Test(expected = ItemDeliveredException.class)
	public void deliverOrder2_fail() throws DeliveryException, ItemDeliveredException {
		DeliverItem item = new DeliverItem(1L, 1L, 1L, 1L, "DELIVERED", java.time.LocalDateTime.now());
		lenient().when(deliveryRepository.getDeliverableById(anyLong())).thenReturn(item);
		DeliverItem updatedItem = new DeliverItem(1L, 1L, 1L, 1L, "DELIVERED", java.time.LocalDateTime.now());
		deliveryServiceImpl.deliverItems(anyLong());
	}

	@After
	public void reset() {
		order = new DeliverItem();
	}

	@AfterClass
	public static void close() {
		System.out.println("\nDelivery service test cases executed");
	}
}
