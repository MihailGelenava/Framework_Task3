import BaseTest.BaseTest;
import Driver.*;
import Elements.Frame;
import PageObjects.*;
import Utils.BrowserUtils;
import Utils.JsonIO;
import Utils.RandomString;
import model.User;
import model.UsersList;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class Test1 extends BaseTest {

    private static final String TEST_CONFIG_PATH = "resources\\test_config.json";

    @Test
    public void alerts() throws InterruptedException {
        logger.info("TEST ALERTS STARTED");

        HomePage homePage = new HomePage();
        Navigation navigation = new Navigation();
        AlertForm alertForm = new AlertForm();

        Driver.getDriver().get((String)JsonIO.readSimple(TEST_CONFIG_PATH,"url"));
        Assert.assertTrue(homePage.isFormOpen(),"Home page wasn't opened");

        logger.info("ASSERT 1 - Home Page opened");

        homePage.openCard("Alerts");
        navigation.isFormOpen();

        navigation.openExactMenuItem("Alerts");
        alertForm.isFormOpen();
        Assert.assertTrue(alertForm.alertsWrapperVisible(),"Alerts wrapper wasn't opened");

        logger.info("ASSERT 2 - Alerts form opened");

        alertForm.alertButton.click();
        Alert alert = BrowserUtils.switchToAlert();
        Assert.assertEquals(alert.getText(),"You clicked a button","Simple alert wasn't opened");

        logger.info("ASSERT 3 - Simple alert opened");

        alert.accept();
        Assert.assertFalse(alertForm.alertIsActive(),"Alert wasn't closed");

        logger.info("ASSERT 4 - Simple alert closed");

        alertForm.confirmButton.click();
        alert = BrowserUtils.switchToAlert();
        Assert.assertEquals(alert.getText(),"Do you confirm action?","Confirm alert wasn't opened");

        logger.info("ASSERT 5 - Confirm alert opened");

        alert.accept();
        Assert.assertFalse(alertForm.alertIsActive(),"Alert wasn't closed");
        Assert.assertEquals(alertForm.confirmText.getElement().getText(),"You selected Ok",
                "You didn't select ok in confirm alert");

        logger.info("ASSERT 6 - Confirm alert closed and 'Ok' was selected");

        BrowserUtils.scrollToWebElement(alertForm.pormtButton.getElement());
        alertForm.pormtButton.click();
        alert = BrowserUtils.switchToAlert();
        Assert.assertEquals(alert.getText(),"Please enter your name","Promt alert wasn't opened");

        logger.info("ASSERT 7 - Prompt alert opened");

        String randomString = RandomString.generateRandomString(8);

        logger.info("Random string generated: " + randomString);

        alert.sendKeys(randomString);
        alert.accept();
        Assert.assertFalse(alertForm.alertIsActive(),"Alert wasn't closed");
        Assert.assertEquals(alertForm.promtText.getElement().getText(),
                String.format("You entered %s",randomString),
                "You didn't select ok in confirm alert");

        logger.info("ASSERT 8 - Prompt alert closed and entered name match with showed");

    }

    @Test
    public void frames() throws InterruptedException {

        logger.info("TEST FRAMES STARTED");

        HomePage homePage = new HomePage();
        Navigation navigation = new Navigation();
        NestedFramesForm nestedFramesForm = new NestedFramesForm();
        FrameForm frameForm = new FrameForm();

        ArrayList<String> expected = (ArrayList<String>)JsonIO.readSimple(TEST_CONFIG_PATH,"framesList");

        Driver.getDriver().get((String)JsonIO.readSimple(TEST_CONFIG_PATH,"url"));
        Assert.assertTrue(homePage.isFormOpen(),"Home page wasn't opened");

        logger.info("ASSERT 1 - Home Page opened");

        homePage.openCard("Alerts");
        navigation.isFormOpen();

        navigation.openExactMenuItem("Nested Frames");
        Assert.assertTrue(nestedFramesForm.isFormOpen(),"Nested Frames wasn't opened");

        logger.info("ASSERT 2 - Nested Frames Page opened");

        ArrayList<String> listOfFrame = new ArrayList<>();
        nestedFramesForm.parentFrame.switchToFrame();
        listOfFrame.add(nestedFramesForm.parentFrame.searchTextInFrame());

        nestedFramesForm.childFrame.switchToFrame();
        listOfFrame.add(nestedFramesForm.childFrame.searchTextInFrame());

        assertThat(listOfFrame).containsExactlyInAnyOrderElementsOf(expected);

        logger.info("ASSERT 2 - There are messages: Parent frame, Child Iframe");

        Frame.switchToDefaultFrame();
        navigation.openExactMenuItem("Frames");

        Assert.assertTrue(frameForm.isFormOpen());

        logger.info("ASSERT 3 - Frames Page opened");

        frameForm.bigFrame.switchToFrame();
        String bigFrameText = frameForm.bigFrame.searchTextInFrame();
        Frame.switchToDefaultFrame();

        frameForm.smallFrame.switchToFrame();
        String smallFrameText = frameForm.smallFrame.searchTextInFrame();
        Frame.switchToDefaultFrame();

        assertThat(bigFrameText).as("Text in upper frame is equal with in lower frame ").isEqualTo(smallFrameText);

        logger.info("ASSERT 3 - Upper frame message equal with lower frame's");

    }

    @Test(dataProvider = "TableUsers")
    public void tables(User user) throws InterruptedException {

        logger.info("TEST TABLES STARTED");

        HomePage homePage = new HomePage();
        Navigation navigation = new Navigation();
        WebTables webTables = new WebTables();
        RegistrationForm registrationForm = new RegistrationForm();

        Driver.getDriver().get((String)JsonIO.readSimple(TEST_CONFIG_PATH,"url"));
        Assert.assertTrue(homePage.isFormOpen(),"Home page wasn't opened");

        logger.info("ASSERT 1 - Home Page opened");

        homePage.openCard("Elements");
        navigation.isFormOpen();

        navigation.openExactMenuItem("Web Tables");
        Assert.assertTrue(webTables.isFormOpen(),"Web Tables wasn't opened");

        logger.info("ASSERT 2 - Web Tables Page opened");

        webTables.addButton.click();

        Assert.assertTrue(registrationForm.isFormOpen(),"Registration form wasn't opened");

        logger.info("ASSERT 3 - Registration form appeared");


        registrationForm.fillRegistrationForm(user);
        Assert.assertTrue(registrationForm.isClosed(),"Registration form wasn't closed");

        assertThat(webTables.getTableUsers()).as("There is no such users in table").contains(user);

        logger.info("ASSERT 4 - Registration form closed and sent user appear in the User Table");

        webTables.deleteUser(user);
        Assert.assertNull(webTables.getExactUserRow(user),
                "Row with such user wasn't deleted from the table");

        logger.info("ASSERT 5 - Exact user deleted from the User Table");

    }

    @DataProvider(name="TableUsers")
    public Iterator<Object[]> dpMethod(){
        UsersList userList = new UsersList();
        Collection<Object[]> users = new ArrayList<>();

        for(User u: userList.getUsers()){
            users.add(new Object[]{u});
        }

        return users.iterator();
    }

    @Test
    public void handles() throws InterruptedException {

        logger.info("TEST HANDLES STARTED");

        HomePage homePage = new HomePage();
        Navigation navigation = new Navigation();
        BrowserWindowsForm browserWindowsForm = new BrowserWindowsForm();
        SamplePage samplePage = new SamplePage();
        LinksForm linksForm = new LinksForm();

        Driver.getDriver().get((String)JsonIO.readSimple(TEST_CONFIG_PATH,"url"));
        Assert.assertTrue(homePage.isFormOpen(),"Home page wasn't opened");

        logger.info("ASSERT 1 - Home Page opened");

        homePage.openCard("Alerts");
        navigation.isFormOpen();
        navigation.openExactMenuItem("Browser Windows");

        Assert.assertTrue(browserWindowsForm.isFormOpen(),"Alerts wrapper wasn't opened");

        logger.info("ASSERT 2 - Browser Windows form opened");

        BrowserUtils.rememberTabHandle();
        browserWindowsForm.newTabButton.click();
        BrowserUtils.switchNextWindow();

        Assert.assertTrue(samplePage.isFormOpen(),"Sample Page wasn't opened");
        Assert.assertTrue(BrowserUtils.getWindowsCount()>1,"Sample Page was opened not in new tab");

        logger.info("ASSERT 3 - Sample page opened in new tab");

        BrowserUtils.closeTab();
        BrowserUtils.switchToDefaultWindow();

        Assert.assertTrue(browserWindowsForm.isFormOpen(),"Browser Windows wrapper wasn't opened");

        logger.info("ASSERT 4 - Tab closed, returning to previous page");

        navigation.isFormOpen();
        navigation.openExactDropDownList("Elements");
        navigation.isFormOpen();
        navigation.openExactMenuItem("Links");

        Assert.assertTrue(linksForm.isFormOpen(),"Links wrapper wasn't opened");

        logger.info("ASSERT 5 - Links Page opened");

        BrowserUtils.rememberTabHandle();

        linksForm.homeLink.click();
        BrowserUtils.switchNextWindow();
        Assert.assertTrue(homePage.isFormOpen(),"Links wrapper wasn't opened");

        logger.info("ASSERT 6 - Home Page opened in new tab");

        BrowserUtils.switchToDefaultWindow();

        Assert.assertTrue(linksForm.isFormOpen(),"Links wrapper wasn't opened");

        logger.info("ASSERT 7 - Return to the Links Page");

    }
}
