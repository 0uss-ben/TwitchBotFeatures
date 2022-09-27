package SpotifyApi;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;

public class SearchSpotifyTrack {

    public static void SearchTrack(SpotifyClient spotifyClient, SpotifyApi spotifyApi, String trackName) {
        final SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(trackName)
                .limit(10)
                .build();

        try {
            final Paging<Track> trackPaging = searchTracksRequest.execute();

            System.out.println("Total: " + trackPaging.getTotal());
        } catch (IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e)
        {
            System.out.println("Error: " + e.getMessage());
        }




    }



}
