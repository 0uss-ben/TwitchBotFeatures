package SpotifyApi;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.data.player.GetUsersCurrentlyPlayingTrackRequest;

import java.io.*;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SpotifyClient implements SpotifyMusicPlayerInt
{
    private static SpotifyApi spotifyApi;
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://example.com/callback/");


    public boolean newUser()
    {
        final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri().build();
        final URI uri = authorizationCodeUriRequest.execute();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("URI: " + uri.toString());
        System.out.println("Please Enter code :");
        try {
            String code = new String(reader.readLine());

            final AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code).build();
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
            TokenManager.setNewRefreshToken(authorizationCodeCredentials.getRefreshToken());
            return true;

        }
        catch (IOException | SpotifyWebApiException | ParseException e)
        {
            System.out.println("Error: " + e.getMessage());
        }




        return false;
    }

    public boolean refreshToken()
    {
        try {



            spotifyApi.setRefreshToken(TokenManager.getRefreshToken());
            System.out.println("Refresh token : " + spotifyApi.getRefreshToken());
            AuthorizationCodeRefreshRequest authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh().build();
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
            TokenManager.setNewRefreshToken(authorizationCodeCredentials.getRefreshToken());

        }
        catch (IOException | SpotifyWebApiException | ParseException e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        return false;
    }




    public SpotifyClient(String clientId,String clientSecret){
         spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                 .setRedirectUri(redirectUri)
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
