public class MathOpNode extends Node
{
    public static enum Operation
    {
        ADD,SUBTRACT,MULTIPLY,DIVIDE;
    }
    private Operation Operation;
    private Node leftOperand; 
    private Node rightOperand;
    public MathOpNode(Operation Operation, Node leftOperand, Node rightOperand)
    {
        this.Operation= Operation;
        this.leftOperand = leftOperand;
        this.rightOperand = rightOperand;
    }
    public Node getLeftOperand()
    {
        return leftOperand;
    }
    public Node getRightOperand()
    {
        return rightOperand;
    }
    public Operation getOperation()
    {
        return Operation;
    }
    public String toString()
    {
        String mathnodereturn = "MathOpNode("+Operation+", "+leftOperand+", "+rightOperand+")";
        return mathnodereturn;
    }

}
