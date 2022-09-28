package org.example;

import SpotifyApi.SearchSpotifyTrack;
import  SpotifyApi.SpotifyClient;
import SpotifyApi.SpotifyMusicPlayer;

import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Objects;

public class Main {
    public static void main(String[] args)
    {
        /*  args [0] Spotify Client ID
            args [1] Spotify Client Secret
            args [2] Track  Name
        */
        SpotifyClient spotifyClient = new SpotifyClient(args[0],args[1]);
        //SpotifyMusicPlayer.queueAdd(spotifyClient.getSpotifyApi(), Objects.requireNonNull(SearchSpotifyTrack.SearchTrack(spotifyClient.getSpotifyApi(), args[2])));
        spotifyClient.getCurrentMusicInfo();

    }




}