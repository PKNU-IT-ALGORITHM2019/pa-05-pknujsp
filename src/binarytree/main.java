package binarytree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class main
{
	static public binsearchtree tree = null;

	public static void main(String[] args)
	{

		Scanner scanner = new Scanner(System.in);
		while (true)
		{
			System.out.print("명령어 입력 : ");
			String code = scanner.nextLine();
			if (code.equals("exit"))
				break;
			analysis_code(code);
		}
		System.out.println("종료됨");
		scanner.close();
	}

	private static void analysis_code(String code)
	{
		String[] separated_code = code.split(" ");
		String method = null, value = null;
		if (separated_code.length == 1)
		{
			method = separated_code[0];
		} else
		{
			method = separated_code[0];
			value = separated_code[1];
		}

		switch (method)
		{
		case "read":
			readtxt rt = new readtxt();
			ArrayList<worddata> list = rt.Read_txtfile(value);

			tree = new binsearchtree();

			for (int i = 0; i < list.size(); i++)
			{
				String[] values = { list.get(i).Get_word(), list.get(i).Get_part(), list.get(i).Get_description() };
				tree.insertnode(values);
			}
			list = null;
			System.out.println("읽기완료");
			break;
		case "size":
			System.out.println("단어의 개수 : " + tree.getTreeSize());
			break;
		case "find":
			Node node = tree.Search_data(value);
			if (node == null)
			{
				System.out.println("존재하지 않음");
				return;
			}
			System.out.println("word : " + node.getWord());
			for (int i = 0; i < node.getPart().length; i++)
			{
				System.out.println("class : " + node.getPart()[i]);
				System.out.println("meaning : " + node.getDescription()[i]);
			}
			break;
		case "add":
			Scanner scan = new Scanner(System.in);
			String[] values = new String[3];

			System.out.print("word : ");
			values[0] = scan.nextLine();

			System.out.print("class : ");
			values[1] = scan.nextLine();

			System.out.print("meaning : ");
			values[2] = scan.nextLine();

			if (values[0].equals("") || values[2].equals(""))
			{
				System.out.println("입력 오류");
				break;
			}
			tree.insertnode(values);
			System.out.println("추가 완료");

			break;
		case "delete":
			int res = tree.deleteNode(value);
			if (res == 1)
				System.out.println("Empty.");
			else if (res == 2)
				System.out.println("Not found.");
			else
				System.out.println("Deleted successfully.");
			break;
		case "deleteall":
			ArrayList<String> words = new ArrayList<String>();
			try
			{
				File file = new File(value);
				BufferedReader buff = new BufferedReader(new FileReader(file));

				String c = "";
				while ((c = buff.readLine()) != null)
				{
					words.add(c);
				}
				buff.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			} finally
			{
				for (int i = 0; i < words.size(); i++)
				{
					tree.deleteNode(words.get(i));
				}
				System.out.println(words.size() + " words were deleted successfully.");
			}
			break;
		}
	}

}
