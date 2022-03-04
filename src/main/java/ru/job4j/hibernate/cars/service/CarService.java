package ru.job4j.hibernate.cars.service;

import ru.job4j.hibernate.cars.model.Car;

public interface CarService {

    Car save(Car car);

    void delete(Long id);

    Car findById(Long id);

}
