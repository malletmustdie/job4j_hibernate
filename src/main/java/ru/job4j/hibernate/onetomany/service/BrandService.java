package ru.job4j.hibernate.onetomany.service;

import ru.job4j.hibernate.onetomany.model.CarBrand;

public interface BrandService {

    CarBrand save(CarBrand brand);

    void delete(Long id);

    CarBrand findById(Long id);

}
