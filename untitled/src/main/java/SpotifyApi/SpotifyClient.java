package SpotifyApi;


import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import tokenManager.TokenManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Path;

public class SpotifyClient
{
    private static SpotifyApi spotifyApi;
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://example.com/callback/");

    File file = new File(Path.of("token.txt").toString());

    public SpotifyClient(String clientId,String clientSecret){
         spotifyApi = new SpotifyApi.Builder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRedirectUri(redirectUri)
                .build();

         if(!TokenManager.tokenFileExist() || TokenManager.tokenFileIsEmpty())
         {
             String code =newSetup();
             final AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
                     .build();
             try {
                 final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

                 // Set access and refresh token for further "spotifyApi" object usage
                 spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
                 spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
                 TokenManager.setRefreshToken(spotifyApi.getRefreshToken());
                 System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
             } catch (IOException | SpotifyWebApiException | ParseException e) {
                 System.out.println("Error: " + e.getMessage());
             }



         }
         else
         {
             spotifyApi.setRefreshToken(TokenManager.getRefreshToken());
             final AuthorizationCodeRefreshRequest authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh()
                     .build();
             try {
                 final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();

                 // Set access and refresh token for further "spotifyApi" object usage
                 spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
                 spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
                 TokenManager.setRefreshToken(spotifyApi.getRefreshToken());

                 System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
             } catch (IOException | SpotifyWebApiException | ParseException e) {
                 System.out.println("Error: " + e.getMessage());
             }
         }
    }

    private String newSetup()
    {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .build();
        final URI uri = authorizationCodeUriRequest.execute();
        System.out.println("URI: " + uri.toString());
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(System.in));
        System.out.println("Enter the code : ");
        try
        {
            return bufferedReader.readLine();
        }catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return "empty";
    }








}
