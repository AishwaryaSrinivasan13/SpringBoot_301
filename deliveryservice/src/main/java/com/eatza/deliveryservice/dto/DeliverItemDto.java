package com.eatza.deliveryservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
//@ToString
@NoArgsConstructor
public class DeliverItemDto {

	private Long orderId;
	private Long restaurantId;
	private Long customerId;

	public DeliverItemDto(Long orderId, Long restaurantId, Long customerId) {
		super();
		this.orderId = orderId;
		this.restaurantId = restaurantId;
		this.customerId = customerId;
	}

}
