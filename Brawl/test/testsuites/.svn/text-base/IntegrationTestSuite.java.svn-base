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
public class IntegrationTestSuite extends TestCase {
    
    public IntegrationTestSuite(String testName) {
        super(testName);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("IntegrationTestSuite");
        suite.addTest(new TestSuite(brawl.GameControllerIntTest.class, "GameController"));
        suite.addTest(new TestSuite(brawl.MatchModelIntTest.class, "MatchModel"));
        suite.addTest(new TestSuite(brawl.SettingsModelIntTest.class, "SettingsModel"));
        suite.addTest(new TestSuite(brawl.model.AIIntTest.class, "AI"));
        suite.addTest(new TestSuite(brawl.model.ColumnIntTest.class, "Column"));
        suite.addTest(new TestSuite(brawl.model.DeckIntTest.class, "Deck"));
        suite.addTest(new TestSuite(brawl.utils.KeyMapperIntTest.class, "KeyMapper"));
        suite.addTest(new TestSuite(brawl.utils.MoveValidatorIntTest.class, "MoveValidator"));
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
