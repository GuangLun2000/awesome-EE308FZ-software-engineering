package cn.doubleq666.bobing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private String nickname;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaPlayer1;
    private MediaPlayer mediaPlayer2;
    private MediaPlayer mediaPlayer3;
    private MediaPlayer mediaPlayer4;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void playDice() {

        mediaPlayer1.start();
    }

    public void playBtn() {
        mediaPlayer2.start();
    }

    public void playNiu() {
        mediaPlayer3.start();
    }

    public void playNa() {
        mediaPlayer4.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.m1);
        mediaPlayer1 = MediaPlayer.create(getApplicationContext(), R.raw.m2);
        mediaPlayer2 = MediaPlayer.create(getApplicationContext(), R.raw.m3);
        mediaPlayer3 = MediaPlayer.create(getApplicationContext(), R.raw.m4);
        mediaPlayer4 = MediaPlayer.create(getApplicationContext(), R.raw.m5);

        mediaPlayer.setLooping(true);
        mediaPlayer.start();
//        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//注意要清除 FLAG_TRANSLUCENT_STATUS flag
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK ) {
            //do something.
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        mediaPlayer.stop();
        mediaPlayer.release();

        mediaPlayer1.stop();
        mediaPlayer1.release();

        mediaPlayer2.stop();
        mediaPlayer2.release();

        mediaPlayer3.stop();
        mediaPlayer3.release();

        mediaPlayer4.stop();
        mediaPlayer4.release();
    }
}