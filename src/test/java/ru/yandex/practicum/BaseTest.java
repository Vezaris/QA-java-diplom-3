package ru.yandex.practicum;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import ru.yandex.practicum.Api.AuthUserApi;
import ru.yandex.practicum.Api.Model;
import ru.yandex.practicum.Api.RegisterUserApi;

public class BaseTest {
    RegisterUserApi registerUserApi;
    AuthUserApi authUserApi;
    Model model;

    @Rule
    public DriverRule driverRule = new DriverRule();

    @Before
    public void setUp () {
        RestAssured.baseURI = ru.yandex.practicum.EnvConfig.URL_FOR_TEST_BURGER_API;
        registerUserApi = new RegisterUserApi("myTestName", "myTestMail333@yandex.ru", "myTestPass333");
        authUserApi = new AuthUserApi(registerUserApi.getEmail(), registerUserApi.getPassword());
        model = new Model(registerUserApi);
    }

    @After
    public void deleteUser() {
        model = new Model(authUserApi);
        model.deleteUser();
    }

}
