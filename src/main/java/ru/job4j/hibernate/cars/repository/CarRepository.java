package ru.job4j.hibernate.cars.repository;

import ru.job4j.hibernate.cars.model.Car;

public interface CarRepository {

    Car add(Car car);

    void delete(Long id);

    Car findById(Long id);

}
