import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class Basic 
{
        public static void main(String[] args) throws Exception 
    {
        // ensure that there is one and only one argument (main method)
		// must print out the error message & exit (if loop? exception? )
		if (args.length == 0 || 1 < args.length) 
        {
			System.out.println("Invaild arguments " + args.length);
			System.exit(0);
		}
        Path path = Paths.get(args[0]);
        try
        {
            List<String> fileList = Files.readAllLines(path);
            ArrayList<Token> finalTokenlist = new ArrayList<Token>();
            Parser pars = new Parser(finalTokenlist);
            Lexer line = new Lexer();
            for(String s : fileList)
            {
                try
                {
                    List<Token> token = new ArrayList<Token>();
                    token = line.lex(s);
                    System.out.println("");
                    System.out.println(s);
                    for(Token T : token)
                    {
                        System.out.print(Output(T));

                        finalTokenlist.add(T);
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                }
//                System.out.println(pars.Parse());
            }
            System.out.println("");
        } 
        catch(FileNotFoundException e) 
        {
            System.out.println("An error occured");
            e.printStackTrace();
        }

    }

        private static String Output(Token T) 
        {
            String tokentype = "";

            switch(T.getType())
            {
            case DIV:
                tokentype = "DIVIDE ";
                break;
            case EQUALS:
                tokentype = "EQUALS ";
                break;
            case EndOfLine:
                tokentype = "EndOfLine\n";
                break;
            case GOET:
                tokentype = "GREATER THAN OR EQUAL TO ";
                break;
            case GREATER:
                tokentype = "GREATER THAN ";
                break;
            case IDENTIFER:
                tokentype = "IDENTIFIER " +T.getValue() +" ";
                break;
            case LABEL:
                tokentype = "LABEL ";
                break;
            case LESSER:
                tokentype = "LESS THAN ";
                break;
            case LOET:
                tokentype = "LESS THAN OR EQUAL TO ";
                break;
            case LParen:
                tokentype = "LParen ";
                break;
            case MINUS:
                tokentype = "MINUS ";
                break;
            case NOTEQUALS:
                tokentype = "NOT EQUAL TO ";
                break;
            case NUMBER:
                tokentype = "NUMBER("+T.getValue()+") ";
                break;
            case PLUS:
                tokentype = "PLUS ";
                break;
            case RParen:
                tokentype = "RParen ";
                break;
            case STRING:
                tokentype = "STRING "+T.getValue() + " ";
                break;
            case TIMES:
                tokentype = "TIMES ";
                break;
            case WhiteSpace:
                tokentype = "";
                break;
            case COMMA:
                tokentype = "COMMA ";
                break;
            case PRINT:
                tokentype = "PRINT ";
                break;
            default:
                break;
            }
            return tokentype;
        }
}
