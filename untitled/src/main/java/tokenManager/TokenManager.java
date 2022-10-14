package tokenManager;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class TokenManager
{
    public static void setRefreshToken(String refreshToken)
    {
        try{

            BufferedWriter bufferedWriter = Files.newBufferedWriter(Path.of("token.txt"), StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);
            bufferedWriter.write(refreshToken);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static String getRefreshToken()
    {

        try{
            BufferedReader bufferedReader = Files.newBufferedReader(Path.of("token.txt"));
            return bufferedReader.readLine();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return "empty";
    }

    public static boolean tokenFileExist()
    {

        File file = new File(Path.of("token.txt").toString());
        if (file.exists()) {
            System.out.println("Exist");
            return true;
        } else {
            System.out.println("Doesn't Exist");
            try{
                if(file.createNewFile())
                    System.out.println("File Created");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return false;
        }
    }

    public static boolean tokenFileIsEmpty()
    {

        try{
            BufferedReader bufferedReader = Files.newBufferedReader(Path.of("token.txt"),StandardCharsets.UTF_8);
            return bufferedReader.readLine().isEmpty();

        }
        catch(NullPointerException e)
        {
         return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }


}

