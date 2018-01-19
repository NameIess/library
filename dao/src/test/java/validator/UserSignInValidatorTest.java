package validator;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import resources.ResourceData;

@RunWith(DataProviderRunner.class)
public class UserSignInValidatorTest {
    private Verifiable<User> underTest = new UserSignInValidator();


    @DataProvider
    public static Object[][] validMailValidPass() {
        return new Object[][]{
                {ResourceData.userWithValidEmailAndValidPass.get(0)},
                {ResourceData.userWithValidEmailAndValidPass.get(1)},
                {ResourceData.userWithValidEmailAndValidPass.get(2)},
                {ResourceData.userWithValidEmailAndValidPass.get(3)},
                {ResourceData.userWithValidEmailAndValidPass.get(4)},
                {ResourceData.userWithValidEmailAndValidPass.get(5)},
                {ResourceData.userWithValidEmailAndValidPass.get(6)},
                {ResourceData.userWithValidEmailAndValidPass.get(7)}
        };
    }

    @DataProvider
    public static Object[][] invalidMailValidPass() {
        return new Object[][]{
                {ResourceData.userWithInvalidEmailAndValidPass.get(0)},
                {ResourceData.userWithInvalidEmailAndValidPass.get(1)},
                {ResourceData.userWithInvalidEmailAndValidPass.get(2)},
                {ResourceData.userWithInvalidEmailAndValidPass.get(3)},
                {ResourceData.userWithInvalidEmailAndValidPass.get(4)},
                {ResourceData.userWithInvalidEmailAndValidPass.get(5)},
                {ResourceData.userWithInvalidEmailAndValidPass.get(6)},
                {ResourceData.userWithInvalidEmailAndValidPass.get(7)},
                {ResourceData.userWithInvalidEmailAndValidPass.get(8)}
        };
    }

    @DataProvider
    public static Object[][] invalidMailInvalidPass() {
        return new Object[][]{
                {ResourceData.userWithInvalidEmailAndInvalidPass.get(0)},
                {ResourceData.userWithInvalidEmailAndInvalidPass.get(1)},
                {ResourceData.userWithInvalidEmailAndInvalidPass.get(2)},
                {ResourceData.userWithInvalidEmailAndInvalidPass.get(3)},
                {ResourceData.userWithInvalidEmailAndInvalidPass.get(4)},
                {ResourceData.userWithInvalidEmailAndInvalidPass.get(5)},
                {ResourceData.userWithInvalidEmailAndInvalidPass.get(6)},
                {ResourceData.userWithInvalidEmailAndInvalidPass.get(7)}
        };
    }

    @DataProvider
    public static Object[][] validMailInvalidPass() {
        return new Object[][]{
                {ResourceData.userWithValidEmailAndInvalidPass.get(0)},
                {ResourceData.userWithValidEmailAndInvalidPass.get(1)},
                {ResourceData.userWithValidEmailAndInvalidPass.get(2)},
                {ResourceData.userWithValidEmailAndInvalidPass.get(3)},
                {ResourceData.userWithValidEmailAndInvalidPass.get(4)},
                {ResourceData.userWithValidEmailAndInvalidPass.get(5)},
                {ResourceData.userWithValidEmailAndInvalidPass.get(6)},
                {ResourceData.userWithValidEmailAndInvalidPass.get(7)}
        };
    }

    @Test
    @UseDataProvider("validMailValidPass")
    public void shouldReturnTrueWhenEmailAndPassValid(User validated) {
        System.out.println(validated);
        Boolean currentResult = underTest.validate(validated);
        Assert.assertTrue(currentResult);
    }

    @Test
    @UseDataProvider("invalidMailValidPass")
    public void shouldReturnFalseWhenEmailInvalidAndPassValid(User validated) {
        Boolean currentResult = underTest.validate(validated);
        Assert.assertFalse(currentResult);
    }

    @Test
    @UseDataProvider("invalidMailInvalidPass")
    public void shouldReturnFalseWhenEmailInvalidAndPassInvalid(User validated) {
        Boolean currentResult = underTest.validate(validated);
        Assert.assertFalse(currentResult);
    }

    @Test
    @UseDataProvider("validMailInvalidPass")
    public void shouldReturnFalseWhenEmailValidAndPassInvalid(User validated) {
        Boolean currentResult = underTest.validate(validated);
        Assert.assertFalse(currentResult);
    }
}
