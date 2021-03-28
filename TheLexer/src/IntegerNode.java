public class IntegerNode extends Node 
{
    private int heldint;
    public IntegerNode(int heldint)
    {
        this.heldint = heldint;
    }
    public int getHeldint() 
    {
        return heldint;
    }
    @Override
    public String toString() 
    {
        String converterString = String.valueOf(heldint);
        return converterString;
    }
}
