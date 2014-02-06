/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brawl.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author FBRD-Paul Doyle
 */
public class JSONUtilTest extends TestCase
{
    String sampleJSON1 =
        "{\"Test\":"
        + "[{\"1\":\"value1\"},"
        + "{\"2\":\"value2\"},"
        + "{\"3\":"
            + "["
            + "{\"A\":\"value3A\"},"
            + "{\"B\":\"value3B\"}]"
            + "}"
        + "]}";

    String sampleJSON2 =
        "{\"Test\":"
        + "[{\"1\":\"value1\"},"
        + "{\"2\":\"value2\"},"
        + "]}";

    public JSONUtilTest(String testName)
    {
        super(testName);
    }

    /**
     * Test of getJSONAsString method, of class JSONUtil.
     */
    public void testGetJSON()
    {
        System.out.println("getJSONAsString");
        String resource = "resource/JSONReadTest.json";
        String expected = sampleJSON1;
        JSONObject jsonObj = null;
        try
        {
            jsonObj = (JSONObject)JSONUtil.getJSON(resource, true);
        }
        catch (FileNotFoundException ex)
        {
            Assert.fail("File not found.");
        }
        catch (ParseException pex)
        {
            Assert.fail("Exception during parsing");
        }
        String result = jsonObj.toString().replaceAll("\\s","");
        assertTrue(result.equals(expected));
    }

    /**
     * Test of writeToJSON method, of class JSONUtil.
     */
    public void testWriteToJSON()
    {
        System.out.println("writeToJSON");
        Map<String,String> val1 = new HashMap<String,String>();
        Map<String,String> val2 = new HashMap<String,String>();
        val1.put("1","value1");
        val2.put("2","value2");
        JSONArray valArray = new JSONArray();
        valArray.add(new JSONObject(val1));
        valArray.add(new JSONObject(val2));
        Map<String,JSONArray> jsonObj = new HashMap<String, JSONArray>();
        jsonObj.put("Test",valArray);
        JSONObject testJson = new JSONObject(jsonObj);
        String resourceLocation = JSONUtil.SETTINGS_PATH + "/JSONWriteTest.json";
        JSONUtil.writeToJSON(testJson, resourceLocation);
        JSONObject result = null;
        try
        {
            result = (JSONObject)JSONUtil.getJSON(resourceLocation, false);
        }
        catch (FileNotFoundException ex)
        {
            Assert.fail("File not found.");
        }
        catch (ParseException pex)
        {
            Assert.fail("Exception during parsing");
        }
        assertTrue(result.equals(testJson));
        File unwriteable = new File(JSONUtil.SETTINGS_PATH + "unwriteable.json");
        try
        {
            unwriteable.createNewFile();
        }
        catch (IOException ex)
        {
            System.out.println("There was an error creating the file");
        }
        unwriteable.setReadOnly();
        assertFalse(JSONUtil.writeToJSON(result, unwriteable.getAbsolutePath()));
        unwriteable.delete();
    }
}