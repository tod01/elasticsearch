package com.example.elasticsearch.service;

import com.example.elasticsearch.document.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class VehicleDummyDataService {

    private final VehicleService vehicleService;
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger LOG = LoggerFactory.getLogger(VehicleDummyDataService.class);

    @Autowired
    public VehicleDummyDataService(final VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public void insertDummyData() {
        vehicleService.index(buildVehicle("1", "Audi A1", "AAA-123", "2010-01-01"));
        vehicleService.index(buildVehicle("2", "Audi A3", "AAB-123", "2011-07-05"));
        vehicleService.index(buildVehicle("3", "Audi A3", "AAC-123", "2012-10-03"));

        vehicleService.index(buildVehicle("4", "BMW M3", "AAA-023", "2021-10-06"));
        vehicleService.index(buildVehicle("5", "BMW 3", "1AA-023", "2001-10-01"));
        vehicleService.index(buildVehicle("6", "BMW M5", "12A-023", "1999-05-08"));

        vehicleService.index(buildVehicle("7", "VM Golf", "42A-023", "1991-04-08"));
        vehicleService.index(buildVehicle("8", "VM Passat", "18A-023", "2021-04-08"));

        vehicleService.index(buildVehicle("9", "Skoda Kodiaq", "28A-023", "2020-01-04"));
        vehicleService.index(buildVehicle("10", "Skoda Yeti", "88A-023", "2015-03-09"));
    }

    private static Vehicle buildVehicle(final String id, final String name, final String number, final String date) {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setName(name);
        vehicle.setNumber(number);
        try {
            vehicle.setCreated(DATE_FORMAT.parse(date));
        }catch (ParseException e) {
            LOG.error(e.getMessage(), e);
        }

        return vehicle;
    }
}
