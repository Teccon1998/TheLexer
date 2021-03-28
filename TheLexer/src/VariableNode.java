public class VariableNode extends StatementNode{

    private String VarNodeContainer;
    public VariableNode(String name) 
    {
        VarNodeContainer = name;
    }
    @Override
    public String toString() {
       return VarNodeContainer;
    }
    
}

