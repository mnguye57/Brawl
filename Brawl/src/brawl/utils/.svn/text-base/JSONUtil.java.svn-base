/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package brawl.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Utility class for reading from and writing to JSON files using the json-simple
 * library
 * @author Paul
 */
public class JSONUtil
{
    /**
     * Refers to the directory where persistent settings files are stored
     */
    public static final String SETTINGS_PATH = System.getProperty("user.home")
            + "/brawl/settings/";

    static
    {
        // Before anything else, make sure the settings folder has been created
        File settingsDir = new File(System.getProperty("user.home") + "/brawl");
        if (!settingsDir.exists())
        {
            settingsDir.mkdir();
        }
        settingsDir = new File(settingsDir.getAbsolutePath() + "/settings");
        if (!settingsDir.exists())
        {
            settingsDir.mkdir();
        }
    }

    /**
     * Get a valid JSON object from the given resource path, either from a
     * file built into the jar or from one on the user's file system
     *
     * @param resource path to the resource in question
     * @param isInJar flag to indicate whether this file is bundled with the
     * jar or saved to the settings folder
     * @return an Object that can be cast to a valid JSONObject
     */
    public static Object getJSON(String resource, boolean isInJar)
        throws FileNotFoundException, ParseException
    {
        String jsonString = "";
        // If it's in the jar, use the class loader to get the resource
        if (isInJar)
        {
            jsonString = new Scanner(JSONUtil.class.getClassLoader()
                    .getResourceAsStream(resource)).useDelimiter("\\A").next();
        }
        else
        {
            // Try to open the file requested and throw an exception if it can't
            // be found
            jsonString = new Scanner(new File(resource)).useDelimiter("\\A")
                    .next();
        }

        JSONParser parser = new JSONParser();

        // Get the root JSON object
        Object obj = null;
        try
        {
            obj = parser.parse(jsonString);
        }
        catch (ParseException ex)
        {
            // Throw an exception if the JSON is malformed
            System.err.println("Parsing error.  Check that JSON file "
                    + resource + " has correct syntax.");
            throw ex;
        }

        return obj;
    }

    /**
     * Writes the given JSON object to the specified file, whose path must
     * exist
     *
     * @param obj A JSON object that will be written to a string and saved
     * @param resourceLocation The file that will be saved to (will overwrite
     * an existing file)
     * @return Returns true on success and false on failure
     */
    public static boolean writeToJSON(JSONObject obj, String resourceLocation)
    {
        try
        {
            // Get the file at the given location
            File file = new File(resourceLocation);
            // Write the contents of the JSON to the file
            FileWriter jsonFile = new FileWriter(file, false);
            jsonFile.write(obj.toJSONString());
            jsonFile.flush();
            jsonFile.close();
            return true;
        }
        catch (IOException ex)
        {
            // If something goes wrong (likely permissions or nonexistant path),
            // print an error
            System.err.println("Error writing to file:");
            ex.printStackTrace();
            return false;
        }
    }
}
