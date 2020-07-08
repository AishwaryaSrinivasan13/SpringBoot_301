package com.eatza.deliveryservice.service.deliveryservice;

import com.eatza.deliveryservice.dto.DeliverItemDto;
import com.eatza.deliveryservice.exception.DeliveryException;
import com.eatza.deliveryservice.exception.ItemDeliveredException;
import com.eatza.deliveryservice.model.DeliverItem;

public interface DeliverService {

	String logOrder(DeliverItemDto deliverItem) throws DeliveryException;

	DeliverItem deliverItems(Long orderId) throws DeliveryException, ItemDeliveredException;

}
