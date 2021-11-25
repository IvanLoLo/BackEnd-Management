package com.ttd.manage_ms.controllers;

import com.ttd.manage_ms.exceptions.DeliveryNotFoundException;
import com.ttd.manage_ms.models.Delivery;
import com.ttd.manage_ms.repositories.DeliveryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;

    public DeliveryController(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @GetMapping("/pedidos/{username}")
    List<Delivery> getDelivers(@PathVariable String username, @RequestParam(required = false) String filtrar){

        List<Delivery> deliveryEmisor = deliveryRepository.findByUsernameEmisor(username);
        List<Delivery> deliveryReceptor = deliveryRepository.findByUsernameReceptor(username);

        if(filtrar == null) {
            if ((deliveryEmisor == null && deliveryReceptor == null) || (deliveryEmisor.isEmpty() && deliveryReceptor.isEmpty())) {
                throw new DeliveryNotFoundException("No se encontraron pedidos relacionados con el usuario: " + username);
            }

            return Stream.concat(deliveryEmisor.stream(), deliveryReceptor.stream()).collect(Collectors.toList());

        }else if(filtrar.equals("Emisor")) {
                if (deliveryEmisor == null || deliveryEmisor.isEmpty())
                    throw new DeliveryNotFoundException("No se encontraron pedidos relacionados con el usuario: " + username);

                return deliveryEmisor;
        }else if(filtrar.equals("Receptor")){
                if (deliveryReceptor == null || deliveryReceptor.isEmpty())
                    throw new DeliveryNotFoundException("No se encontraron pedidos relacionados con el usuario: " + username);

                return deliveryReceptor;
        }

        throw new DeliveryNotFoundException("El filtro para el domicilio no es válido");

    }

    @PostMapping("/pedidos")
    Delivery newDelivery(@RequestBody Delivery delivery){
        return deliveryRepository.save(delivery);
    }

    //TODO Detalles domicilio (Buscar por id)

    //TODO Eliminar domicilio (Buscar por id)


    //TODO Editar domicilio (Buscar por id) FindByIdAndUpdate
    @PutMapping("pedidos/edit/{id}")
    Delivery editDelivery(@RequestBody Map<String, Object> updateDataDelivery, @PathVariable String id){
        Delivery delivery = deliveryRepository.findById(id).orElse(null);
        delivery.setUsernameEmisor((String) updateDataDelivery.get("usernameEmisor"));
        delivery.setUsernameReceptor((String) updateDataDelivery.get("usernameReceptor"));
        delivery.setCiudadOrigen((String) updateDataDelivery.get("ciudadOrigen"));
        delivery.setCiudadDestino((String) updateDataDelivery.get("ciudadDestino"));
        delivery.setDireccionOrigen((String) updateDataDelivery.get("direccionOrigen"));
        delivery.setDireccionDestino((String) updateDataDelivery.get("direccionDestino"));
        delivery.setValue((Integer) updateDataDelivery.get("value"));
        delivery.setDescription((String) updateDataDelivery.get("description"));
        delivery.setEstado((String) updateDataDelivery.get("estado"));
        delivery.setPickUpDate((Date) updateDataDelivery.get("pickUpDate"));
        delivery.setDeliverDate((Date) updateDataDelivery.get("deliverDate"));
        delivery.setPqr((String) updateDataDelivery.get("pqr"));
        return deliveryRepository.save(delivery);
    }


}
