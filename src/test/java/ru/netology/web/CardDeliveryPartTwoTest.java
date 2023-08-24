package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryPartTwoTest {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    public void meetingThroughWeek() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("са");
        $$(".menu-item__control").findBy(Condition.text("Санкт-Петербург")).click();
        String meetingDay = generateDate(7, "d");
        String meetingDate = generateDate(7, "dd.MM.yyyy");
        $("[data-test-id='date'] input").click();
        if (!generateDate(3, "MM").equals(generateDate(7, "MM"))) {
            $("[data-step='1']").click();
        }
        $$("td.calendar__day").findBy(Condition.text(meetingDay)).click();
        $("[data-test-id='name'] input").setValue("Алексеев Валентин");
        $("[data-test-id='phone'] input").setValue("+71231234567");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + meetingDate));
    }

    @Test
    public void meetingThrough10Days() {
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("са");
        $$(".menu-item__control").findBy(Condition.text("Санкт-Петербург")).click();
        String meetingDay = generateDate(10, "d");
        String meetingDate = generateDate(10, "dd.MM.yyyy");
        $("[data-test-id='date'] input").click();
        if (!generateDate(3, "MM").equals(generateDate(10, "MM"))) {
            $("[data-step='1']").click();
        }
        $$("td.calendar__day").findBy(Condition.text(meetingDay)).click();
        $("[data-test-id='name'] input").setValue("Алексеев Валентин");
        $("[data-test-id='phone'] input").setValue("+71231234567");
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(Condition.exactText("Встреча успешно забронирована на " + meetingDate));
    }
}
