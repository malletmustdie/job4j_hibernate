package ru.job4j.hibernate.onetomany.repository.impl;

import ru.job4j.hibernate.onetomany.model.Car;
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
    public Car add(Car car) {
        this.tx(session -> session.save(car));
        return null;
    }

    @Override
    public void delete(Long id) {
        this.tx(session ->
                        session.createQuery("delete from Car c where c.id = :id")
                               .setParameter("id", id)
                               .executeUpdate());
    }

    @Override
    public Car findById(Long id) {
        return (Car) this.tx(session ->
                                     session.createQuery("from Car c where c.id = :id")
                                            .setParameter("id", id)
                                            .uniqueResult());
    }

}
