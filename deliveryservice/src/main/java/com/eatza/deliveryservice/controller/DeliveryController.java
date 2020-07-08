package com.eatza.deliveryservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.eatza.deliveryservice.dto.DeliverItemDto;
import com.eatza.deliveryservice.exception.DeliveryException;
import com.eatza.deliveryservice.exception.ItemDeliveredException;
import com.eatza.deliveryservice.model.DeliverItem;
import com.eatza.deliveryservice.service.deliveryservice.DeliverService;

@RestController
public class DeliveryController {

	@Autowired
	DeliverService deliveryService;

	private static final Logger logger = LoggerFactory.getLogger(DeliveryController.class);

	@KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.consumer.group.id}")
	public ResponseEntity<String> logOrderToDeliver(@RequestBody DeliverItemDto deliverItemDto)
			throws DeliveryException {
		logger.debug("In deliver items method, calling the service");
		String message = this.deliveryService.logOrder(deliverItemDto);
		logger.debug("Log Successful");
		return ResponseEntity.status(HttpStatus.OK).body(message); 
	}

	@PutMapping("/deliver/close/{orderId}")
	public ResponseEntity<DeliverItem> deliverItems(@RequestHeader String authorization, @PathVariable Long orderId)
			throws DeliveryException, ItemDeliveredException {
		logger.debug("In deliver items method, calling the service");
		DeliverItem deliverItem = deliveryService.deliverItems(orderId);
		logger.debug("Item Delivered!!!");
		return ResponseEntity.status(HttpStatus.OK).body(deliverItem);
	}

}
