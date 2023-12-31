package ru.aptech.bustrack.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.aptech.bustrack.entities.Station;
import ru.aptech.bustrack.repositories.StationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StationService {
    @Autowired
    protected StationRepository stationRepository;

    public Optional<Station> getStationById(Long id) {
        return stationRepository.findById(id);
    }

    public List<Station> getStations() { return stationRepository.findAll(); }

    public void deleteStationById(Long id) {
        stationRepository.deleteById(id);
    }

    public Station saveStation(Station station) {
        return stationRepository.save(station);
    }

    public int saveAllStations(List<Station> stations) {
        List<Station> savedStations = stationRepository.saveAll(stations);
        return savedStations.size();
    }
    public List<String> findAlreadySavedStationNames(List<String> names) {
        return stationRepository.findAlreadySavedStationNames(names);
    }

}
