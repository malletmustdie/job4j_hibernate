package ru.job4j.hibernate.cars.service.impl;

import ru.job4j.hibernate.cars.model.Brand;
import ru.job4j.hibernate.cars.repository.BrandRepository;
import ru.job4j.hibernate.cars.repository.impl.BrandRepositoryImpl;
import ru.job4j.hibernate.cars.service.BrandService;

public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository = BrandRepositoryImpl.getBrandRepository();

    @Override
    public Brand save(Brand brand) {
        return brandRepository.add(brand);
    }

    @Override
    public void delete(Long id) {
        brandRepository.delete(id);
    }

    @Override
    public Brand findById(Long id) {
        return brandRepository.findById(id);
    }

}
