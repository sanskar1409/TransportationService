
package com.onecognizant.seed;

import com.github.javafaker.Faker;
import com.onecognizant.entity.*;
import com.onecognizant.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

@Component
public class FakeDataSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransportServicesRepository transportServicesRepository;

    @Autowired
    private TransportSubscriptionsRepository transportSubscriptionsRepository;

    @Autowired
    private SubscriptionPaymentsRepository subscriptionPaymentsRepository;

    @Override
    public void run(String... args) {
        Faker faker = new Faker();
        Random random = new Random();

        // Seed Users
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername(faker.name().username());
            user.setPassword("password123");
            user.setEmail(faker.internet().emailAddress());
            user.setRole("USER");
            userRepository.save(user);
        }

        // Seed Transport Services
        for (int i = 0; i < 5; i++) {
            TransportServices service = new TransportServices();
            service.setPickupLocation(faker.address().streetName());
            service.setOnRoutePickupPoints(faker.address().cityName() + ", " + faker.address().cityName());
            service.setStartTime(LocalTime.of(8, 0));
            service.setReturnTime(LocalTime.of(18, 0));
            service.setVehicleNo("MH12" + faker.bothify("??####"));
            service.setVehicleType("Bus");
            service.setDriverPhoneNumber(faker.phoneNumber().subscriberNumber(10));
            service.setMaximumCapacity(40);
            service.setCurrentCapacity(random.nextInt(40));
            service.setMonthlyFare(faker.number().randomDouble(2, 1000, 2000));
            transportServicesRepository.save(service);
        }

        // Seed Transport Subscriptions and Payments
        for (int i = 0; i < 10; i++) {
            TransportServices service = transportServicesRepository.findAll().get(random.nextInt(5));

            TransportSubscriptions subscription = new TransportSubscriptions();
            subscription.setSubscribedByEmployee(faker.number().numberBetween(10000, 99999));
            subscription.setSubscriptionStartDate(LocalDate.now().minusDays(random.nextInt(10)));
            subscription.setSubscriptionEndDate(LocalDate.now().plusDays(30));
            subscription.setSubscriptionStatus("Active");
            subscription.setTransportService(service);
            transportSubscriptionsRepository.save(subscription);

            SubscriptionPayments payment = new SubscriptionPayments();
            payment.setPaymentDate(LocalDate.now());
            payment.setAmount(service.getMonthlyFare());
            payment.setPaymentMode("CreditCard");
            payment.setTransportSubscriptions(subscription);
            subscriptionPaymentsRepository.save(payment);
        }

        System.out.println("Fake data seeded successfully.");
    }
}
