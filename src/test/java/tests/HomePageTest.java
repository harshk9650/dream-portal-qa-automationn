package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import java.util.Set;

public class HomePageTest extends BaseTest {

    @Test
    public void testLoadingAnimationAndContentVisibility() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();

        // Verify loading animation appears
        Assert.assertTrue(homePage.isLoadingAnimationVisible(), "Loading animation should be visible initially");

        // Verify loading animation disappears
        homePage.waitForLoadingToDisappear();
        Assert.assertFalse(homePage.isLoadingAnimationVisible(), "Loading animation should disappear");

        // Verify main content becomes visible
        Assert.assertTrue(homePage.isMainContentVisible(), "Main content should be visible after loading");

        // Verify 'My Dreams' button becomes visible
        Assert.assertTrue(homePage.isDreamButtonVisible(), "Dream button should be visible");
    }

    @Test
    public void testClickMyDreamsOpensNewTabs() {
        HomePage homePage = new HomePage(driver);
        homePage.navigateTo();
        homePage.waitForLoadingToDisappear();
        homePage.clickMyDreams();
        
        // Let tabs open
        try { Thread.sleep(2000); } catch (Exception e) {}

        Set<String> windowHandles = driver.getWindowHandles();
        Assert.assertEquals(windowHandles.size(), 3, "Expected 3 windows/tabs in total (Original, Diary, Total)");

        boolean foundDiary = false;
        boolean foundTotal = false;

        for (String handle : windowHandles) {
            driver.switchTo().window(handle);
            String url = driver.getCurrentUrl();
            if (url.contains("dreams-diary.html")) {
                foundDiary = true;
            } else if (url.contains("dreams-total.html")) {
                foundTotal = true;
            }
        }

        Assert.assertTrue(foundDiary, "Dreams Diary page should be opened in a new tab");
        Assert.assertTrue(foundTotal, "Dreams Total page should be opened in a new tab");
    }
}
