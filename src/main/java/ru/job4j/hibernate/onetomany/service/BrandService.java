package ru.job4j.hibernate.onetomany.service;

import ru.job4j.hibernate.onetomany.model.Brand;

public interface BrandService {

    Brand save(Brand brand);

    void delete(Long id);

    Brand findById(Long id);

}
