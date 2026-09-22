package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DreamsDiaryPage;
import pages.DreamsTotalPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrossPageValidationTest extends BaseTest {

    @Test
    public void testDiaryToSummaryValidation() {
        // 1. Read Diary Entries
        driver.get("https://arjitnigam.github.io/myDreams/dreams-diary.html");
        DreamsDiaryPage diaryPage = new DreamsDiaryPage(driver);
        
        List<Map<String, String>> dreamsData = diaryPage.getAllDreamsData();
        
        int calculatedGood = 0;
        int calculatedBad = 0;
        int calculatedTotal = dreamsData.size();
        
        Map<String, Integer> dreamCounts = new HashMap<>();

        for (Map<String, String> dream : dreamsData) {
            String type = dream.get("Dream Type");
            String name = dream.get("Dream Name");
            
            if (type.equals("Good")) {
                calculatedGood++;
            } else if (type.equals("Bad")) {
                calculatedBad++;
            }
            
            dreamCounts.put(name, dreamCounts.getOrDefault(name, 0) + 1);
        }
        
        int calculatedRecurring = 0;
        for (int count : dreamCounts.values()) {
            if (count > 1) {
                calculatedRecurring++;
            }
        }
        
        // 2. Validate against Summary Page
        driver.get("https://arjitnigam.github.io/myDreams/dreams-total.html");
        DreamsTotalPage totalPage = new DreamsTotalPage(driver);
        
        Assert.assertEquals(totalPage.getGoodDreamCount(), calculatedGood, "Good dreams count mismatch");
        Assert.assertEquals(totalPage.getBadDreamCount(), calculatedBad, "Bad dreams count mismatch");
        Assert.assertEquals(totalPage.getTotalDreamCount(), calculatedTotal, "Total dreams count mismatch");
        Assert.assertEquals(totalPage.getRecurringDreamCount(), calculatedRecurring, "Recurring dreams count mismatch");
        
        // Ensure "Flying over mountains" and "Lost in maze" are indeed the recurring ones
        Assert.assertTrue(dreamCounts.get("Flying over mountains") > 1, "'Flying over mountains' should be recurring");
        Assert.assertTrue(dreamCounts.get("Lost in maze") > 1, "'Lost in maze' should be recurring");
    }
}
