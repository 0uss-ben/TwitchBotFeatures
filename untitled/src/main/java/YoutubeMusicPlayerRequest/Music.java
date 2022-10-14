package YoutubeMusicPlayerRequest;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Music
{


    private static final String API_KEY = "AIzaSyAO5-VJEucVBlHCwUdV5peal2CE4kQgGnI";


    public static void Play(String search)
    {
        search = search.replace(" ","_");
         String videoTitle;
         String videoId;
        try
        {
            URL url = new URL(String.format("https://youtube.googleapis.com/youtube/v3/search?part=snippet&q=%s&type=video&key=%s", search, API_KEY));
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder stringBuilder = new StringBuilder();
            if(connection.getResponseCode() == 200)
            {
                System.out.println("OK 200");
                String inputStream;
                while((inputStream = reader.readLine())!= null)
                {
                    stringBuilder.append(inputStream);

                }

                System.out.println("test : ");
                int i = stringBuilder.indexOf("[");
                int j = stringBuilder.indexOf("]");
                System.out.println(stringBuilder);


                JSONArray jsonArray = new JSONArray(stringBuilder.substring(i,j+1));
                videoId = jsonArray.getJSONObject(0).getJSONObject("id").getString("videoId");
                videoTitle = jsonArray.getJSONObject(0).getJSONObject("snippet").getString("title");
                System.out.println("Title:".concat(videoTitle));
                System.out.println("VideoId :".concat(videoId));

                Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome youtube.com/watch?v=".concat(videoId)});

            }
                connection.disconnect();

        }
        catch (Exception ex)
        {
            ex.getStackTrace();
        }


    }





}
