package application;

import java.util.ArrayList;

public class Node {
	Node parent;	//the parent of each node
	ArrayList<Node> children = new ArrayList<>();	//a list of all the children of each node
	int nodeDepth;			//the depth of the node in the MinMaxTree
	int [] nodeMove;		//the x.position, y.position and dice of each node
	Board nodeBoard;		
	double nodeEvaluation;	//for the evaluation of the node
	
	public Node() {
		parent = null;
		nodeDepth = 0;
		nodeMove = null;
		nodeBoard = null;
		nodeEvaluation = 0;
	}
	public Node(Node node) {
		node.parent = parent;
		node.nodeDepth = nodeDepth;
		node.nodeBoard = nodeBoard;
		node.nodeMove = nodeMove;
		node.nodeEvaluation = nodeEvaluation;
	}
	public Node(Node parent, int [] nodeMove, int nodeDepth) {
		this.parent = parent;
		this.nodeDepth = nodeDepth;
		this.nodeMove = nodeMove;
		this.nodeBoard = null;
		this.nodeEvaluation = 0;
	}
	public void setNodeDepth(int nodeDepth) {
		this.nodeDepth = nodeDepth;
	}
	public int getNodeDepth() {
		return nodeDepth;
	}
	public void setNodeEvaluation(double nodeEvaluation) {
		this.nodeEvaluation = nodeEvaluation;
	}
	public double getNodeEvaluation() {
		return nodeEvaluation;
	}
}
