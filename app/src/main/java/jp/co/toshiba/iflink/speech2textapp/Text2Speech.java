package jp.co.toshiba.iflink.speech2textapp;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.HashMap;
import java.util.Locale;

public class Text2Speech {
    final String TAG ="TEXT2SPEECH";
    Context context = null;
    TextToSpeech tts;
    public Text2Speech(Context cnt){
        context = cnt;
    }
    public void speak(String text){
        tts =new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                try{
                    textSpeak(text);
                }catch (Exception ex){
                    Log.e(TAG, ex.getLocalizedMessage());
                }
            }
        });
    }
    private void textSpeak(String text){
        tts.setLanguage(Locale.JAPANESE);
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
    }
}
