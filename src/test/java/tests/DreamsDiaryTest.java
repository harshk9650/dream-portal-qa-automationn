package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DreamsDiaryPage;

import java.util.List;
import java.util.Map;

public class DreamsDiaryTest extends BaseTest {

    @Test
    public void testDiaryEntries() {
        driver.get("https://arjitnigam.github.io/myDreams/dreams-diary.html");
        DreamsDiaryPage diaryPage = new DreamsDiaryPage(driver);

        // Verify exactly 10 dream entries exist
        int entriesCount = diaryPage.getDreamEntriesCount();
        Assert.assertEquals(entriesCount, 10, "Expected exactly 10 dream entries");

        List<Map<String, String>> dreamsData = diaryPage.getAllDreamsData();
        for (Map<String, String> dream : dreamsData) {
            String dreamName = dream.get("Dream Name");
            String daysAgo = dream.get("Days Ago");
            String dreamType = dream.get("Dream Type");

            // Verify required cells are populated
            Assert.assertNotNull(dreamName, "Dream name should not be null");
            Assert.assertFalse(dreamName.isEmpty(), "Dream name should not be empty");
            Assert.assertNotNull(daysAgo, "Days Ago should not be null");
            Assert.assertFalse(daysAgo.isEmpty(), "Days Ago should not be empty");

            // Verify every dream type is either 'Good' or 'Bad'
            Assert.assertTrue(dreamType.equals("Good") || dreamType.equals("Bad"),
                    "Dream type must be either 'Good' or 'Bad'. Found: " + dreamType);
        }
    }
}
