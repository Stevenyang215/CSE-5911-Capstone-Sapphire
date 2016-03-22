package sapphire.seemetrain;

import junit.framework.TestCase;

/**
 * File Created by Joseph on 3/8/16.
 */
public class TestSMTAccount extends TestCase {

    public void testIsLoggedIn() throws Exception {
        SMTAccount acc = new SMTAccount("name", "pass");
        assertFalse(acc.isLoggedIn());
    }

    public void testSetLoggedIn() throws Exception {
        SMTAccount acc = new SMTAccount("name", "pass");
        acc.setLoggedIn(true);
        assertTrue(acc.isLoggedIn());
    }

    public void testGetAccName() throws Exception {
        SMTAccount acc = new SMTAccount("name", "pass");
        assertEquals("name", acc.getAccName());
    }

    public void testSetAccName() throws Exception {
        SMTAccount acc = new SMTAccount("name", "pass");
        acc.setAccName("");
        assertEquals("", acc.getAccName());
        acc.setAccName(null);
        assertNull(acc.getAccName());
        acc.setAccName("Something Generic");
        assertEquals("Something Generic", acc.getAccName());
    }

    public void testGetAccPass() throws Exception {
        SMTAccount acc = new SMTAccount("name", "pass");
        assertEquals("pass", acc.getAccPass());
    }

    public void testSetAccPass() throws Exception {
        SMTAccount acc = new SMTAccount("name", "pass");
        acc.setAccPass("");
        assertEquals("", acc.getAccPass());
        acc.setAccPass(null);
        assertNull(acc.getAccPass());
        acc.setAccPass("Something Generic");
        assertEquals("Something Generic", acc.getAccPass());
    }

    public void testAuthAccount() throws Exception {
        SMTAccount acc = new SMTAccount("name", "pass");
        String testPass = "pass";
        assertTrue(acc.authAccount(testPass));
        testPass = "wrongpass";
        assertFalse(acc.authAccount(testPass));
        testPass = null;
        assertFalse(acc.authAccount(testPass));
    }
}