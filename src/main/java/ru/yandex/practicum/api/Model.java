package ru.yandex.practicum.api;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class Model {
    private RegisterUserApi registerUserApi;
    private AuthUserApi authUserApi;

    public Model(RegisterUserApi registerUserApi) {
        this.registerUserApi = registerUserApi;
    }

    public Model(AuthUserApi authUserApi) {
        this.authUserApi = authUserApi;
    }

                        /*** Эндпоинты ***/
    private static final String REGISTER_USER = "auth/register";
    private static final String AUTH_USER ="auth/login";
    private static final String EDIT_USER = "auth/user";

                        /*** Шаги ***/
    @Step("Отправить запрос на регистрацию пользователя")
    public void sendPostRegister() {
        given()
                .header("Content-type", "application/json")
                .body(registerUserApi)
                .when()
                .post(REGISTER_USER);
    }
    @Step("Отправить запрос на авторизацию пользователя")
    public Response sendPostAuth() {
        return  given()
                .header("Content-type", "application/json")
                .body(authUserApi)
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
