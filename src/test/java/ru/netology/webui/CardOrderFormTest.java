package ru.netology.webui;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardOrderFormTest {

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("span[data-test-id=name] input.input__control[name=name]").setValue("Василий");
        form.$("span[data-test-id=phone] input.input__control[name=phone]").setValue("+79270000000");
        form.$("label[data-test-id=agreement]").click();
        form.$("button").click();
        $("p[data-test-id=order-success]").shouldHave(matchText("заявка успешно отправлена"));
    }

    @Test
    void shouldShowErrorMsgIfNameIsIncorrect() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("span[data-test-id=name] input.input__control[name=name]").setValue("Dfcbkbq");
        form.$("span[data-test-id=phone] input.input__control[name=phone]").setValue("+79270000000");
        form.$("label[data-test-id=agreement]").click();
        form.$("button").click();
        $("span.input_invalid[data-test-id=name]").exists();
        $("span[data-test-id=name].input_invalid span.input__sub")
                .shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldShowErrorMsgIfPhoneIsIncorrect() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("span[data-test-id=name] input.input__control[name=name]").setValue("Василий");
        form.$("span[data-test-id=phone] input.input__control[name=phone]").setValue("79270000000");
        form.$("label[data-test-id=agreement]").click();
        form.$("button").click();
        $("span.input_invalid[data-test-id=phone]").exists();
        $("span[data-test-id=phone].input_invalid span.input__sub")
                .shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldShowErrorIfAgreementNotChecked() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("span[data-test-id=name] input.input__control[name=name]").setValue("Василий");
        form.$("span[data-test-id=phone] input.input__control[name=phone]").setValue("+79270000000");
        form.$("button").click();
        $("label.input_invalid[data-test-id=agreement]").exists();
    }

    @Test
    void shouldShowErrorMsgIfPhoneIsEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("span[data-test-id=name] input.input__control[name=name]").setValue("Василий");
        form.$("label[data-test-id=agreement]").click();
        form.$("button").click();
        $("span.input_invalid[data-test-id=phone]").exists();
        $("span[data-test-id=phone].input_invalid span.input__sub")
                .shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldShowErrorMsgIfNameIsEmpty() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        form.$("span[data-test-id=phone] input.input__control[name=phone]").setValue("+79270000000");
        form.$("label[data-test-id=agreement]").click();
        form.$("button").click();
        $("span.input_invalid[data-test-id=name]").exists();
        $("span[data-test-id=name].input_invalid span.input__sub")
                .shouldHave(text("Поле обязательно для заполнения"));
    }
}
