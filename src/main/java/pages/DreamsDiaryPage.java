package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DreamsDiaryPage {
    private WebDriver driver;

    private By tableRows = By.cssSelector("#dreamsDiary tbody tr");

    public DreamsDiaryPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getDreamEntriesCount() {
        return driver.findElements(tableRows).size();
    }

    public List<Map<String, String>> getAllDreamsData() {
        List<Map<String, String>> dreamsList = new ArrayList<>();
        List<WebElement> rows = driver.findElements(tableRows);
        
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() == 3) {
                Map<String, String> dreamData = new HashMap<>();
                dreamData.put("Dream Name", cells.get(0).getText());
                dreamData.put("Days Ago", cells.get(1).getText());
                dreamData.put("Dream Type", cells.get(2).getText());
                dreamsList.add(dreamData);
            }
        }
        return dreamsList;
    }
}
