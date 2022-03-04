package ru.job4j.hibernate.onetomany.repository;

import ru.job4j.hibernate.onetomany.model.Brand;

public interface BrandRepository {

    Brand add(Brand brand);

    void delete(Long id);

    Brand findById(Long id);

}
