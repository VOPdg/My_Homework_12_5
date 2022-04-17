package guru.qa.test.pages.test;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import guru.qa.test.pages.components.enums.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.lang.String.format;

public class DemoqaTestsWithObjectWithFaker {
    @BeforeAll
    static void setUp() {
        //Configuration.holdBrowserOpen = true;  чтобы браузер не закрывался
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void fillformTest() {

        Faker faker = new Faker();

        String firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = faker.internet().emailAddress(),
                expectedFullName = format("%s %s", firstName, lastName),
                genderData = Gender.values()[faker.random().nextInt(Gender.values().length)].toString(),
                tel = faker.numerify("##########"),
                subject = Subjects.values()[faker.random().nextInt(Subjects.values().length)].toString(),
                hobby = Hobbies.values()[faker.random().nextInt(Hobbies.values().length)].toString(),
                txtFile = ("text.txt"),
                address = faker.address().fullAddress(),
                state = States.values()[faker.random().nextInt(States.values().length)].toString(),
                city = getCity(faker, state),
                text = ("Thanks for submitting the form");


        Date dateDB = new Date(faker.date().birthday().getTime());

        DateFormat dateFormat = new SimpleDateFormat("dd MMMM,yyyy", Locale.ENGLISH);
        String expectedDateOfBirth = dateFormat.format(dateDB);

        RegistrationFormPage registrationFormPage = new RegistrationFormPage();
        registrationFormPage.openPage();
        registrationFormPage.setFirstName(firstName);
        registrationFormPage.setSecondName(lastName);
        registrationFormPage.setEmail(email);
        registrationFormPage.setGender(genderData);
        registrationFormPage.setTelefon(tel);
        registrationFormPage.setDateOfBirth(dateDB);
        registrationFormPage.setSubject(subject);
        registrationFormPage.setFile(txtFile);
        registrationFormPage.setHobbies(hobby);
        registrationFormPage.setAddress(address);
        registrationFormPage.setState(state);
        registrationFormPage.setCity(city);
        registrationFormPage.submitForm();
        registrationFormPage.checkTitle(text);
        registrationFormPage.checkResult("Student Name", expectedFullName);
        registrationFormPage.checkResult("Student Email", email);
        registrationFormPage.checkResult("Gender", genderData);
        registrationFormPage.checkResult("Mobile", tel);
        registrationFormPage.checkResult("Date of Birth", expectedDateOfBirth);
        registrationFormPage.checkResult("Subjects", subject);
        registrationFormPage.checkResult("Hobbies", hobby);
        registrationFormPage.checkResult("Picture", txtFile);
        registrationFormPage.checkResult("Address", address);
        registrationFormPage.checkResult("State and City", state + " " + city);

    }

    private String getCity(Faker faker, String state) {
        switch (state) {
            case "NCR":
                return NCRCities.values()[faker.random().nextInt(NCRCities.values().length)].toString();
            case "Haryana":
                return HarCities.values()[faker.random().nextInt(HarCities.values().length)].toString();
            case "Rajasthan":
                return RajCities.values()[faker.random().nextInt(RajCities.values().length)].toString();
        }
        ;
        return "";
    }
}

