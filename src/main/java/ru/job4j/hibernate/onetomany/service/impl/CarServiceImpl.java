package ru.job4j.hibernate.onetomany.service.impl;

import ru.job4j.hibernate.onetomany.model.Car;
import ru.job4j.hibernate.onetomany.repository.CarRepository;
import ru.job4j.hibernate.onetomany.repository.impl.CarRepositoryImpl;
import ru.job4j.hibernate.onetomany.service.CarService;

public class CarServiceImpl implements CarService {

    private final CarRepository carRepository = CarRepositoryImpl.getCarRepository();

    @Override
    public Car save(Car car) {
        return carRepository.add(car);
    }

    @Override
    public void delete(Long id) {
        carRepository.delete(id);
    }

    @Override
    public Car findById(Long id) {
        return carRepository.findById(id);
    }

}
