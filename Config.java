import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

/**
 * This class is used to read configuration files and load data into the game.
 * @author Hunter Herbst
 *
 */
public class Config 
{

	private BufferedReader br;//BufferedReader used to read the file
	private String filename;//The name of the config file
	private Hashtable<String, ConfigAttribute> attributes;//A hashtable created from the attributes created in the config file
	
	/**
	 * Open a new Configuration file with a given file name.
	 * @param filename The file name of the desired Configuration file.
	 */
	public Config(String filename)
	{
		setFilename(filename);
		attributes = new Hashtable<String, ConfigAttribute>();
		File cfgDir = new File("src/configs/" + filename);
		try
		{
			br = new BufferedReader(new FileReader(cfgDir));
		}
		catch(IOException ioe)
		{
			//TODO change the error output to run on a boolean value to change between output types
			
			//Use for stack trace debugging
			//ioe.printStackTrace();
			
			//Use for user-friendly output
			System.out.println("Error: Could not open config file \"" + filename + "\"");
		}
	}
	
	public Config(String filename, Boolean loadImmediately)
	{
		setFilename(filename);
		attributes = new Hashtable<String, ConfigAttribute>();
		File cfgDir = new File("src/configs/" + filename);
		try
		{
			br = new BufferedReader(new FileReader(cfgDir));
		}
		catch(IOException ioe)
		{
			//TODO change the error output to run on a boolean value to change between output types
			
			//Use for stack trace debugging
			//ioe.printStackTrace();
			
			//Use for user-friendly output
			System.out.println("Error: Could not open config file \"" + filename + "\"");
		}
		if(loadImmediately)
		{
			loadConfig();
		}
	}
	
	
	/*
	 * Getter and setter methods for the file name
	 */
	
	/**
	 * Set the config file location
	 * @param filename The name of the config file
	 */
	private void setFilename(String filename)
	{
		this.filename = filename;
	}
	
	/**
	 * Get the config file name
	 * @return The name of the config file
	 */
	private String getFilename()
	{
		return this.filename;
	}
	
	
	/*
	 * Methods for getting and using ConfigAttributes
	 */
	
	public ConfigAttribute getAttribute(String key)
	{
		return attributes.get(key);
	}
	
	public void loadConfig()
	{
		String configText = getText();
		this.done();
		//System.out.println(configText);
		
		String[] splitAttributes = configText.substring(0, configText.length()-1).split(";");
	
		for(int i = 0; i < splitAttributes.length; i++)
		{
			addAttribute(splitAttributes[i]);
		}
	}
	
	private void addAttribute(String attribute)
	{
		String attr = attribute.trim();
		
		//System.out.println(attr);
		
		String[] attrComps = attr.split("\\n");
		
		ConfigAttribute tmp = new ConfigAttribute(attrComps[0].trim().substring(1, attrComps[0].trim().length()-1));
		//System.out.println(tmp.getName());
		
		for(int i = 2; i < attrComps.length-1; i++)
		{
			String[] component = new String[3];
			
			component[0] = attrComps[i].trim().substring(0,1);
			component[1] = attrComps[i].trim().substring(2).split("=")[0];
			component[2] = attrComps[i].trim().substring(2).split("=")[1];
		
		
			switch(component[0].charAt(0))
			{
			case 'S':
				tmp.addString(component[1], component[2]);
				break;
			case 'I':
				tmp.addInt(component[1], Integer.parseInt(component[2]));
				break;
			case 'D':
				tmp.addDouble(component[1], Double.parseDouble(component[2]));
				break;
			case 'B':
				tmp.addBoolean(component[1], Boolean.parseBoolean(component[2]));
				break;
			}
		}
		
		attributes.put(tmp.getName(), tmp);
		
	}
	
	/*
	 * Methods for using/manipulating the BufferedReader
	 */
	
	/**
	 * When the config file is done being used, call this method to close the file.
	 * @return Whether or not the file successfully closed
	 */
	public boolean done()
	{
		try
		{
			br.close();
			return true;
		}
		catch(IOException ioe)
		{
			//TODO change the error output to run on a boolean value to change between output types
			
			//Use for stack trace debugging
			//ioe.printStackTrace();
			
			//Use for user-friendly output
			System.out.println("Error: Could not close config file \"" + getFilename() + "\"");
			
			return false;
		}
	}
	
	private String getText()
	{
		String str = "";
		String line;
		
		try
		{
			line = br.readLine();
			while(line != null)
			{
				if(line.trim().length() > 0 && line.trim().charAt(0) != '#')
				{
					str+=line + "\n";
				}
				line = br.readLine();
			}
			
			return str;
		}
		catch(IOException ioe)
		{
			//TODO change the error output to run on a boolean value to change between output types
			
			//Use for stack trace debugging
			//ioe.printStackTrace();
			
			//Use for user-friendly output
			System.out.println("Error: Could not read the config file \"" + getFilename() + "\"");
			return null;
		}
	}
	
	@Override
	public String toString()
	{
		return "Config file \"" + getFilename() + "\" has the following attributes:\n"
				+ attributes;
	}
	
	
}
