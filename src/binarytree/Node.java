package binarytree;

import java.util.ArrayList;

public class Node
{
	private String word;
	private ArrayList<String> part = new ArrayList<String>();
	private ArrayList<String> description = new ArrayList<String>();

	Node leftnode = null;
	Node rightnode = null;

	public String getWord()
	{
		return word;
	}

	public void setWord(String word)
	{
		this.word = word;
	}

	public String[] getPart()
	{
		String[] Parts = new String[part.size()];
		for (int i = 0; i < part.size(); i++)
		{
			Parts[i] = part.get(i);
		}
		return Parts;
	}

	public void setPart(String part)
	{
		this.part.add(part);
	}

	public String[] getDescription()
	{
		String[] Descs = new String[description.size()];
		for (int i = 0; i < description.size(); i++)
		{
			Descs[i] = description.get(i);
		}
		return Descs;
	}

	public void setDescription(String description)
	{
		this.description.add(description);
	}

}