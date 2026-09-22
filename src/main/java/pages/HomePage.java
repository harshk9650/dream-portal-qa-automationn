package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class HomePage {
    
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By loadingAnimation = By.id("loadingAnimation");
    private By mainContent = By.id("mainContent");
    private By dreamButton = By.id("dreamButton");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo() {
        driver.get("https://arjitnigam.github.io/myDreams/");
    }

    public boolean isLoadingAnimationVisible() {
        return driver.findElement(loadingAnimation).isDisplayed();
    }

    public void waitForLoadingToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingAnimation));
    }

    public boolean isMainContentVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainContent));
        return driver.findElement(mainContent).isDisplayed();
    }

    public boolean isDreamButtonVisible() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dreamButton));
        return driver.findElement(dreamButton).isDisplayed();
    }

    public void clickMyDreams() {
        wait.until(ExpectedConditions.elementToBeClickable(dreamButton)).click();
    }
}
