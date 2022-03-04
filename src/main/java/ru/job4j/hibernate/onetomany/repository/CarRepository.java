package ru.job4j.hibernate.onetomany.repository;

import ru.job4j.hibernate.onetomany.model.CarModel;

public interface CarRepository {

    CarModel add(CarModel car);

    void delete(Long id);

    CarModel findById(Long id);

}
