package ru.job4j.hibernate.onetomany;

import java.util.List;

import ru.job4j.hibernate.onetomany.model.CarBrand;
import ru.job4j.hibernate.onetomany.model.CarModel;
import ru.job4j.hibernate.onetomany.service.BrandService;
import ru.job4j.hibernate.onetomany.service.impl.BrandServiceImpl;

public class Application {

    public static void main(String[] args) {
        BrandService brandService = new BrandServiceImpl();
        CarBrand vw = new CarBrand("Volkswagen");
        List<CarModel> vwModels = List.of(
                new CarModel("Polo"),
                new CarModel("Jetta"),
                new CarModel("Passat"),
                new CarModel("Taos"),
                new CarModel("Tiguan"),
                new CarModel("Touareg")
        );
        vw.setModels(vwModels);
        CarBrand lada = new CarBrand("Lada");
        List<CarModel> ladaModels = List.of(
                new CarModel("Priora"),
                new CarModel("Granta"),
                new CarModel("Vesta"),
                new CarModel("X-ray"),
                new CarModel("Niva"),
                new CarModel("Kalina")
        );
        lada.setModels(ladaModels);
        brandService.save(vw);
        brandService.save(lada);
    }

}
