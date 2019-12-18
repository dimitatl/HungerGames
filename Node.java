package hungerGames2020;

import java.util.ArrayList;

public class Node {
	Node parent;
	ArrayList<Node> children = new ArrayList<>();;
	int nodeDepth;
	int [] nodeMove;
	Board nodeBoard;
	double nodeEvaluation;
	
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
