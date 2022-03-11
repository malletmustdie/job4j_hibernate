package ru.job4j.hibernate.candidates;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {

    public static void main(String[] args) {

        List<Candidate> result = new ArrayList<>();

        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure().build();

        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate one = Candidate.of("Alex", 1, 90000.0);
            Candidate two = Candidate.of("Michael", 3, 190000.0);
            Candidate three = Candidate.of("Jim", 10, 999999.999);

            VacancyStore vacancyStoreOne = VacancyStore.of("Store-1");
            VacancyStore vacancyStoreTwo = VacancyStore.of("Store-2");

            Vacancy vacancyOne = new Vacancy("Alex Vacancy");
            Vacancy vacancyTwo = new Vacancy("Michael Vacancy");
            Vacancy vacancyThree = new Vacancy("Jim Vacancy");

            vacancyStoreOne.addVacancy(vacancyOne);
            vacancyStoreTwo.addVacancy(vacancyTwo);
            vacancyStoreTwo.addVacancy(vacancyThree);

            one.setStore(vacancyStoreOne);
            two.setStore(vacancyStoreTwo);
            three.setStore(vacancyStoreTwo);

            session.save(one);
            session.save(two);
            session.save(three);
            session.save(vacancyStoreOne);
            session.save(vacancyStoreTwo);

            List<Candidate> candidates = session.createQuery("from Candidate c")
                                                .list();
            candidates.forEach(c -> System.out.println(c.getName() + " - " + c.getSalary()));

            Candidate candidateFoundedById =
                    (Candidate) session.createQuery(
                                               "from Candidate c "
                                                       + "where c.id = :fId")
                                       .setParameter("fId", 1L)
                                       .uniqueResult();
            System.out.println(candidateFoundedById);

            List<Candidate> candidatesFoundedByName =
                    session.createQuery(
                                   "from Candidate c "
                                           + "where c.name = :name")
                           .setParameter("name", "Jim")
                           .list();
            candidatesFoundedByName.forEach(
                    c -> System.out.println(c.getName() + " - " + c.getSalary())
            );

            session.createQuery("update Candidate c "
                                        + "set c.name = :name, "
                                        + "c.experience = :experience, "
                                        + "c.salary = :salary "
                                        + "where c.id = :fId")
                   .setParameter("name", "Dwightttt")
                   .setParameter("experience", 5)
                   .setParameter("salary", 500000.00)
                   .setParameter("fId", 1L)
                   .executeUpdate();

            session.createQuery("delete from Candidate c where c.id = :fId")
                   .setParameter("fId", 3L)
                   .executeUpdate();

            result = session.createQuery("from Candidate c "
                                                 + "join fetch c.store vs "
                                                 + "join fetch vs.vacancies "
                                                 + "where c.id = :fId")
                            .setParameter("fId", 1L)
                            .list();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }

        result.forEach(System.out::println);
    }

}
