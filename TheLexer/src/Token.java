public class Token 
{
    private TokenType type;
    private String value;    

    public enum TokenType 
    {
		NUMBER, DIV, TIMES, PLUS, MINUS, WhiteSpace, EndOfLine, LParen, RParen, NOTEQUALS, EQUALS,
		GREATER, GOET, LESSER, LOET, STRING, IDENTIFER, LABEL, DECIMAL, COMMA,PRINT;
    }
    public Token(TokenType type, String value)
    {
        this.type = type;
        this.value = value;
    }
    public Token(TokenType type)
    {
        this.type = type;
    }
    public TokenType getType()
    {
        return type;
    }
    public String getValue()
    {
        return value;
    }
    public void setType(TokenType type)
    {
        this.type = type;
    }
    public void setValue(String value)
    {
        this.value = value;
    }
    
    @Override
    public String toString()
    {
        if(this.type == TokenType.EndOfLine)
        {
            return type +"\n";
        }
        return "Token [TokenType " + type + ", Value: " + value + "]";
    }
}
