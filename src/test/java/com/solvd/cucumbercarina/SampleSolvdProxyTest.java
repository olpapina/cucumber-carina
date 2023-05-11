package com.solvd.cucumbercarina;

import com.browserup.bup.BrowserUpProxy;
import com.browserup.bup.proxy.CaptureType;
import com.browserup.harreader.model.Har;
import com.browserup.harreader.model.HarEntry;
import com.browserup.harreader.model.HarRequest;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.solvd.cucumbercarina.gui.site.pages.HomePage;
import com.zebrunner.carina.proxy.ProxyPool;
import com.zebrunner.carina.proxy.browserup.CarinaBrowserUpProxy;
import com.zebrunner.carina.utils.R;

import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SampleSolvdProxyTest implements IAbstractTest {

    @Test(description = "Test 'DYNAMIC' proxy mode Solvd")
    public void verifySolvdCallContainsDataTest() {
        R.CONFIG.put("browserup_proxy", "true", true);
        R.CONFIG.put("proxy_type", "DYNAMIC", true);
        R.CONFIG.put("proxy_port", "0", true);

        getDriver();
        BrowserUpProxy browserUpProxy = ProxyPool.getOriginal(CarinaBrowserUpProxy.class)
                .orElseThrow()
                .getProxy();
        browserUpProxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
        browserUpProxy.newHar();

        HomePage homePage = new HomePage(getDriver());
        homePage.open();

        Har har = browserUpProxy.getHar();
        SoftAssert softAssert = new SoftAssert();
        for (HarEntry entry : har.getLog().getEntries()) {
            HarRequest request = entry.getRequest();

            if (request.getUrl().contains(R.TESTDATA.get("requestedSite")) && request.getMethod().name().contains(R.TESTDATA.get("requestMethod"))) {
                softAssert.assertTrue(request.getUrl().contains(R.TESTDATA.get("requestedSite")), "Current session doesn't contain request to https://rest.happierleads.com");
                softAssert.assertTrue(request.getMethod().name().contains(R.TESTDATA.get("requestMethod")), "Current session doesn't contain POST request to https://rest.happierleads.com");
                JSONObject requestBody = new JSONObject(request.getPostData().getText());
                softAssert.assertNotNull(requestBody.toString(), "Request body contains empty 'data' property.");
            }
        }
        softAssert.assertAll();
    }
}
