package com.example.myapplicationui.Function;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import java.util.Locale;

/**
 * Created by 박지찬 on 2017-07-16.
 */

public class TTSClass{

    private static TextToSpeech mTTS;
    private static Context mMain;
    private static String mText;
    private static String[] mArray;

    public static void Init(Context main, String text) {
        mMain = main;
        mText = text;
        mTTS = new TextToSpeech(mMain, new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int status)
            {
                //onInitListener.onInit(status); -- it raises up an error;
                if (status == TextToSpeech.SUCCESS)
                {
                    int result = mTTS.setLanguage(Locale.KOREA);
                    mTTS.setPitch(1);
                    mTTS.setSpeechRate(1);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        //Log.e(TAG, "Language is not available.");
                    }
                    else
                    {
                        mTTS.speak(mText , TextToSpeech.QUEUE_FLUSH,  null);
                    }
                }
                else
                {
                    // Initialization failed.
                    //Log.e(TAG, "Could not initialize TextToSpeech.");
                }
            }
        }

        );

    }

    public static void Init(Context main, String[] text) {
        mMain = main;
        mArray = text;
        mTTS = new TextToSpeech(mMain, new TextToSpeech.OnInitListener()
        {
            @Override
            public void onInit(int status)
            {
                //onInitListener.onInit(status); -- it raises up an error;
                if (status == TextToSpeech.SUCCESS)
                {
                    int result = mTTS.setLanguage(Locale.KOREA);
                    mTTS.setPitch(1);
                    mTTS.setSpeechRate(1);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED)
                    {
                        //Log.e(TAG, "Language is not available.");
                    }
                    else
                    {
                        mTTS.speak("1번 " + mArray[0] , TextToSpeech.QUEUE_FLUSH,  null);
                        for(int i = 1; i<mArray.length; i++){
                            if(mArray[i]!=null) {
                                mTTS.speak(i+1 + "번 " + mArray[i], TextToSpeech.QUEUE_ADD, null);
                            }
                        }
                    }
                }
                else
                {
                    // Initialization failed.
                    //Log.e(TAG, "Could not initialize TextToSpeech.");
                }
            }
        }
        );
    }

    public static void onDestroy() {
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
    }
}
