package com.eatza.deliveryservice.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "delivery")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class DeliverItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long orderId;
	private Long restaurantId;
	private Long customerId;
	private String status;
	@UpdateTimestamp
	private LocalDateTime updatedTime;

	public DeliverItem(Long orderId, Long restaurantId, Long customerId, String status) {
		super();
		this.orderId = orderId;
		this.restaurantId = restaurantId;
		this.customerId = customerId;
		this.status = status;
	}

	public DeliverItem(Long orderId, Long restaurantId, Long customerId) {
		super();
		this.orderId = orderId;
		this.restaurantId = restaurantId;
		this.customerId = customerId;
	}

//	public void setUpdatedTime(LocalDateTime updatedTime) {
//		this.updatedTime = updatedTime;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}

}
