package ru.job4j.hibernate.onetomany.service.impl;

import ru.job4j.hibernate.onetomany.model.CarBrand;
import ru.job4j.hibernate.onetomany.repository.BrandRepository;
import ru.job4j.hibernate.onetomany.repository.impl.BrandRepositoryImpl;
import ru.job4j.hibernate.onetomany.service.BrandService;

public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository = BrandRepositoryImpl.getBrandRepository();

    @Override
    public CarBrand save(CarBrand brand) {
        return brandRepository.add(brand);
    }

    @Override
    public void delete(Long id) {
        brandRepository.delete(id);
    }

    @Override
    public CarBrand findById(Long id) {
        return brandRepository.findById(id);
    }

}
