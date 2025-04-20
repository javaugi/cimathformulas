/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.spring5.mongodb.Customer;
import com.spring5.mongodb.CustomerRepository;
import java.util.Arrays;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 *
 * @author javaugi
 */
@Service
public class MyApplicationMain {
    
    @Value("${app.mongodb.enabled}")
    protected boolean mongoDbEnabled;
    
    @Autowired
    private CustomerRepository repository;
    
    public void runTests() {
        System.out.println("MyApplicationMain CommandLineRunner ...");
        /*
        this.mongoDbDemo();
        this.mongoDbDemo2();
        this.runRedisNoSQLDemo();
        this.runCassandraDemo();
        this.runNeo4jDemo();
        // */        
    }
    

    private void mongoDbDemo() {
        try {
            repository.deleteAll();

            // save a couple of customers
            repository.save(new Customer("Alice", "Smith"));
            repository.save(new Customer("Bob", "Smith"));

            // fetch all customers
            System.out.println("Customers found with findAll():");
            System.out.println("-------------------------------");
            for (Customer customer : repository.findAll()) {
                System.out.println(customer);
            }
            System.out.println();

            // fetch an individual customer
            System.out.println("Customer found with findByFirstName('Alice'):");
            System.out.println("--------------------------------");
            System.out.println(repository.findByFirstName("Alice"));

            System.out.println("Customers found with findByLastName('Smith'):");
            System.out.println("--------------------------------");
            for (Customer customer : repository.findByLastName("Smith")) {
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void mongoDbDemo2() {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("mydatabase");
            MongoCollection<Document> collection = database.getCollection("mycollection");

            Document doc = new Document("name", "John Doe")
                    .append("age", 30)
                    .append("city", "New York")
                    .append("hobbies", Arrays.asList("reading", "hiking"));

            collection.insertOne(doc);
            System.out.println("Document inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void runRedisNoSQLDemo() {
        try (Jedis jedis = new Jedis("localhost", 6379)) {
            jedis.set("name", "Jane Smith");
            String name = jedis.get("name");
            System.out.println("Name: " + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void runCassandraDemo() {
        /*
        try (CqlSession session = CqlSession.builder().build()) {
            session.execute("CREATE KEYSPACE IF NOT EXISTS mykeyspace WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1}");
            session.execute("USE mykeyspace");
            session.execute("CREATE TABLE IF NOT EXISTS users (id int PRIMARY KEY, name text, city text)");
            session.execute("INSERT INTO users (id, name, city) VALUES (1, 'Alice', 'Los Angeles')");

            ResultSet rs = session.execute("SELECT * FROM users WHERE id = 1");
            Row row = rs.one();
            if (row != null) {
                System.out.println("User: " + row.getString("name") + ", City: " + row.getString("city"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // */
    }

    private void runNeo4jDemo() {
        /*
        Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "password"));

        try (Session session = driver.session()) {
            String greeting = session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    Result result = tx.run("CREATE (a:Greeting) "
                            + "SET a.message = $message "
                            + "RETURN a.message + ', from node ' + id(a)",
                            parameters("message", "Hello, world"));
                    return result.single().get(0).asString();
                }
            });
            System.out.println(greeting);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }
        // */
    }    
}
