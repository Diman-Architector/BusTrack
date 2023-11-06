package ru.aptech.bustrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aptech.bustrack.entities.Station;
import ru.aptech.bustrack.services.StationService;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StationController {

    @Autowired
    protected StationService stationService;


    @GetMapping("/station")
    public ResponseEntity<?> getStationById(@RequestParam(name = "id") Long id) {
        // знак ? - какой угодно класс может быть использован в качестве параметризации
        // если есть id пользователя то нам вернется один user

        Optional<Station> station = stationService.getStationById(id);
        if (station.isPresent()) { //метод isPresent спрашивает - есть ли в обертке что-нибудь??
            return ResponseEntity.ok(station.get());
        } else {
            return ResponseEntity.badRequest().body("Остановка не найдена");
        }
    }

    @GetMapping("/stations")
    public ResponseEntity<?> getStations() { //если не указан id пользователя будет список пользователей
        return ResponseEntity.ok(stationService.getStations());
    }

    @PostMapping("/station")
    public void saveStation(@RequestBody Station station) {
        stationService.saveStation(station);
    }

    @DeleteMapping("/station")
    public void deleteStation(@RequestParam(name = "id") Long id) {
        stationService.deleteStationById(id);
    }
}
