package org.example;

import SpotifyApi.SearchSpotifyTrack;
import  SpotifyApi.SpotifyClient;

public class Main {
    public static void main(String[] args)
    {
        ///args [0] Spotify Client ID
        ///args [1] Spotify Client Secret
        ///args [2] Spotify Track  Name
        SpotifyClient spotifyClient = new SpotifyClient(args[0],args[1]);
        SearchSpotifyTrack.SearchTrack(spotifyClient.getSpotifyApi(),args[2]);

    }




}