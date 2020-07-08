package com.eatza.deliveryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eatza.deliveryservice.model.DeliverItem;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliverItem, Long> {

	@Query(value = "SELECT d FROM DeliverItem d WHERE d.orderId = :id")
	public DeliverItem getDeliverableById(@Param("id") Long orderId);

}
