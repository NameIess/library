package resources;

import model.Book;
import model.Receipt;
import model.Role;
import model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResourceData {
    public static final String CALCULATE_TRANSFER_AMOUNT_METHOD_NAME = "calculateTransferAmount";
    public static final String IS_VALID_INPUT_DATA_METHOD_NAME = "isValidInputData";
    public static final String ENCRYPT_PASSWORD_METHOD_NAME = "encryptPassword";
    public static final String SAMPLE_STRING = "string";
    public static final String VALID_PATH = "../src/test/java/resources/test.properties";
    public static final String NULL_OBJECT = null;
    public static final Integer ONE_TIME = 1;
    public static final Integer TWO_TIMES = 2;
    public static final Integer ZERO_INTEGER_VALUE = 0;
    public static final Long ENTITY_ID_1 = 1L;
    public static final Long STATUS_ID = 2L;
    public static final int NEGATIVE_INTEGER_VALUE = -5;
    public static final int POSITIVE_INTEGER_VALUE = 5;
    public static final int ORDERED_BOOKS_AMOUNT = 4;
    public static final int AVAILABLE_BOOKS_AMOUNT = 10;
    public static final String TEST_FILE_KEY = "test.data";
    public static final String TEST_FILE_DATA = "success";
    public static List<User> userWithValidEmailAndValidPass = new ArrayList<>();
    public static List<User> userWithInvalidEmailAndValidPass = new ArrayList<>();
    public static List<User> userWithInvalidEmailAndInvalidPass = new ArrayList<>();
    public static List<User> userWithValidEmailAndInvalidPass = new ArrayList<>();
    public static List<String> validMd5Encoding = Arrays.asList(
            "As1As", "@g.r", "25ed73ebef0081ac91c3b030c8cc17c9",
            "asserrrrrrrrrrrrrrrrrrrrrrrrFs!gmail3com", "f@f.f", "fc7654acfd8b70c8b79ed14e1edf78e4",
            "As1As", "ad@gaweg.r", "39d17b2227d76b1c113cabf51e39e07b");
    public static Book bookInstance = new Book();
    public static User userInstance = new User();
    public static Receipt receiptInstance = new Receipt();
    public static Role roleInstance = new Role();

    static {
        User u1 = new User();
        u1.setEmail("@g.r");
        u1.setPassword("As1As");
        userWithInvalidEmailAndValidPass.add(u1);

        User u2 = new User();
        u2.setEmail("");
        u2.setPassword("asserrrrrrrrrrrrrrrrrrrrrrrrFs!gmail3com");
        userWithInvalidEmailAndValidPass.add(u2);

        User u3 = new User();
        u3.setEmail("ad@ga,r");
        u3.setPassword("As1As");
        userWithInvalidEmailAndValidPass.add(u3);

        User u4 = new User();
        u4.setEmail("aerhd@g..r");
        u4.setPassword("asserrrrrrrrrrrrrrrrrrFs!gmail3com");
        userWithInvalidEmailAndValidPass.add(u4);

        User u5 = new User();
        u5.setEmail("asdf@@fasdf.wt");
        u5.setPassword("asserrrr!@$rrrrrrrrrrrrrrFs!gmail3com");
        userWithInvalidEmailAndValidPass.add(u5);

        User u6 = new User();
        u6.setEmail("русскиеБуквы@.к");
        u6.setPassword("WASDf9");
        userWithInvalidEmailAndValidPass.add(u6);

        User u7 = new User();
        u7.setEmail("with_space_postfix@fasd.by ");
        u7.setPassword("!!!Aa5");
        userWithInvalidEmailAndValidPass.add(u7);

        User u8 = new User();
        u8.setEmail(" with_space_prefix@gasdg.com");
        u8.setPassword("!@#$%^&*()_QWZh7");
        userWithInvalidEmailAndValidPass.add(u8);

        User u9 = new User();
        u9.setEmail("with_space_inside @gasdg.com");
        u9.setPassword("!@#$%^&*()_QWZh7");
        userWithInvalidEmailAndValidPass.add(u9);
    }

    static {
        User u1 = new User();

        u1.setEmail("ad@g.r");
        u1.setPassword("As1As");
        userWithValidEmailAndValidPass.add(u1);

        User u2 = new User();
        u2.setEmail("f@f.f");
        u2.setPassword("asserrrrrrrrrrrrrrrrrrrrrrrrFs!gmail3com");
        userWithValidEmailAndValidPass.add(u2);

        User u3 = new User();
        u3.setEmail("ad@gaweg.r");
        u3.setPassword("As1As");
        userWithValidEmailAndValidPass.add(u3);

        User u4 = new User();
        u4.setEmail("aerhd@g.r");
        u4.setPassword("asserrrrrrrrrrrrrrrrrrFs!gmail3com");
        userWithValidEmailAndValidPass.add(u4);

        User u5 = new User();
        u5.setEmail("af.sdf.asdf@gas.fsd");
        u5.setPassword("asserrrr!@$rrrrrrrrrrrrrrFs!gmail3com");
        userWithValidEmailAndValidPass.add(u5);

        User u6 = new User();
        u6.setEmail("af.sdf.a242341sdf@gas.fsd");
        u6.setPassword("WASDf9");
        userWithValidEmailAndValidPass.add(u6);

        User u7 = new User();
        u7.setEmail("af.sdf.a2asdfasdfasdf13t42341sdf@gas.by");
        u7.setPassword("!!!Aa5 ");
        userWithValidEmailAndValidPass.add(u7);

        User u8 = new User();
        u8.setEmail("af.sdf.a2asdfasdfasdf13t42341sdf@gAFwqwtas.fsdasfasdf");
        u8.setPassword(" !@#$%^&*()_QWZh7");
        userWithValidEmailAndValidPass.add(u8);
    }

    static {
        User u1 = new User();
        u1.setEmail("@g.r");
        u1.setPassword("AsAs");
        userWithInvalidEmailAndInvalidPass.add(u1);

        User u2 = new User();
        u2.setEmail("");
        u2.setPassword("!gmail3com");
        userWithInvalidEmailAndInvalidPass.add(u2);

        User u3 = new User();
        u3.setEmail("ad@ga,r");
        u3.setPassword("+++");
        userWithInvalidEmailAndInvalidPass.add(u3);

        User u4 = new User();
        u4.setEmail("aerhd@g..r");
        u4.setPassword("As1+");
        userWithInvalidEmailAndInvalidPass.add(u4);

        User u5 = new User();
        u5.setEmail("asdf@@fasdf.wt");
        u5.setPassword("asserrrr!sadfaw3Qfsdf vsdv%R23fasfasdfasd");
        userWithInvalidEmailAndInvalidPass.add(u5);

        User u6 = new User();
        u6.setEmail("русскиеБуквы@.к");
        u6.setPassword("asserrrr!sadfaw3Qfsdf vsdv%R23fasfasdfasdasserrrr!sadfaw3Qfsdf vsdv%R23fasfasdfasdasserrrr!sadfaw3Qfsdf vsdv%R23fasfasdfasd");
        userWithInvalidEmailAndInvalidPass.add(u6);

        User u7 = new User();
        u7.setEmail("with_space_postfix@fasd.by ");
        u7.setPassword("");
        userWithInvalidEmailAndInvalidPass.add(u7);

        User u8 = new User();
        u8.setEmail("asserrrr!sadfaw3Qfsdf vsdv%R23fasfasdfasd)");
        u8.setPassword(" ");
        userWithInvalidEmailAndInvalidPass.add(u8);
    }

    static {
        User u1 = new User();

        u1.setEmail("ad@g.r");
        u1.setPassword("as!Q");
        userWithValidEmailAndInvalidPass.add(u1);

        User u2 = new User();
        u2.setEmail("f@f.f");
        u2.setPassword("nouppercaselettersfound1");
        userWithValidEmailAndInvalidPass.add(u2);

        User u3 = new User();
        u3.setEmail("ad@gaweg.r");
        u3.setPassword("noDigitsFound");
        userWithValidEmailAndInvalidPass.add(u3);

        User u4 = new User();
        u4.setEmail("aerhd@g.r");
        u4.setPassword("русскиеБуквыВПароле24");
        userWithValidEmailAndInvalidPass.add(u4);

        User u5 = new User();
        u5.setEmail("af.sdf.asdf@gas.fsd");
        u5.setPassword("lowercaseonly");
        userWithValidEmailAndInvalidPass.add(u5);

        User u6 = new User();
        u6.setEmail("af.sdf.a242341sdf@gas.fsd");
        u6.setPassword("UPPERCASEONLY");
        userWithValidEmailAndInvalidPass.add(u6);

        User u7 = new User();
        u7.setEmail("af.sdf.a2asdfasdfasdf13t42341sdf@gas.by");
        u7.setPassword("41_length_Symbols_Password____Password!20 ");
        userWithValidEmailAndInvalidPass.add(u7);

        User u8 = new User();
        u8.setEmail("af.sdf.a2asdfasdfasdf13t42341sdf@gAFwqwtas.fsdasfasdf");
        u8.setPassword("167_length_Symbols_Password____Password!2041_length_Symbols_Password____Password!2041_length_Symbols_Password____Password!2041_length_Symbols_Password____Password!20__");
        userWithValidEmailAndInvalidPass.add(u8);
    }

    static {
        bookInstance.setId(ENTITY_ID_1);
        userInstance.setId(ENTITY_ID_1);
        receiptInstance.setId(ENTITY_ID_1);

        roleInstance.setId(ENTITY_ID_1);
        roleInstance.setName(SAMPLE_STRING);
    }

}
