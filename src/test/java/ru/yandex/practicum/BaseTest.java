package ru.yandex.practicum;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import ru.yandex.practicum.api.AuthUserApi;
import ru.yandex.practicum.api.Model;
import ru.yandex.practicum.api.RegisterUserApi;

public class BaseTest {

  @Rule
  public DriverRule driverRule = new DriverRule();
  RegisterUserApi registerUserApi;
  AuthUserApi authUserApi;
  Model model;

  @Before
  public void setUp() {
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
