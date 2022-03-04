package ru.job4j.hibernate.cars.service;

import ru.job4j.hibernate.cars.model.Brand;

public interface BrandService {

    Brand save(Brand brand);

    void delete(Long id);

    Brand findById(Long id);

}
