package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import pages.DreamsDiaryPage;
import utils.AIValidator;

import java.util.List;
import java.util.Map;

public class AIValidationTest extends BaseTest {

    @Test
    public void testAIOpinionMatchesApplicationType() {
        String apiKey = System.getenv("OPENAI_API_KEY");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new SkipException("Skipping AI Validation test because OPENAI_API_KEY is not set.");
        }

        driver.get("https://arjitnigam.github.io/myDreams/dreams-diary.html");
        DreamsDiaryPage diaryPage = new DreamsDiaryPage(driver);
        List<Map<String, String>> dreamsData = diaryPage.getAllDreamsData();

        // Let's test a sample of dreams to avoid hitting API limits
        Map<String, String> sampleDream = dreamsData.get(dreamsData.size() - 1); // e.g., "Monster chase" -> "Bad"
        
        String dreamName = sampleDream.get("Dream Name");
        String uiType = sampleDream.get("Dream Type");

        try {
            String aiType = AIValidator.classifyDreamType(dreamName);
            System.out.println("Dream: " + dreamName + " | UI Type: " + uiType + " | AI Type: " + aiType);
            Assert.assertEquals(aiType.toLowerCase(), uiType.toLowerCase(), 
                "AI classification did not match UI classification for dream: " + dreamName);
        } catch (Exception e) {
            Assert.fail("AI Validation failed with exception: " + e.getMessage());
        }
    }
}
