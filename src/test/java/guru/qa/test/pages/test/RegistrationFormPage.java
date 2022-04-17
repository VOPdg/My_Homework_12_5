package guru.qa.test.pages.test;

import com.codeborne.selenide.Selenide;
import guru.qa.test.pages.components.ColendarConponent;
import java.util.Date;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationFormPage {
    ColendarConponent calendarComponent = new ColendarConponent();

    public void openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        Selenide.executeJavaScript("document.getElementById('fixedban').remove()");

        Selenide.executeJavaScript("document.getElementsByTagName('footer')[0].remove()");

    }

    public void setFirstName(String value) {
        $("[id=firstName]").setValue(value);

    }

    public void setSecondName(String value) {
        $("[id=lastName]").setValue(value);

    }

    public void setEmail(String value) {
        $("[id=userEmail]").setValue(value);

    }

    public void setGender(String value) {
        $("[id=genterWrapper]").$(byText(value)).click();
        ;
    }

    public void setTelefon(String value) {
        $("[id=userNumber]").setValue(value);

    }

    public void setDateOfBirth(Date value) {
        calendarComponent.setDateOfBirth(value);

    }

    public void setSubject(String value) {
        $("#subjectsInput").setValue(value).pressEnter();

    }

    public void setHobbies(String value) {
        $("#hobbiesWrapper").$(byText(value)).click();
    }

    public void setFile(String txtFile) {
        $("#uploadPicture").uploadFromClasspath(txtFile);
    }

    public void setAddress(String value) {
        $("#currentAddress").setValue(value);

    }

    public void setState(String value) {
        $("#state").scrollTo().click();
        $("#state").$(byText((value))).click();
    }

    public void setCity(String value) {
        $("#city").click();
        $("#city").$(byText(value)).click();
    }

    public void submitForm() {
        $("#submit").scrollTo().click();

    }

    public void checkTitle(String value) {
        $("#example-modal-sizes-title-lg").shouldHave(text(value));

    }

    public void checkResult(String key, String value) {
        $(".table-responsive").$(byText(key))
                .parent().shouldHave(text(value));
    }
}


