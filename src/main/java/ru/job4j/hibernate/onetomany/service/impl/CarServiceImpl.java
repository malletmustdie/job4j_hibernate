package ru.job4j.hibernate.onetomany.service.impl;

import ru.job4j.hibernate.onetomany.model.CarModel;
import ru.job4j.hibernate.onetomany.repository.CarRepository;
import ru.job4j.hibernate.onetomany.repository.impl.CarRepositoryImpl;
import ru.job4j.hibernate.onetomany.service.CarService;

public class CarServiceImpl implements CarService {

    private final CarRepository carRepository = CarRepositoryImpl.getCarRepository();

    @Override
    public CarModel save(CarModel car) {
        return carRepository.add(car);
    }

    @Override
    public void delete(Long id) {
        carRepository.delete(id);
    }

    @Override
    public CarModel findById(Long id) {
        return carRepository.findById(id);
    }

}
