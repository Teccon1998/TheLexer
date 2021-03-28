import java.util.List;
public class StatementsNode extends StatementNode
{
    private List<StatementNode> STATEMENTS;
    public StatementsNode(List<StatementNode> statements)
    {
        this.STATEMENTS = statements;
    }

    public List<StatementNode> getStatementsList()
    {
        return STATEMENTS;
    }

    @Override
    public String toString() 
    {
        return STATEMENTS.toString();
    }
}
