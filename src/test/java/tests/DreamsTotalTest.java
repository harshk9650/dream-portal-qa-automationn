package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DreamsTotalPage;

public class DreamsTotalTest extends BaseTest {

    @Test
    public void testSummaryValues() {
        driver.get("https://arjitnigam.github.io/myDreams/dreams-total.html");
        DreamsTotalPage totalPage = new DreamsTotalPage(driver);

        Assert.assertEquals(totalPage.getGoodDreamCount(), 6, "Expected Good Dreams count to be 6");
        Assert.assertEquals(totalPage.getBadDreamCount(), 4, "Expected Bad Dreams count to be 4");
        Assert.assertEquals(totalPage.getTotalDreamCount(), 10, "Expected Total Dreams count to be 10");
        Assert.assertEquals(totalPage.getRecurringDreamCount(), 2, "Expected Recurring Dreams count to be 2");
    }
}
