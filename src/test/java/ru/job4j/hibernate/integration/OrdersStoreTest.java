package ru.job4j.hibernate.integration;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrdersStoreTest {

    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:tests;sql.syntax_pgs=true");
        pool.setUsername("sa");
        pool.setPassword("");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./db/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @After
    public void cleanUp() throws SQLException {
        pool.getConnection().prepareStatement("drop table orders").executeUpdate();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name", "description"));

        List<Order> all = (List<Order>) store.findAll();

        Assert.assertThat(all.size(), Matchers.is(1));
        Assert.assertThat(all.get(0).getDescription(), Matchers.is("description"));
        Assert.assertThat(all.get(0).getId(), Matchers.is(1));
    }

    @Test
    public void whenUpdateOrder() {
        OrdersStore store = new OrdersStore(pool);
        store.save(Order.of("name", "description"));
        Order expected = Order.of("new-name", "new-description");
        Assert.assertTrue(store.update(1, expected));
        Order actual = store.findById(1);
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertThat(actual.getDescription(), Matchers.is("new-description"));
    }

    @Test
    public void whenSaveOrderAndFindOneRowById() {
        OrdersStore store = new OrdersStore(pool);
        Order expected = Order.of("name", "description");
        store.save(expected);
        Order actual = store.findById(expected.getId());
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    public void whenSaveOrderAndFindOneRowByName() {
        OrdersStore store = new OrdersStore(pool);
        Order expected = Order.of("name", "description");
        store.save(expected);
        Order actual = store.findByName(expected.getName());
        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    public void whenSaveTwoOrdersAndGetAllRows() {
        OrdersStore store = new OrdersStore(pool);
        Order order1 = Order.of("name-1", "description-1");
        Order order2 = Order.of("name-2", "description-2");
        store.save(order1);
        store.save(order2);
        List<Order> actualOrders = store.findAll();
        Assert.assertEquals(2, actualOrders.size());
        Assert.assertEquals(order1.getName(), actualOrders.get(0).getName());
        Assert.assertEquals(order2.getName(), actualOrders.get(1).getName());
        Assert.assertEquals(order1.getDescription(), actualOrders.get(0).getDescription());
        Assert.assertEquals(order2.getDescription(), actualOrders.get(1).getDescription());
    }

}