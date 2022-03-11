package ru.job4j.hibernate.candidates;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Candidate one = Candidate.of("Alex", 1, 90000.0);
            Candidate two = Candidate.of("Michael", 3, 190000.0);
            Candidate three = Candidate.of("Jim", 10, 999999.999);

            session.save(one);
            session.save(two);
            session.save(three);

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
                   .setParameter("fId", 7L)
                   .executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
