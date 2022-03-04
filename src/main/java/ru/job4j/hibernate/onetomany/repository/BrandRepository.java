package ru.job4j.hibernate.onetomany.repository;

import ru.job4j.hibernate.onetomany.model.CarBrand;

public interface BrandRepository {

    CarBrand add(CarBrand brand);

    void delete(Long id);

    CarBrand findById(Long id);

}
