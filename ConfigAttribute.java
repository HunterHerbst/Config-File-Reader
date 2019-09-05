import java.util.Hashtable;

/**
 * This class is the backbone for config.class. Each entry in a .conf file is a separate attribute.
 * @author Hunter Herbst
 *
 */
public class ConfigAttribute 
{
	
	private String name;
	private Hashtable<String, Integer> ints;
	private Hashtable<String, Double> doubles;
	private Hashtable<String, Boolean> booleans;
	private Hashtable<String, String> strings;
	
	/**
	 * Create a new configuration attribute.
	 * @param name The name of the new attribute.
	 */
	public ConfigAttribute(String name)
	{
		setName(name);
		ints = new Hashtable<String, Integer>();
		doubles = new Hashtable<String, Double>();
		booleans = new Hashtable<String, Boolean>();
		strings = new Hashtable<String, String>();
	}
	
	/**
	 * Set the name of the configuration attribute.
	 * @param name The new name of the attribute.
	 */
	private void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Get the name of the configuration attribute.
	 * @return The configuration attribute's name.
	 */
	public String getName()
	{
		return this.name;
	}
	
	
	/*
	 * Methods for adding values to the attribute
	 */
	
	/**
	 * Attach an integer value to the attribute.
	 * @param key The name of the integer to be attached
	 * @param value The value of the integer
	 */
	public void addInt(String key, int value)
	{
		ints.put(key, value);
	}
	
	/**
	 * Attach a double value to the attribute.
	 * @param key The name of the double to be attached
	 * @param value The value of the double
	 */
	public void addDouble(String key, double value)
	{
		doubles.put(key, value);
	}
	
	/**
	 * Attach a boolean value to the attribute.
	 * @param key The name of the boolean to be attached
	 * @param value The value of the boolean
	 */
	public void addBoolean(String key, boolean value)
	{
		booleans.put(key, value);
	}
	
	/**
	 * Attach a String to the attribute.
	 * @param key The name of the String to be attached
	 * @param value The value of the String
	 */
	public void addString(String key, String value)
	{
		strings.put(key, value);
	}
	
	
	/*
	 * Methods for getting values from the attribute
	 */
	
	/**
	 * Get an attached integer value from the attribute.
	 * @param key The name of the attached integer to be retrieved
	 * @return The value of the attached integer
	 */
	public int getInt(String key)
	{
		return ints.get(key);
	}
	
	/**
	 * Get an attached double value from the attribute.
	 * @param key The name of the attached double to be retrieved
	 * @return The value of the attached double
	 */
	public double getDouble(String key)
	{
		return doubles.get(key);
	}
	
	/**
	 * Get an attached boolean value from the attribute.
	 * @param key The name of the attached boolean to be retrieved
	 * @return The value of the attached boolean
	 */
	public boolean getBoolean(String key)
	{
		return booleans.get(key);
	}
	
	/**
	 * Get an attached String from the attribute.
	 * @param key The name of the attached String to be retrieved
	 * @return The value of the attached String
	 */
	public String getString(String key)
	{
		return strings.get(key);
	}
	
	/*
	 * Other Methods
	 */
	
	
	@Override
	/**
	 * Get the configuration attribute as a printable String
	 */
	public String toString()
	{
		String tmp = "";
		tmp+=getName() + ":\n";
		tmp+="\tStrings: " + strings + "\n";
		tmp+="\tIntegers: " + ints + "\n";
		tmp+="\tDoubles: " + doubles + "\n";
		tmp+="\tBooleans: " + booleans;
		
		return tmp;
	}
}
