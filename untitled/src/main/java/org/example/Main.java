package org.example;

import SpotifyApi.SpotifyClient;
import tokenManager.TokenManager;

public class Main {
    public static void main(String[] args)
    {
        /*  args [0] Spotify Client ID
            args [1] Spotify Client Secret
            args [2] Track  Name
        */

        SpotifyClient spotifyClient = new SpotifyClient(args[0],args[1]);


    }




}