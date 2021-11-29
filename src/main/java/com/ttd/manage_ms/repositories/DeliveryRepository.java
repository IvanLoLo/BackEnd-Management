package com.ttd.manage_ms.repositories;

import com.ttd.manage_ms.models.Delivery;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends MongoRepository<Delivery, String> {

    List<Delivery> findByUsernameEmisor (String usernameEmisor);
    List<Delivery> findByUsernameReceptor (String usernameReceptor);
    List<Delivery> findByEstado (String estado);

    Optional<Delivery> findById(String id);
}
