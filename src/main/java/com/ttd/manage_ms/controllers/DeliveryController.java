package com.ttd.manage_ms.controllers;

import com.ttd.manage_ms.exceptions.DeliveryNotFoundException;
import com.ttd.manage_ms.models.Delivery;
import com.ttd.manage_ms.repositories.DeliveryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    @GetMapping("/pedidos/details/{id}")
    Object getId(@PathVariable String id) {

        Optional<Delivery> deliveryDetails = deliveryRepository.findById(id);

        if (deliveryDetails == null || deliveryDetails.isEmpty())
            throw new DeliveryNotFoundException("No se encontraron pedidos relacionados con el ID: " + id);

        return deliveryDetails;

    }

    //TODO Eliminar domicilio (Buscar por id)
    @DeleteMapping("/pedidos/delete/{id}")
    String delete(@PathVariable("id")String id) {
        if(deliveryRepository.existsById(id)){
            deliveryRepository.deleteById(id);
            return "El domicilio fue eliminado satisfactoriamente";
        }else throw new DeliveryNotFoundException("No se encontró un pedido con el id: "+id);
    }

    //TODO Editar domicilio (Buscar por id) FindByIdAndUpdate
    @PutMapping("pedidos/edit/{id}")
    Delivery editDelivery(@RequestBody Delivery updateDataDelivery, @PathVariable String id){
        if (!deliveryRepository.existsById(id))
            throw new DeliveryNotFoundException("No se encontró un pedido con el id: "+id);

        Delivery delivery = deliveryRepository.findById(id).orElse(null);

        delivery.setUsernameEmisor(updateDataDelivery.getUsernameEmisor());
        delivery.setUsernameReceptor(updateDataDelivery.getUsernameReceptor());
        delivery.setCiudadOrigen(updateDataDelivery.getCiudadOrigen());
        delivery.setCiudadDestino(updateDataDelivery.getCiudadDestino());
        delivery.setDireccionOrigen(updateDataDelivery.getDireccionOrigen());
        delivery.setDireccionDestino(updateDataDelivery.getDireccionDestino());
        delivery.setValue(updateDataDelivery.getValue());
        delivery.setDescription(updateDataDelivery.getDescription());
        delivery.setEstado(updateDataDelivery.getEstado());
        delivery.setPickUpDate(updateDataDelivery.getPickUpDate());
        delivery.setDeliverDate(updateDataDelivery.getDeliverDate());
        delivery.setPqr(updateDataDelivery.getPqr());

        return deliveryRepository.save(delivery);
    }


}
