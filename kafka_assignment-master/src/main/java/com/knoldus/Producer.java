package com.knoldus;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

public class Producer {
    public static void main(String[] args) {
        // For example 192.168.1.1:9092,192.168.1.2:9092
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.knoldus.UserSerializer");

        KafkaProducer<String, UserModel> kafkaProducer = new KafkaProducer<>(properties);
        try {
            Random random = new Random();
            for (int i = 1; i < 3; i++) {
                System.out.println(i);
                UserModel userModel = new UserModel(i, "Dheeraj Mishra", random.nextInt(5) + 20, "B.Tech.");
                kafkaProducer.send(new ProducerRecord<String, UserModel>("User", String.valueOf(i), userModel));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            kafkaProducer.close();
        }
    }
}