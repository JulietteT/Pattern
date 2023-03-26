package ru.netology.delivery.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.delivery.data.DataGenerator;

import static com.codeborne.selenide.Selenide.open;

class DeliveryTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        // TODO: добавить логику теста в рамках которого будет выполнено планирование и перепланирование встречи.
        // Для заполнения полей формы можно использовать пользователя validUser и строки с датами в переменных
        // firstMeetingDate и secondMeetingDate. Можно также вызывать методы generateCity(locale),
        // generateName(locale), generatePhone(locale) для генерации и получения в тесте соответственно города,
        // имени и номера телефона без создания пользователя в методе generateUser(String locale) в датагенераторе
        $("[data-test-id=city] input").setValue(validUser.getCity());
        $("[data-test-id=date] 'input").sendKeys(Keys.chard (Keys.Shift, Keys.HOME)(Keys.Delete);
        $("[data-test-id=date] 'input").sendValue(firstMeetingDate);
        $("[data-test-id=date] 'input").setValue(validUser.getName());
        $("[data-test-id=name] 'input").setValue(validUser.getPhone());
        $("[data-test-id=agreement]").click();
        $(byText("Запланировать")).click();
        $(byText("Успешно")).shouldBe(visible, Duration.ofSecons(15));
        $"[data-test-id='succes-notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);
        $("[data-test-id=date] 'input").sendKeys(Keys.chard (Keys.Shift, Keys.HOME)(Keys.Delete);
        $("[data-test-id=date] 'input").sendValue(firstMeetingDate);
        $(byText("Запланировать")).click();
        $"[data-test-id='replan-notification'] .notification__content")
                .shouldHave(exactText("У вас уже встреча препланирована на другую дату? Перепланировать?))
                .shouldBe(visible);
        $"[data-test-id='replan-notification'] button". click();
        $"[data-test-id='succes-notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate))
                .shouldBe(visible);
    }
}

