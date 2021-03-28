public class FloatNode extends Node 
{

    private float heldfloat;


    public FloatNode(float heldfloat)
    {
        this.heldfloat = heldfloat;
    }
    public float getHeldFloat() 
    {
        return heldfloat;
    }

    @Override
    public String toString() 
    {
        String converterString = String.valueOf(heldfloat);
        return converterString;
    }
    
}
