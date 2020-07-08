package com.eatza.deliveryservice.service.deliveryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eatza.deliveryservice.dto.DeliverItemDto;
import com.eatza.deliveryservice.exception.DeliveryException;
import com.eatza.deliveryservice.exception.ItemDeliveredException;
import com.eatza.deliveryservice.model.DeliverItem;
import com.eatza.deliveryservice.repository.DeliveryRepository;

@Service
public class DeliveryServiceImpl implements DeliverService {

	@Autowired
	DeliveryRepository deliveryRepository;

	private static final Logger logger = LoggerFactory.getLogger(DeliveryServiceImpl.class);

	@Override
	public String logOrder(DeliverItemDto deliverItem) throws DeliveryException {
		logger.debug("In logOrder method, logging the order");
		try {
			DeliverItem deliverable = new DeliverItem(deliverItem.getOrderId(), deliverItem.getRestaurantId(),
					deliverItem.getCustomerId(), "YET TO DELIVER");
			logger.debug("Saving the order");
			DeliverItem order = deliveryRepository.save(deliverable);
			System.out.println("ORDER\n" + order);
			String message = "Order successfully logged";
			logger.debug(message);
			return message;
		} catch (Exception e) {
			throw new DeliveryException("Something went wrong while delivering the order.");
		}
	}

	@Override
	public DeliverItem deliverItems(Long orderId) throws DeliveryException, ItemDeliveredException {
		logger.debug("In deliverItems method, delivering the order");
		String message = "";
		DeliverItem item = this.deliveryRepository.getDeliverableById(orderId);
		DeliverItem deliveredItem = new DeliverItem();
		if (item == null) {
			throw new DeliveryException("Order Id not found");
		} else if (item.getStatus().equalsIgnoreCase("YET TO DELIVER")) {
			item.setStatus("DELIVERED");
			logger.debug("Updating order");
			deliveredItem = this.deliveryRepository.save(item);
			message = "Order delivered!!!";
			logger.debug(message);
		} else
			throw new ItemDeliveredException("Order already delivered");

		return deliveredItem;
	}

}
