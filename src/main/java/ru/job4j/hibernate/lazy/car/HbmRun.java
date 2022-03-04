package ru.job4j.hibernate.lazy.car;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {

    public static void main(String[] args) {
        List<Brand> list = new ArrayList<>();
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Brand vw = Brand.of("VW");
            Car polo = Car.of("Polo", vw);
            Car passat = Car.of("Passat", vw);
            Car tiguan = Car.of("Tiguan", vw);
            vw.getModels().add(polo);
            vw.getModels().add(passat);
            vw.getModels().add(tiguan);

            session.save(vw);
            session.save(polo);
            session.save(passat);
            session.save(tiguan);

            Brand vaz = Brand.of("LADA");
            Car granta = Car.of("Granta", vaz);
            Car vesta = Car.of("Vesta", vaz);
            Car niva = Car.of("Niva", vaz);
            vaz.getModels().add(granta);
            vaz.getModels().add(vesta);
            vaz.getModels().add(niva);

            session.save(vaz);
            session.save(granta);
            session.save(vesta);
            session.save(niva);

            list = session.createQuery(
                    "select distinct b from Brand b join fetch b.models"
            ).list();

            session.getTransaction().commit();
            session.close();
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        var lada = list.get(0);
        lada.getModels().forEach(System.out::println);

        var vw = list.get(1);
        vw.getModels().forEach(System.out::println);
    }

}
