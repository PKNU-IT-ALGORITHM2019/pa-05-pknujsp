package binarytree;

import java.util.ArrayList;

public class binsearchtree
{
	private static Node rootnode = null;
	private ArrayList<String> nodes = new ArrayList<String>();

	public Node getRootnode()
	{
		return rootnode;
	}

	public void setRootnode(Node node)
	{
		rootnode = node;
	}

	public Node getNodePos(String[] values)
	{
		String word = values[0];
		String part = values[1];
		String description = values[2];
		Node currentNode = getRootnode();

		while (currentNode != null)
		{
			String c_word = currentNode.getWord();
			int result = word.compareTo(c_word);
			if (result == 0) // 단어가 일치하는 경우
			{
				currentNode.setPart(part);
				currentNode.setDescription(description);
				return currentNode;
			} else if (result < 0) // 추가할 단어 < 현재 노드의 단어
			{
				currentNode = currentNode.leftnode;
			} else // 추가할 단어 > 현재 노드의 단어
			{
				currentNode = currentNode.rightnode;
			}
		}

		return null;
	}

	public void insertnode(String[] values)
	{
		if (getNodePos(values) != null)
			return;
		Node parentNode = null, newNode = new Node(), currentNode = getRootnode();

		String word = values[0];
		newNode.setWord(word);
		newNode.setPart(values[1]);
		newNode.setDescription(values[2]);

		if (getRootnode() == null)
			setRootnode(newNode);
		else
		{
			while (currentNode != null)
			{
				parentNode = currentNode;
				String c_word = currentNode.getWord();
				int result = word.compareTo(c_word);
				if (result < 0) // 추가할 단어 < 현재 노드의 단어
				{
					currentNode = currentNode.leftnode;
					if (currentNode == null)
					{
						parentNode.leftnode = newNode;
						return;
					}
				} else // 추가할 단어 > 현재 노드의 단어
				{
					currentNode = currentNode.rightnode;
					if (currentNode == null)
					{
						parentNode.rightnode = newNode;
						return;
					}
				}
			}
		}
		if (!nodes.isEmpty())
			nodes.clear();
	}

	public int deleteNode(String word)
	{
		if (getRootnode() == null)
			return 1;
		Node parent = rootnode, current = rootnode;
		boolean isleft = true;

		while (!current.getWord().equals(word))
		{
			String c_word = current.getWord();
			int result = word.compareTo(c_word);
			parent = current;

			if (result < 0)
			{
				current = current.leftnode;
				isleft = true;
			} else
			{
				current = current.rightnode;
				isleft = false;
			}
			if (current == null)
			{
				return 2;
			}
		}

		if ((current.leftnode == null) && (current.rightnode == null))
		{
			if (current == rootnode)
				rootnode = null;
			else if (isleft)
				parent.leftnode = null;
			else
				parent.rightnode = null;

		} else if ((current.leftnode == null) || (current.rightnode == null))
		{
			int pos = (current.leftnode == null) ? 2 : 1;
			if (parent != null)
			{
				if (current == parent.leftnode)
					parent.leftnode = current.leftnode;
				else
					parent.rightnode = current.rightnode;
			} else
			{
				if (pos == 2)
					rootnode = current.rightnode;
				else
					rootnode = current.leftnode;
			}
		} else
		{
			Node successor = getSuccessor(current);
			if (current == rootnode)
			{
				rootnode = successor;
			} else if (current == parent.leftnode)
			{
				parent.leftnode = successor;
			} else
			{
				parent.rightnode = successor;
			}
			successor.leftnode = current.leftnode;

		}

		nodes.clear();
		return 3;
	}

	public Node getSuccessor(Node node)
	{
		Node successsor = null;
		Node successsorParent = null;
		Node current = node.rightnode;
		while (current != null)
		{
			successsorParent = successsor;
			successsor = current;
			current = current.leftnode;
		}
		if (successsor != node.rightnode)
		{
			successsorParent.leftnode = successsor.rightnode;
			successsor.rightnode = node.rightnode;
		}
		return successsor;
	}

	public int getTreeSize()
	{
		start_inorderTravelsal(getRootnode());
		int size = nodes.size();
		nodes.clear();
		return size;
	}

	private void start_inorderTravelsal(Node node)
	{
		if (node != null)
		{
			start_inorderTravelsal(node.leftnode);
			nodes.add(node.getWord());
			start_inorderTravelsal(node.rightnode);
		}
	}

	public Node Search_data(String word)
	{
		Node node = null;

		if (getRootnode() == null)
			return null;

		node = rootnode;
		while (node != null)
		{
			String c_word = node.getWord();
			int result = word.compareTo(c_word);
			if (result == 0) // 단어가 일치하는 경우
				break;
			else if (result < 0) // 검색 단어 < 현재 노드의 단어
				node = node.leftnode;
			else // 검색 단어 > 현재 노드의 단어
				node = node.rightnode;
		}
		return node;
	}
}
