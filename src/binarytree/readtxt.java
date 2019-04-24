package binarytree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class readtxt
{

	public ArrayList<worddata> Read_txtfile(String filename)
	{
		ArrayList<worddata> list = new ArrayList<worddata>();
		try
		{
			File file = new File(filename);
			BufferedReader buff = new BufferedReader(new FileReader(file));
			String string = "";

			while ((string = buff.readLine()) != null)
			{
				worddata wd = new worddata();
				wd.Set_word(getword(string));
				wd.Set_part(getpart(string));
				wd.Set_description(getdescription(string));
				list.add(wd);
			}
			buff.close();

		} catch (IOException e)
		{
		} finally
		{

		}
		return list;
	}

	private String getword(String string)
	{
		String[] separated_words = string.split(" \\(");

		return separated_words[0];
	}

	private String getpart(String string)
	{
		String[] separated_category = string.split("\\(");
		String separated_string = separated_category[1];

		separated_category = separated_string.split("\\)");
		separated_string = "(" + separated_category[0] + ")";

		return separated_string;
	}

	private String getdescription(String string)
	{
		String[] separated_description = string.split("\\) ");
		String final_description = null;
		if (separated_description.length == 1)
		{
			final_description = "";
		} else
		{
			final_description = separated_description[1];

			for (int i = 2; i < separated_description.length; i++)
			{
				final_description = final_description + ")" + separated_description[i];
			}
		}
		return final_description;
	}
}
