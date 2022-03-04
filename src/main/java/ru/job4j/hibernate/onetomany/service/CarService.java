package ru.job4j.hibernate.onetomany.service;

import ru.job4j.hibernate.onetomany.model.Car;

public interface CarService {

    Car save(Car car);

    void delete(Long id);

    Car findById(Long id);

}
