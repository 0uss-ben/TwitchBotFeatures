package MusicFinder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public final class MusicPlayer {

    private static final String API_KEY = "AIzaSyAO5-VJEucVBlHCwUdV5peal2CE4kQgGnI";
    public static void play(String searchString){
        searchString= searchString.replace(" ","_");
        System.out.println();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(String.format("https://youtube.googleapis.com/youtube/v3/search?part=snippet&q=%s&type=video&key=%s", searchString, API_KEY))).build();
        client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(MusicPlayer:: parse)
                .thenAccept(System.out::println)
                .join();

    }

    public static String parse(String response)
    {
        //JSONObject jsonObject = new JSONObject(response);
        //JSONArray infos = new JSONArray(jsonObject.getJSONArray("items"));
        //JSONObject video = infos.getJSONObject(0);
        //JSONObject videoTitle = (JSONObject) video.get("snippet");
        //System.out.println("Title :".concat(videoTitle.getString("title")));
        //JSONObject videoId = (JSONObject) video.get("id");
        //System.out.println("VideoId :".concat(videoId.getString("videoId")));
        try {

            //Runtime.getRuntime().exec(new String[]{"cmd", "/c","start chrome youtube.com/watch?v=".concat(videoId.getString("videoId"))});
        }
        catch (Exception ex)
        {
            ex.getStackTrace();
        }


        return null;
    }


}
