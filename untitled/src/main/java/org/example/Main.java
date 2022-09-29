package org.example;

import SpotifyApi.SearchSpotifyTrack;
import  SpotifyApi.SpotifyClient;
import SpotifyApi.SpotifyMusicPlayer;

import java.io.File;
import java.io.IOException;
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
        File file = new File("token.txt");

        if(! file.exists())
        {
            try {
                boolean b = file.createNewFile();
                if(b)
                {
                    spotifyClient.newUser();
                }
            }
            catch (IOException | SecurityException ex)
            {
                System.out.printf("Error :%s%n",ex.getMessage());
            }

        }
        else
        {
            if(file.length() == 0)
            {
                spotifyClient.newUser();
            }
            else
            {
                spotifyClient.refreshToken();
            }

            System.out.println("Already exist");

        }




        /*
        //SpotifyMusicPlayer.queueAdd(spotifyClient.getSpotifyApi(), Objects.requireNonNull(SearchSpotifyTrack.SearchTrack(spotifyClient.getSpotifyApi(), args[2])));
        //spotifyClient.getCurrentMusicInfo();*/

    }




}