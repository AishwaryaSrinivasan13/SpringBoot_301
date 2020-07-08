package com.eatza.order.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DeliveryResponseDto {

	private Long id;
	private Long orderId;
	private Long restaurantId;
	private Long customerId;
	private String status;

	public DeliveryResponseDto(Long orderId, Long restaurantId, Long customerId) {
		super();
		this.orderId = orderId;
		this.restaurantId = restaurantId;
		this.customerId = customerId;
	}

}
