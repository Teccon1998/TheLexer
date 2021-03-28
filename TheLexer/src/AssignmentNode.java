public class AssignmentNode extends StatementNode
{

    private VariableNode varNode;
    private Node assignedNode;

    public AssignmentNode(Node vNode, Node assigNode)
    {
        this.varNode = (VariableNode) vNode;
        this.assignedNode = assigNode;
    }

    public Node getAssignedNode() 
    {
        return assignedNode;
    }

    public Node getVarNode()
    {
        return varNode;
    }

    @Override
    public String toString() 
    {
        if(assignedNode != null && varNode != null)
        {
            return varNode.toString() +" = "+ assignedNode.toString();
        }
        else
        {
            return null;
        }
        
    }
    
}
