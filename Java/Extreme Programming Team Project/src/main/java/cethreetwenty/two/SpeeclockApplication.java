/* Copyright (c) 2019 Cognitran Limited. All Rights Reserved. */
package cethreetwenty.two;

import java.time.LocalTime;

import cethreetwenty.two.application.ApplicationFrame;
import cethreetwenty.two.utility.TextToSpeech;

public class SpeeclockApplication
{
    public static void main(String[] args)
    {
        new ApplicationFrame();
        final LocalTime time = LocalTime.now();
        final int hour = time.getHour();
        final int minutes = time.getMinute();

        TextToSpeech.textToSpeech("The time is " + String.format("%02d", hour) + " " + String.format("%02d", minutes));
    }
}
