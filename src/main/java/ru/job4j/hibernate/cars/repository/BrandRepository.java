package ru.job4j.hibernate.cars.repository;

import ru.job4j.hibernate.cars.model.Brand;

public interface BrandRepository {

    Brand add(Brand brand);

    void delete(Long id);

    Brand findById(Long id);

}
