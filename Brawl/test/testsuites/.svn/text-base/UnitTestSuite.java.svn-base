/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testsuites;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 *
 * @author Paul
 */
public class UnitTestSuite extends TestCase {
    
    public UnitTestSuite(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("NewTestSuite");
        suite.addTest(new TestSuite(brawl.BrawlMainTest.class, "BrawlMain"));
        suite.addTest(new TestSuite(brawl.GameControllerTest.class, "GameController"));
        suite.addTest(new TestSuite(brawl.MatchModelTest.class, "MatchModel"));
        suite.addTest(new TestSuite(brawl.SettingsModelTest.class, "SettingsModel"));
        suite.addTest(new TestSuite(brawl.model.AITest.class, "AI"));
        suite.addTest(new TestSuite(brawl.model.CardTest.class, "Card"));
        suite.addTest(new TestSuite(brawl.model.MoveTest.class, "Move"));
        suite.addTest(new TestSuite(brawl.utils.JSONUtilTest.class, "JSONUtil"));
        suite.addTest(new TestSuite(brawl.utils.MoveValidatorTest.class, "MoveValidator"));
        return suite;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

}
