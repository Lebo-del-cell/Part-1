/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Student
 */
public class LoginTest {
    private Login instance;
    public LoginTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new Login("Kyle", "Smith");
        instance.registerUser (
        "Kyl_1", "Ch&&sec@ke99!", "+27838968976");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of checkUserName method, of class Login.
     */
    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        String username = "Kyl_1";
        boolean expResult = true;
        boolean result = instance.checkUserName(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of CheckPasswordComplexity method, of class Login.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("CheckPasswordComplexity");
        String password = "Ch&&sec@ke99!";
        boolean expResult = true;
        boolean result = instance.CheckPasswordComplexity(password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    /** Test of checkPasswordComplexity method with incorrect password.*/
    @Test public void testCheckPasswordComplexityIncorrect() {
        System.out.println("checkPasswordComplexityIncorrect");
        
        String password = "password";
                boolean expResult = false;
                boolean result = instance.CheckPasswordComplexity(password);
                assertEquals(expResult, result);
    }
    /**
     * Test of checkCellPhoneNumber method, of class Login.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        String cellPhoneNumber = "+27838968976";
        boolean expResult = true;
        boolean result = instance.checkCellPhoneNumber(cellPhoneNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
     /** Test of checkCellPhoneNumber method with incorrect number.*/
    @Test public void testCheckPhoneNumberIncorrect() {
        System.out.println("checkCellPhoneNumberIncorrect");
        
        String cellPhoneNumber = "08966553";
                boolean expResult = false;
                boolean result = instance.checkCellPhoneNumber(cellPhoneNumber);
                assertEquals(expResult, result);
    }

    /**
     * Test of registerUser method, of class Login.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String username = "Kyl_1";
        String password = "Ch&&sec@ke99!";
        String cellPhoneNumber = "+27838968976";
        String expResult = "User successfully registered";
        String result = instance.registerUser(username, password, cellPhoneNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loginUser method, of class Login.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        String username = "Kyl_1";
        String password = "Ch&&sec@ke99!";
        boolean expResult = true;
        boolean result = instance.loginUser(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of loginUser method with incorrect details.
     */
    /** Test of checkCellPhoneNumber method with incorrect number.*/
    @Test public void testLoginUserIncorrect() {
        System.out.println("checkCellPhoneNumberIncorrect");
        
        String username = "Wrong";
                String password = "Wrong123!";
                boolean expResult = false;
                boolean result = instance.loginUser(username, password);
                assertEquals(expResult, result);
    }
    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
        String username = "Kyl_1";
        String password = "Ch&&sec@ke99!";
        String expResult = "Welcome Kyle Smith, it is great to see you.";
        String result = instance.returnLoginStatus(username, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    /** Test of returnLoginStatus method with incorrect details.*/
    @Test public void testReturnLoginStatusIncorrect() {
        System.out.println("returnLoginStatusIncorrect");
        
        String username = "Wrong";
                String password = "Wrong123!";
                String expResult = "Username or password incorrect, please try again.";
                
                String result = instance.returnLoginStatus(username, password );
                 
                assertEquals(expResult, result);

        
    }
    
}
