package com.eatza.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.eatza.order.dto.DeliveryResponseDto;

@FeignClient(url = "http://localhost:8085", name = "delivery-service")
public interface DeliveryServiceClient {

	@PostMapping("/log")
	public ResponseEntity<String> logOrder(DeliveryResponseDto order);

}
