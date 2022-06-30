package cethreetwenty.two.utility;

import java.io.File;
import java.io.FileOutputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.*;


import cethreetwenty.two.SoundSettings.SoundSettingsTab;
import com.voicerss.tts.*;
import com.voicerss.tts.AudioFormat;


public class TextToSpeech
{
    private static final String SPEECH_DATA_FILENAME = "latestspeech.wav";
    private static final VoiceProvider VOICE_PROVIDER = new VoiceProvider("8b17d494292e49fcba696e8701877a24");

    public static String languageSelected = "English";

    static
    {
        VOICE_PROVIDER.addSpeechErrorEventListener(new SpeechErrorEventListener()
        {
            @Override
            public void handleSpeechErrorEvent(SpeechErrorEvent e)
            {
                System.out.print(e.getException().getMessage());
            }
        });

        VOICE_PROVIDER.addSpeechDataEventListener(new SpeechDataEventListener()
        {
            @Override
            public void handleSpeechDataEvent(SpeechDataEvent e)
            {
                try (FileOutputStream fos = new FileOutputStream(SPEECH_DATA_FILENAME))
                {
                    byte[] voice = (byte[]) e.getData();
                    fos.write(voice, 0, voice.length);
                    fos.flush();
                    fos.close();
                    if (SoundSettingsTab.soundOn) {
                        playAudioFile(SPEECH_DATA_FILENAME);
                        System.out.println("Audio file played.");
                    } else {
                        System.out.println("Sound is muted.");
                    }
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        });
    }

    private String text;


    public TextToSpeech(final String text)
    {
        this.text = text;
    }

    public static void textToSpeech(final String text)
    {
        VOICE_PROVIDER.speechAsync(new TextToSpeech(text).getParams());
        System.out.println("Audio file created: " + text);
    }

    public VoiceParameters getParams() {
        String language = "";
        if (languageSelected.equals("English")) {
            language = Languages.English_GreatBritain;
        }
        else if(languageSelected.equals("Russian")) {
            language = Languages.Russian;
        }
        else if(languageSelected.equals("Chinese")) {
            language = Languages.Chinese_HongKong;
        }
        else if(languageSelected.equals("Japanese")) {
            language = Languages.Japanese;
        }


        VoiceParameters params = new VoiceParameters(text, language);

        params.setCodec(AudioCodec.WAV);
        params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setSSML(false);
        params.setRate(0);

        return params;
    }

    public static void playAudioFile(final String filename)
    {
        try
        {
            File yourFile = new File(filename);
            AudioInputStream stream;
            javax.sound.sampled.AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            FloatControl volume= (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float range = volume.getMaximum() - volume.getMinimum();
            float gain = (range * SoundSettingsTab.soundVolume/100) + volume.getMinimum();
            volume.setValue(gain);
            clip.start();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public static String getLanguageSelected() {
        return languageSelected;
    }

    public static void setLanguageSelected(String languageSelected) {
        TextToSpeech.languageSelected = languageSelected;
        System.out.println("Language selected: " + languageSelected);
    }
}
