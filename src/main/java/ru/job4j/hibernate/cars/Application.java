package ru.job4j.hibernate.cars;

import java.util.List;

import ru.job4j.hibernate.cars.model.Brand;
import ru.job4j.hibernate.cars.model.Car;
import ru.job4j.hibernate.cars.service.BrandService;
import ru.job4j.hibernate.cars.service.impl.BrandServiceImpl;

public class Application {

    public static void main(String[] args) {
        BrandService brandService = new BrandServiceImpl();
        Brand vw = new Brand("Volkswagen");
        List<Car> vwModels = List.of(
                new Car("Polo"),
                new Car("Jetta"),
                new Car("Passat"),
                new Car("Taos"),
                new Car("Tiguan"),
                new Car("Touareg")
        );
        vw.setModels(vwModels);
        Brand lada = new Brand("Lada");
        List<Car> ladaModels = List.of(
                new Car("Priora"),
                new Car("Granta"),
                new Car("Vesta"),
                new Car("X-ray"),
                new Car("Niva"),
                new Car("Kalina")
        );
        lada.setModels(ladaModels);
        brandService.save(vw);
        brandService.save(lada);
    }

}
