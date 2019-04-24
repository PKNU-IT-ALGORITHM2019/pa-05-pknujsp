package binarytree;

public class worddata
{
	private String word;
	private String part;
	private String description;

	public void Set_word(String w)
	{
		this.word = w;
	}

	public void Set_part(String p)
	{
		this.part = p;
	}

	public void Set_description(String d)
	{
		this.description = d;
	}

	public String Get_word()
	{
		return this.word;
	}

	public String Get_part()
	{
		return this.part;
	}

	public String Get_description()
	{
		return this.description;
	}
}
