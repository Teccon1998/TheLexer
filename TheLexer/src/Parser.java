import java.util.ArrayList;
import java.util.List;
public class Parser 
    {
    int i = 0;
    private List<Token> tokens;
    private List<Node> pList = new ArrayList<Node>();

    public Parser(List<Token> t)
    {
        this.tokens = t;
    }
    public Node Parse() throws Exception 
    {
        Node expReturn = Statements();
        tokens.remove(0);
        return expReturn;
    }

    private StatementsNode Statements() throws Exception
    {
        List<StatementNode> nodestoadd = new ArrayList<StatementNode>();
        StatementNode stat;
        // while((stat = Statement()) != null)
        // {
        //     nodestoadd.add(stat);
        // }
        // for(int i = 0; nodestoadd.get(i) != null;i++)
        // {
        //     nodestoadd.add(stat);
        // }
        stat = Statement();
        do
        {

            nodestoadd.add(stat);
            i++;

        }while((stat = Statement()) != null);
        StatementsNode statNodes = new StatementsNode(nodestoadd);
        return statNodes;
    }
    private StatementNode Statement() throws Exception
    {

        AssignmentNode aNode = assignmentStatement();
        PrintNode pNode =  printStatement(aNode);
        if(aNode.getAssignedNode() == null && aNode.getVarNode() == null)
        {
            if(pNode.getPrintNode(i).toString() == null)
            {
                return null;
            }
            else
            {
                return pNode;
            }
        }
        else
        {
            return aNode;
        }
    }
    private AssignmentNode assignmentStatement() throws Exception
    {
        AssignmentNode aNode = new AssignmentNode(Expression(), Expression());
        return aNode;
    }
    private PrintNode printStatement(Node N)
    {
        if(N != null)
        {
            pList = printList(N);
            PrintNode pNode = new PrintNode(pList);
            return pNode;
        }
        return null;
    }
    private List<Node> printList(Node N)
    {
        pList.add(N);
        return pList;
    }



    private Token MatchAndReturn(Token.TokenType type)
    {
        if(tokens.get(0).getType() != type)
        {
            return null;
        }
        else
        {
            Token returnVal = tokens.get(0);
            tokens.remove(0);
            return returnVal;
        }
    }


    public Node Expression() throws Exception
    {
        Node soFar = Term();
        if(soFar == null)
        {
            return null;
        }
        return GetRightOfExpression(soFar);
    }









    private Node Term() throws Exception 
    {
        Node soFar = Factor();
        if(soFar == null)
        {
            return null;
        }
        else
        {
            return GetRightOfTerm(soFar);
        }
    }








    private Node Factor() throws Exception 
    {
        Token Num = MatchAndReturn(Token.TokenType.NUMBER);
        if(Num != null)
        {
            if(Num.getValue().contains("."))
            {
                return new FloatNode(Float.parseFloat(Num.getValue()));
            }
            else
            {
                return new IntegerNode(Integer.parseInt(Num.getValue()));
            }
        }

        if(MatchAndReturn(Token.TokenType.LParen) != null)
        {
            Node expr = Expression();
            if(expr == null)
            {
                throw new Exception("Found left paren with no expression");
            }
            if(MatchAndReturn(Token.TokenType.RParen) != null)
            {
                return expr;
            }
            throw new Exception("Found left paren with expression but no right paren");
        }
        Token IDENTIFIER = MatchAndReturn(Token.TokenType.IDENTIFER);
        if(IDENTIFIER != null)
        {
            if(tokens.get(0).getType() == Token.TokenType.EQUALS)
            {
                tokens.remove(0);
                VariableNode varNode;
                varNode = new VariableNode(IDENTIFIER.getValue());
                return varNode;
            }
            else
            {
                throw new Exception("No Equals exception");
            }
        }
        // Token PRINT = MatchAndReturn(Token.TokenType.PRINT);
        // if(PRINT != null)
        // {
        //     tokens.remove(0);
        //     PrintNode pNode = new PrintNode(tokens);
        // }

        if(tokens.get(0).getType() == Token.TokenType.EndOfLine)
        {
            return null;
        }
        return null;
    }





    public Node GetRightOfExpression(Node left) throws Exception 
    {
        
        Token operation = GetOperationExpression();
        if(operation == null)
        {
            return left;
        }
        Node secondTerm = Term();
        if(secondTerm == null)
        {
            throw new Exception("Found a + or - but no term on the right");
        }
        MathOpNode op = new MathOpNode(operation.getType() == Token.TokenType.PLUS ? MathOpNode.Operation.ADD : MathOpNode.Operation.SUBTRACT,left,secondTerm);
        return GetRightOfExpression(op);
    }
    public Token GetOperationExpression()
    {
        Token token;
        if((token = MatchAndReturn(Token.TokenType.PLUS)) != null)
        {
            return token;
        }
        else if((token = MatchAndReturn(Token.TokenType.MINUS)) != null)
        {
            return token;
        }
        else 
        {
            return token;
        }

    }

    public Node GetRightOfTerm(Node left) throws Exception 
    {
        Token operation = GetOperationTerm();
        if(operation == null)
        {
            return left;
        }
        Node secondFactor = Factor();
        if(secondFactor == null)
        {
            throw new Exception("Found a * or / but no term on the right");
        }
        MathOpNode op = new MathOpNode(operation.getType() == Token.TokenType.TIMES ?
        MathOpNode.Operation.MULTIPLY : MathOpNode.Operation.DIVIDE,
        left,secondFactor);
        return GetRightOfTerm(op);
        
    }



    public Token GetOperationTerm()
    {
        Token token;
        if((token = MatchAndReturn(Token.TokenType.TIMES)) != null)
        {
            return token;
        }
        else if((token = MatchAndReturn(Token.TokenType.DIV)) != null)
        {
            return token;
        }
        else 
        {
            return token;
        }
    }

}
