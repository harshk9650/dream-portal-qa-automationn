package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DreamsTotalPage {
    private WebDriver driver;

    private By tableRows = By.cssSelector("#dreamsTotal tbody tr");

    public DreamsTotalPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getStatisticValue(String statisticName) {
        List<WebElement> rows = driver.findElements(tableRows);
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() == 2) {
                if (cells.get(0).getText().trim().equals(statisticName)) {
                    return Integer.parseInt(cells.get(1).getText().trim());
                }
            }
        }
        return -1; // Not found
    }

    public int getGoodDreamCount() {
        return getStatisticValue("Good Dreams");
    }

    public int getBadDreamCount() {
        return getStatisticValue("Bad Dreams");
    }

    public int getTotalDreamCount() {
        return getStatisticValue("Total Dreams");
    }
    
    public int getRecurringDreamCount() {
        return getStatisticValue("Recurring Dreams");
    }
}
