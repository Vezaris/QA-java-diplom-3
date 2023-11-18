package ru.yandex.practicum.Api;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Model {
    private final Object OBJECT;

    public Model(Object OBJECT) {
        this.OBJECT = OBJECT;
    }

    final String REGISTER_USER = "auth/register";
    final String AUTH_USER ="auth/login";
    final String EDIT_USER = "auth/user";

    @Step("Отправить запрос на регистрацию пользователя")
    public void sendPostRegister() {
        given()
                .header("Content-type", "application/json")
                .body(OBJECT)
                .when()
                .post(REGISTER_USER);
    }
    @Step("Отправить запрос на авторизацию пользователя")
    public Response sendPostAuth() {
        return  given()
                .header("Content-type", "application/json")
                .body(OBJECT)
                .when()
                .post(AUTH_USER);
    }

    @Step("Отправить запрос на удаление пользователя")
    public void sendDeleteUser(String token) {
        given()
                .header("Content-type", "application/json")
                .auth().oauth2(token)
                .delete(EDIT_USER);
    }

    @Step("Получить токен авторизации")
    public String getToken() {
        return  sendPostAuth().then().extract().path("accessToken").toString().substring(7);
    }

    @Step("Удалить пользователя")
    public void deleteUser() {
        try {
            String token = getToken();
            sendDeleteUser(token);
        } catch (NullPointerException exception) {
            System.out.println("TIPS: Удалять не нужно");
        }
    }
}
