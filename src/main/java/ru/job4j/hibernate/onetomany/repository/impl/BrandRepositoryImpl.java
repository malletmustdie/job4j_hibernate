package ru.job4j.hibernate.onetomany.repository.impl;

import ru.job4j.hibernate.onetomany.model.CarBrand;
import ru.job4j.hibernate.onetomany.repository.AbstractRepository;
import ru.job4j.hibernate.onetomany.repository.BrandRepository;

public class BrandRepositoryImpl extends AbstractRepository implements BrandRepository {

    private BrandRepositoryImpl() {

    }

    private static class Holder {
        public static final BrandRepositoryImpl HOLDER_INSTANCE = new BrandRepositoryImpl();
    }

    public static BrandRepositoryImpl getBrandRepository() {
        return Holder.HOLDER_INSTANCE;
    }

    @Override
    public CarBrand add(CarBrand brand) {
        this.tx(session -> session.save(brand));
        return brand;
    }

    @Override
    public void delete(Long id) {
        this.tx(session ->
                        session.createQuery("delete from CarBrand b where b.id = :id")
                               .setParameter("id", id)
                               .executeUpdate());
    }

    @Override
    public CarBrand findById(Long id) {
        return (CarBrand) this.tx(session ->
                                       session.createQuery("from CarBrand b where b.id = :id")
                                              .setParameter("id", id)
                                              .uniqueResult());
    }

}
