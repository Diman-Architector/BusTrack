package ru.aptech.bustrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aptech.bustrack.entities.TransportType;
import ru.aptech.bustrack.services.TransportTypeService;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TransportTypeController {

    @Autowired
    protected TransportTypeService transportTypeService;


    @GetMapping("/transportType")
    public ResponseEntity<?> getTransportTypeById(@RequestParam(name = "id") Long id) {
        // знак ? - какой угодно класс может быть использован в качестве параметризации
        // если есть id пользователя то нам вернется один user

        Optional<TransportType> transportType = transportTypeService.getTransportTypeById(id);
        if (transportType.isPresent()) { //метод isPresent спрашивает - есть ли в обертке что-нибудь??
            return ResponseEntity.ok(transportType.get());
        } else {
            return ResponseEntity.badRequest().body("Тип транспорта не найден");
        }
    }

    @GetMapping("/transportTypes")
    public ResponseEntity<?> getTransportTypes() { //если не указан id пользователя будет список пользователей
        return ResponseEntity.ok(transportTypeService.getTransportTypes());
    }

    @PostMapping("/transportType")
    public void saveTransportType(@RequestBody TransportType transportType) {
        transportTypeService.saveTransportType(transportType);
    }

    @DeleteMapping("/transportType")
    public void deleteTransportType(@RequestParam(name = "id") Long id) {
        transportTypeService.deleteTransportTypeById(id);
    }
}
