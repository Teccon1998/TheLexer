import java.util.ArrayList;
import java.util.List;
public class PrintNode extends StatementNode
{
    private List<Node> NodeList = new ArrayList<Node>();

    public PrintNode(List<Node> Nodes) 
    {
        this.NodeList = Nodes;
    }

    public List<Node> getPrintNodeList()
    {
        return NodeList;
    }
    public Node getPrintNode(int i)
    {
        return NodeList.get(i);
    }

    @Override
    public String toString() 
    {
        return NodeList.toString();
    }
}