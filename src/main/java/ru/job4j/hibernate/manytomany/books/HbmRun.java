package ru.job4j.hibernate.manytomany.books;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

            Author author1 = new Author("Author1");
            Book oneOfAuthor1 = new Book("oneOfAuthor1");
            Book twoOfAuthor1 = new Book("twoOfAuthor1");
            Book threeOfAuthor1 = new Book("threeOfAuthor1");
            author1.addBook(oneOfAuthor1);
            author1.addBook(twoOfAuthor1);
            author1.addBook(threeOfAuthor1);

            Author author2 = new Author("Author2");
            Book oneOfAuthor2 = new Book("oneOfAuthor2");
            Book twoOfAuthor2 = new Book("twoOfAuthor2");
            Book threeOfAuthor2 = new Book("threeOfAuthor2");
            author2.addBook(oneOfAuthor2);
            author2.addBook(twoOfAuthor2);
            author2.addBook(threeOfAuthor2);
            author2.addBook(oneOfAuthor1);

            session.save(author1);
            session.save(author2);

            Author currentAuthor = session.get(Author.class, 1L);
            session.remove(currentAuthor);

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
