package ru.job4j.hibernate.onetomany.repository.impl;

import ru.job4j.hibernate.onetomany.model.CarModel;
import ru.job4j.hibernate.onetomany.repository.AbstractRepository;
import ru.job4j.hibernate.onetomany.repository.CarRepository;

public class CarRepositoryImpl extends AbstractRepository implements CarRepository {

    private CarRepositoryImpl() {

    }

    private static class Holder {
        public static final CarRepositoryImpl HOLDER_INSTANCE = new CarRepositoryImpl();
    }

    public static CarRepositoryImpl getCarRepository() {
        return Holder.HOLDER_INSTANCE;
    }

    @Override
    public CarModel add(CarModel car) {
        this.tx(session -> session.save(car));
        return null;
    }

    @Override
    public void delete(Long id) {
        this.tx(session ->
                        session.createQuery("delete from CarModel c where c.id = :id")
                               .setParameter("id", id)
                               .executeUpdate());
    }

    @Override
    public CarModel findById(Long id) {
        return (CarModel) this.tx(session ->
                                     session.createQuery("from CarModel c where c.id = :id")
                                            .setParameter("id", id)
                                            .uniqueResult());
    }

}
