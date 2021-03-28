import java.util.ArrayList;
import java.util.List;
public class Lexer 
{
    public static enum knownWords
    {
        PRINT;
    }
    public List<Token> lex(String s) throws Exception {
        ArrayList<Token> tokenlist = new ArrayList<Token>();

        String NumString = "";
        String charString= "";

        for(int i = 0; i<s.length(); i++)
        {   
            char c = s.charAt(i);
            if (Character.isDigit(c)) 
            {
                NumString += c;
                
            }
            else if(c == '/')
            {
                if(!NumString.isEmpty())
                {
                    tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                    tokenlist.add(new Token(Token.TokenType.DIV));
                    NumString = "";
                }
                else
                {
                    tokenlist.add(new Token(Token.TokenType.DIV));
                }
            } 
            else if(c == '*')
            {
                if(!NumString.isEmpty())
                {
                    tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                    tokenlist.add(new Token(Token.TokenType.TIMES));
                    NumString = "";
                }
                else
                {
                    tokenlist.add(new Token(Token.TokenType.TIMES));
                }
            }
            else if(c == '+') 
            {
                if(!NumString.isEmpty())
                {
                    tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                    tokenlist.add(new Token(Token.TokenType.PLUS));
                    NumString = "";
                }
                else
                {
                    tokenlist.add(new Token(Token.TokenType.PLUS));
                }
            } 
            else if(c == '-')
            {

                if(s.charAt(i) == '-' &&s.charAt(i+1) == '-' && Character.isDigit(s.charAt(i+2)))
                {   
                    if(NumString.isEmpty())
                    {
                        tokenlist.add(new Token(Token.TokenType.MINUS));
                    }
                    else if(!NumString.isEmpty())
                    {   
                        tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                        tokenlist.add(new Token(Token.TokenType.MINUS));
                        NumString ="";
                    }
                    else 
                    {
                        tokenlist.add(new Token(Token.TokenType.MINUS));
                        tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                        NumString ="";
                    }
                }
                else if(s.charAt(i) == '-' &&Character.isDigit(s.charAt(i+1)))
                {
                    try
                    {
                        if(s.charAt(i) == '-' && Character.isDigit(s.charAt(i+1)) && Character.isDigit(s.charAt(i-1))) 
                        {
                            tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                            tokenlist.add(new Token(Token.TokenType.MINUS));
                            NumString ="";
                        }
                        else if(Character.isDigit(s.charAt(i-1)) 
                        || tokenlist.get(i-1).getType() == Token.TokenType.PLUS 
                        || tokenlist.get(i-1).getType() == Token.TokenType.MINUS 
                        || tokenlist.get(i-1).getType() == Token.TokenType.TIMES 
                        || tokenlist.get(i-1).getType() == Token.TokenType.DIV)
                        {
                            NumString += c;
                        }
                        else
                        {
                            tokenlist.add(new Token(Token.TokenType.MINUS));
                        }

                    }catch(Exception e){
                        NumString +=c;
                    }
                    
                }
                else 
                {
                    tokenlist.add(new Token(Token.TokenType.MINUS));
                }
            }
            else if(c == ' ')
            {
                boolean printingtoken = (charString.toUpperCase().equals("PRINT"));
                if(!NumString.isEmpty())
                {
                    tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                    
                    NumString = "";
                }
                //
                else if(NumString.isEmpty() && !(charString.isEmpty()) && printingtoken)
                {
                    tokenlist.add(new Token(Token.TokenType.PRINT));
                    charString = "";
                }
            }
            else if(c == '\n')
            {
                if(!NumString.isEmpty())
                {
                    tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                    tokenlist.add(new Token(Token.TokenType.EndOfLine));
                    NumString = "";
                }
                else
                {
                    tokenlist.add(new Token(Token.TokenType.EndOfLine));
                }

            }
            else if(c == '(')
            {
                if(!NumString.isEmpty())
                {
                    tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                    tokenlist.add(new Token(Token.TokenType.LParen));
                    NumString = "";
                }
                else
                {
                    tokenlist.add(new Token(Token.TokenType.LParen));
                }

            }
            else if(c == ')')
            {
                if(!NumString.isEmpty())
                {
                    tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                    tokenlist.add(new Token(Token.TokenType.RParen));
                    NumString = "";
                }
                else
                {
                    tokenlist.add(new Token(Token.TokenType.RParen));
                }

            }
            else if(c == '<' && s.charAt(i+1) == '>')
            {
                i++;
                if(!NumString.isEmpty())
                {
                    tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                    tokenlist.add(new Token(Token.TokenType.NOTEQUALS));
                    NumString = "";
                }
                else
                {
                    tokenlist.add(new Token(Token.TokenType.NOTEQUALS));
                }

            }
            else if(c == '>' && s.charAt(i+1) == '=')
            {
                i++;
                if(!NumString.isEmpty())
                {
                    tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                    tokenlist.add(new Token(Token.TokenType.GOET));
                    NumString = "";
                }
                else
                {
                    tokenlist.add(new Token(Token.TokenType.GOET));
                }

            }
            else if(c == '<' && s.charAt(i+1) == '=')
            {
                i++;
                if(!NumString.isEmpty())
                {
                    tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                    tokenlist.add(new Token(Token.TokenType.LOET));
                    NumString = "";
                }
                else
                {
                    tokenlist.add(new Token(Token.TokenType.LOET));
                }

            }
            else if(c == '<')
            {
                if(!NumString.isEmpty())
                {
                    tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                    tokenlist.add(new Token(Token.TokenType.LESSER));
                    NumString = "";
                }
                else
                {
                    tokenlist.add(new Token(Token.TokenType.LESSER));
                }

            }
            else if(c == '>')
            {
                if(!NumString.isEmpty())
                {
                    tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                    tokenlist.add(new Token(Token.TokenType.GREATER));
                    NumString = "";
                }
                else
                {
                    tokenlist.add(new Token(Token.TokenType.GREATER));
                }

            }
            else if(c == '=')
            {
                if(!NumString.isEmpty())
                {
                    tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                    tokenlist.add(new Token(Token.TokenType.EQUALS));
                    NumString = "";
                }
                else
                {
                    tokenlist.add(new Token(Token.TokenType.EQUALS));
                }

            }
            else if(c == '"')
            {
                charString +=c;
            }
            else if(Character.isLetter(c))
            {
                charString += c;
            }
            else if(c == ':')
            {
                if(!charString.isEmpty())
                {
                    tokenlist.add(new Token(Token.TokenType.STRING,charString));
                    tokenlist.add(new Token(Token.TokenType.LABEL));
                    charString = "";
                }
                else
                {
                    tokenlist.add(new Token(Token.TokenType.LABEL));
                }

            }
            else if(c == '.')
            {
                NumString += c;
            }
            else if (c == ',')
            {
                if(NumString.isEmpty())
                    if(!charString.isEmpty())
                    {
                        tokenlist.add(new Token(Token.TokenType.STRING,charString));
                        tokenlist.add(new Token(Token.TokenType.COMMA));
                        charString = "";
                    }
                    else
                    {
                        tokenlist.add(new Token(Token.TokenType.COMMA));
                    }
                else if(!NumString.isEmpty())
                {
                    tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
                    NumString = "";
                    tokenlist.add(new Token(Token.TokenType.COMMA));
                }
            }
 //num,div,times,plus,minus,whitespace,endofline,lparen,rparen,notequals,equals,greater,goet,lesser,loet,string,label,decimal,comma
        }
        tokenlist.size();

        if(!NumString.isEmpty())
        {
            tokenlist.add(new Token(Token.TokenType.NUMBER,NumString));
        }
        if(!charString.isEmpty())
        {
            List<Token> list1 = new ArrayList<Token>();
            List<Token> list2 = new ArrayList<Token>();
            if(charString.charAt(0) == '"')
            {
                tokenlist.add(new Token(Token.TokenType.STRING,charString));
            }
            else
            {
                list1.add(new Token(Token.TokenType.IDENTIFER, charString));
                list1.addAll(tokenlist);
                for(Token T :tokenlist)
                { 
                    list2.add(T);
                }
                tokenlist.removeAll(list2);
                tokenlist.addAll(list1);
            }
        }
        List<Token> toremovelist = new ArrayList<Token>();
        List<Token> temptokenlist = new ArrayList<Token>();
        for(Token T : tokenlist)
        {
            if(T.getType() == Token.TokenType.STRING && T.getValue().equalsIgnoreCase("print"))
            {
                toremovelist.add(T);
                temptokenlist.add(new Token(Token.TokenType.PRINT));
                temptokenlist.addAll(tokenlist);
                temptokenlist.removeAll(toremovelist);
            }
            else if(T.getType() == Token.TokenType.IDENTIFER && tokenlist.get(1).getType() == Token.TokenType.PRINT)
            {
                temptokenlist.add(tokenlist.get(1));
                temptokenlist.add(T);
            }
        }
        if(temptokenlist.isEmpty())
        {
            temptokenlist.addAll(tokenlist);
        }
        // temptokenlist.addAll(tokenlist);
        // temptokenlist.removeAll(toremovelist);
        tokenlist.removeAll(tokenlist);
        tokenlist.addAll(temptokenlist);

        tokenlist.add(new Token(Token.TokenType.EndOfLine));
        return tokenlist;
    }
}