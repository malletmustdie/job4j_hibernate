package ru.job4j.hibernate.onetomany.repository;

import ru.job4j.hibernate.onetomany.model.Car;

public interface CarRepository {

    Car add(Car car);

    void delete(Long id);

    Car findById(Long id);

}
