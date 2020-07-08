package com.eatza.deliveryservice.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
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
public class DeliveryServiceTest {

	@Mock
	DeliveryRepository deliveryRepository;

	@InjectMocks
	DeliveryServiceImpl deliveryServiceImpl;

	@Test
	public void logOrder() throws DeliveryException {
		DeliverItemDto deliverItemDto = new DeliverItemDto(1L, 1L, 1L);
		DeliverItem item = new DeliverItem(1L, 1L, 1L, "YET TO DELIVER");
		DeliverItem logged = new DeliverItem(1L, 1L, 1L, 1L, "YET TO DELIVER", java.time.LocalDateTime.now());
		when(deliveryRepository.save(any(DeliverItem.class))).thenReturn(logged);
		assertEquals("Order successfully logged", deliveryServiceImpl.logOrder(deliverItemDto));
	}

	@Test
	public void deliverOrder_success() throws DeliveryException, ItemDeliveredException {
		DeliverItem item = new DeliverItem(1L, 1L, 1L, 1L, "YET TO DELIVER", java.time.LocalDateTime.now());
		lenient().when(deliveryRepository.getDeliverableById(anyLong())).thenReturn(item);
		DeliverItem updatedItem = new DeliverItem(1L, 1L, 1L, 1L, "DELIVERED", java.time.LocalDateTime.now());
		when(deliveryRepository.save(any(DeliverItem.class))).thenReturn(updatedItem);
		DeliverItem returnedItem = deliveryRepository.save(updatedItem);
		assertEquals("DELIVERED", returnedItem.getStatus());
		assertNotNull(deliveryServiceImpl.deliverItems(anyLong()));
	}

	@Test(expected = ItemDeliveredException.class)
	public void deliverOrder2_fail() throws DeliveryException, ItemDeliveredException {
		DeliverItem item = new DeliverItem(1L, 1L, 1L, 1L, "DELIVERED", java.time.LocalDateTime.now());
		lenient().when(deliveryRepository.getDeliverableById(anyLong())).thenReturn(item);
		DeliverItem updatedItem = new DeliverItem(1L, 1L, 1L, 1L, "DELIVERED", java.time.LocalDateTime.now());
		deliveryServiceImpl.deliverItems(anyLong());
	}

}
