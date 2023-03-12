import BaseTest.BaseTest;
import Driver.*;
import Elements.Frame;
import PageObjects.*;
import Utils.AlertUtil;
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

        Assert.assertTrue(homePage.isFormOpen(),"Home page wasn't opened");

        logger.info("ASSERT 1 - Home Page opened");

        homePage.openAlertsFrameWindows();
        navigation.isFormOpen();

        navigation.openAlertsFrameWindows();

        Assert.assertTrue(alertForm.isFormOpen(),"Alerts wrapper wasn't opened");

        logger.info("ASSERT 2 - Alerts form opened");

        alertForm.getAlertButton().click();
        AlertUtil.switchToAlert();
        Assert.assertEquals(AlertUtil.getAlertMessage(),"You clicked a button","Simple alert wasn't opened");

        logger.info("ASSERT 3 - Simple alert opened");

        AlertUtil.acceptAlert();
        Assert.assertFalse(alertForm.alertIsActive(),"Alert wasn't closed");

        logger.info("ASSERT 4 - Simple alert closed");

        alertForm.getConfirmButton().click();
        AlertUtil.switchToAlert();
        Assert.assertEquals(AlertUtil.getAlertMessage(),"Do you confirm action?","Confirm alert wasn't opened");

        logger.info("ASSERT 5 - Confirm alert opened");

        AlertUtil.acceptAlert();
        Assert.assertFalse(alertForm.alertIsActive(),"Alert wasn't closed");
        Assert.assertEquals(alertForm.getConfirmText().getElement().getText(),"You selected Ok",
                "You didn't select ok in confirm alert");

        logger.info("ASSERT 6 - Confirm alert closed and 'Ok' was selected");

        BrowserUtils.scrollToWebElement(alertForm.getPromtButton().getElement());
        alertForm.getPromtButton().click();
        AlertUtil.switchToAlert();
        Assert.assertEquals(AlertUtil.getAlertMessage(),"Please enter your name","Promt alert wasn't opened");

        logger.info("ASSERT 7 - Prompt alert opened");

        String randomString = RandomString.generateRandomString(8);

        logger.info("Random string generated: " + randomString);

        AlertUtil.fillAlertInput(randomString);
        AlertUtil.acceptAlert();
        Assert.assertFalse(alertForm.alertIsActive(),"Alert wasn't closed");
        Assert.assertEquals(alertForm.getPromtText().getElement().getText(),
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

        Assert.assertTrue(homePage.isFormOpen(),"Home page wasn't opened");

        logger.info("ASSERT 1 - Home Page opened");

        homePage.openAlertsFrameWindows();
        navigation.isFormOpen();

        navigation.openNestedFrames();
        Assert.assertTrue(nestedFramesForm.isFormOpen(),"Nested Frames wasn't opened");

        logger.info("ASSERT 2 - Nested Frames Page opened");

        ArrayList<String> listOfFrame = new ArrayList<>();
        nestedFramesForm.getParentFrame().switchToFrame();
        listOfFrame.add(nestedFramesForm.getParentFrame().searchTextInFrame());

        nestedFramesForm.getChildFrame().switchToFrame();
        listOfFrame.add(nestedFramesForm.getChildFrame().searchTextInFrame());

        assertThat(listOfFrame).containsExactlyInAnyOrderElementsOf(expected);

        logger.info("ASSERT 2 - There are messages: Parent frame, Child Iframe");

        Frame.switchToDefaultFrame();
        navigation.openFrames();

        Assert.assertTrue(frameForm.isFormOpen());

        logger.info("ASSERT 3 - Frames Page opened");

        frameForm.getBigFrame().switchToFrame();
        String bigFrameText = frameForm.getBigFrame().searchTextInFrame();
        Frame.switchToDefaultFrame();

        frameForm.getSmallFrame().switchToFrame();
        String smallFrameText = frameForm.getSmallFrame().searchTextInFrame();
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

        Assert.assertTrue(homePage.isFormOpen(),"Home page wasn't opened");

        logger.info("ASSERT 1 - Home Page opened");

        homePage.openElements();
        navigation.isFormOpen();

        navigation.openWebTables();
        Assert.assertTrue(webTables.isFormOpen(),"Web Tables wasn't opened");

        logger.info("ASSERT 2 - Web Tables Page opened");

        webTables.getAddButton().click();

        Assert.assertTrue(registrationForm.isFormOpen(),"Registration form wasn't opened");

        logger.info("ASSERT 3 - Registration form appeared");


        registrationForm.fillRegistrationForm(user);
        registrationForm.submitRegistration();

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

        Assert.assertTrue(homePage.isFormOpen(),"Home page wasn't opened");

        logger.info("ASSERT 1 - Home Page opened");

        homePage.openAlertsFrameWindows();
        navigation.isFormOpen();
        navigation.openBrowserWindows();

        Assert.assertTrue(browserWindowsForm.isFormOpen(),"Alerts wrapper wasn't opened");

        logger.info("ASSERT 2 - Browser Windows form opened");

        browserWindowsForm.getNewTabButton().click();
        BrowserUtils.switchNextWindow();

        Assert.assertTrue(samplePage.isFormOpen(),"Sample Page wasn't opened");
        Assert.assertTrue(BrowserUtils.getWindowsCount()>1,"Sample Page was opened not in new tab");

        logger.info("ASSERT 3 - Sample page opened in new tab");

        BrowserUtils.closeTab();
        BrowserUtils.switchToDefaultWindow();

        Assert.assertTrue(browserWindowsForm.isFormOpen(),"Browser Windows wrapper wasn't opened");

        logger.info("ASSERT 4 - Tab closed, returning to previous page");

        navigation.isFormOpen();
        navigation.openNavElements();
        navigation.isFormOpen();
        navigation.openLinks();

        Assert.assertTrue(linksForm.isFormOpen(),"Links wrapper wasn't opened");

        logger.info("ASSERT 5 - Links Page opened");

        linksForm.getHomeLink().click();
        BrowserUtils.switchNextWindow();
        Assert.assertTrue(homePage.isFormOpen(),"Links wrapper wasn't opened");

        logger.info("ASSERT 6 - Home Page opened in new tab");

        BrowserUtils.switchToDefaultWindow();

        Assert.assertTrue(linksForm.isFormOpen(),"Links wrapper wasn't opened");

        logger.info("ASSERT 7 - Return to the Links Page");

    }
}
