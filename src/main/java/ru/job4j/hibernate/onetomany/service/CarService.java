package ru.job4j.hibernate.onetomany.service;

import ru.job4j.hibernate.onetomany.model.CarModel;

public interface CarService {

    CarModel save(CarModel car);

    void delete(Long id);

    CarModel findById(Long id);

}
