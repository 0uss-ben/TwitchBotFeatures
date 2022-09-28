package SpotifyApi;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.requests.data.player.GetUsersCurrentlyPlayingTrackRequest;

import java.io.IOException;

public class SpotifyClient implements SpotifyMusicPlayerInt
{
    private static SpotifyApi spotifyApi;
    public SpotifyClient(String clientId,String clientSecret){
         spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .build();
    }
    public SpotifyApi getSpotifyApi(){

        return this.spotifyApi;
    }


    @Override
    public void getCurrentMusicInfo()
    {
         final GetUsersCurrentlyPlayingTrackRequest getUsersCurrentlyPlayingTrackRequest = spotifyApi
                .getUsersCurrentlyPlayingTrack()
                .build();
        try {
            final CurrentlyPlaying currentlyPlaying = getUsersCurrentlyPlayingTrackRequest.execute();

            System.out.println("Timestamp: " + currentlyPlaying.getTimestamp());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.out.println("Error: " + e.getCause());
        }


    }
}
