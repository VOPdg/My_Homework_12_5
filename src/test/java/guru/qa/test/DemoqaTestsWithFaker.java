package guru.qa.test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

public class DemoqaTestsWithFaker {
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
                // currentAddress = faker.rickAndMorty().quote();
                expectedFullName = format("%s %s", firstName, lastName);

        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        Selenide.executeJavaScript("document.getElementById('fixedban').remove()");

        Selenide.executeJavaScript("document.getElementsByTagName('footer')[0].remove()");

        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue(email);
        $("[for=gender-radio-2]").click();
        $("[id=userNumber]").setValue("1234543456");
        $("[id=dateOfBirthInput]").click();
        $("[class=react-datepicker__month-select]").selectOption("August");
        $("[class=react-datepicker__year-select]").selectOption("1984");
        $("[aria-label='Choose Friday, August 31st, 1984']").click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("[for=hobbies-checkbox-1]").click();
        $("#uploadPicture").uploadFromClasspath("text.txt");
        $("#currentAddress").setValue("Minsk, 220117");
        $("#state").scrollTo().click();
        $("#state").$(byText("NCR")).click();
        $("#city").click();
        $("#city").$(byText("Delhi")).click();
        $("#submit").scrollTo().click();
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(expectedFullName),
                text(email),
                text("Female"),
                text("1234543456"),
                text("31 August,1984"),
                text("Math"),
                text("Sports"),
                text("text.txt"),
                text("Minsk, 220117"),
                text("NCR Delhi"));
        $("#closeLargeModal").click();
    }
}

