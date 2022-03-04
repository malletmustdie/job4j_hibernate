package ru.job4j.hibernate.cars.repository.impl;

import ru.job4j.hibernate.cars.model.Brand;
import ru.job4j.hibernate.cars.repository.AbstractRepository;
import ru.job4j.hibernate.cars.repository.BrandRepository;

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
    public Brand add(Brand brand) {
        this.tx(session -> session.save(brand));
        return brand;
    }

    @Override
    public void delete(Long id) {
        this.tx(session ->
                        session.createQuery("delete from Brand b where b.id = :id")
                               .setParameter("id", id)
                               .executeUpdate());
    }

    @Override
    public Brand findById(Long id) {
        return (Brand) this.tx(session ->
                                       session.createQuery("from Brand b where b.id = :id")
                                              .setParameter("id", id)
                                              .uniqueResult());
    }

}
