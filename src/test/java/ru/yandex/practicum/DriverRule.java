package ru.yandex.practicum;

import java.util.concurrent.TimeUnit;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverRule extends ExternalResource {

  private WebDriver driver;

  @Override
  protected void before() {
      if ("yandex".equals(System.getProperty("browser"))) {
          setupYandex();
      } else {
          setupChrome();
      }
    driver.manage().timeouts()
        .pageLoadTimeout(ru.yandex.practicum.EnvConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
  }

  public void setupChrome() {
    driver = new ChromeDriver();
  }

  public void setupYandex() {
    System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
    driver = new ChromeDriver();
  }

  @Override
  protected void after() {
    driver.quit();
  }

  public WebDriver getDriver() {
    return driver;
  }
}
