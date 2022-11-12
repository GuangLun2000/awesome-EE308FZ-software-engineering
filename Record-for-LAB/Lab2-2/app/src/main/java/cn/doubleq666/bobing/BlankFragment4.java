package cn.doubleq666.bobing;

import static android.content.Context.SENSOR_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;
//import static androidx.core.app.AppOpsManagerCompat.Api23Impl.getSystemService;

import static java.lang.Thread.sleep;

import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Date;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment4#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment4 extends Fragment {

    private SensorManager sensorManager;
    private Vibrator vibrator;
    private long pret;
    private View mView;
    private TextView line;

    private static final String TAG = "TestSensorActivity";
    private static final int SENSOR_SHAKE = 10;

    private WebView webView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView textView2;
    TextView textView1;
    TextView textView;

    View paiming;

    private int cnt = 0;
    private int now = 5;
    String nickname;

    public BlankFragment4() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment4.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment4 newInstance(String param1, String param2) {
        BlankFragment4 fragment = new BlankFragment4();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pret = 0;
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public int getScore(int a, int b, int c, int d, int e, int f) {
        int[] cntt = new int[10];
        cntt[1] = cntt[2] = cntt[3] = cntt[4] = cntt[5] = cntt[6] = 0;
        cntt[a]++;
        cntt[b]++;
        cntt[c]++;
        cntt[d]++;
        cntt[e]++;
        cntt[f]++;



        if (cntt[1] == 2 && cntt[4] == 4) {
            line.post(new Runnable() {
                @Override
                public void run() {
                    line.setText("状元插金花   +500德育");
                    line.setVisibility(View.VISIBLE);
                }
            });
            ((MainActivity) getActivity()).playNiu();
            return 500;
        } else if (cntt[4] == 6) {
            line.post(new Runnable() {
                @Override
                public void run() {
                    line.setText("六勃红   +500德育");
                    line.setVisibility(View.VISIBLE);
                }
            });
            ((MainActivity) getActivity()).playNiu();
            return 500;
        } else if (cntt[1] == 6) {
            line.post(new Runnable() {
                @Override
                public void run() {
                    line.setText("遍地锦   +500德育");
                    line.setVisibility(View.VISIBLE);
                }
            });
            ((MainActivity) getActivity()).playNiu();
            return 500;
        } else if (cntt[6] == 6) {
            line.post(new Runnable() {
                @Override
                public void run() {
                    line.setText("六勃黑   +500德育");
                    line.setVisibility(View.VISIBLE);
                }
            });
            ((MainActivity) getActivity()).playNiu();
            return 500;
        } else if (cntt[4] == 5) {
            line.post(new Runnable() {
                @Override
                public void run() {
                    line.setText("五红   +200德育");
                    line.setVisibility(View.VISIBLE);
                }
            });
            ((MainActivity) getActivity()).playNiu();
            return 200;
        } else if (cntt[6] == 5 && cntt[4] == 1) {
            line.post(new Runnable() {
                @Override
                public void run() {
                    line.setText("五子袋一秀   +200德育");
                    line.setVisibility(View.VISIBLE);
                }
            });
            ((MainActivity) getActivity()).playNiu();
            return 200;
        } else if (cntt[6] == 5) {
            line.post(new Runnable() {
                @Override
                public void run() {
                    line.setText("五子登科   +200德育");
                    line.setVisibility(View.VISIBLE);
                }
            });
            ((MainActivity) getActivity()).playNiu();
            return 200;
        } else if (cntt[4] == 4) {
            line.post(new Runnable() {
                @Override
                public void run() {
                    line.setText("状元   +200德育");
                    line.setVisibility(View.VISIBLE);
                }
            });
            ((MainActivity) getActivity()).playNiu();
            return 200;
        } else if (cntt[1] == 1 && cntt[2] == 1 && cntt[3] == 1 && cntt[4] == 1 && cntt[5] == 1 && cntt[6] == 1) {
            line.post(new Runnable() {
                @Override
                public void run() {
                    line.setText("对堂   +100德育");
                    line.setVisibility(View.VISIBLE);
                }
            });
            ((MainActivity) getActivity()).playNiu();
            return 100;
        } else if (cntt[4] == 3) {
            line.post(new Runnable() {
                @Override
                public void run() {
                    line.setText("三红   +80德育");
                    line.setVisibility(View.VISIBLE);
                }
            });
            ((MainActivity) getActivity()).playNiu();
            return 80;
        } else if (cntt[6] == 4) {
            line.post(new Runnable() {
                @Override
                public void run() {
                    line.setText("四进   +50德育");
                    line.setVisibility(View.VISIBLE);
                }
            });
            ((MainActivity) getActivity()).playNiu();
            return 50;
        } else if (cntt[4] == 2) {
            line.post(new Runnable() {
                @Override
                public void run() {
                    line.setText("二举   +20德育");
                    line.setVisibility(View.VISIBLE);
                }
            });
            ((MainActivity) getActivity()).playNiu();
            return 20;
        } else if (cntt[4] == 1) {
            line.post(new Runnable() {
                @Override
                public void run() {
                    line.setText("一秀   +10德育");
                    line.setVisibility(View.VISIBLE);
                }
            });
            ((MainActivity) getActivity()).playNiu();
            return 10;
        } else {
            ((MainActivity) getActivity()).playNa();
        }
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_blank4, container, false);

        sensorManager = (SensorManager) getActivity().getSystemService(SENSOR_SERVICE);
        vibrator = (Vibrator) getActivity().getSystemService(VIBRATOR_SERVICE);

        paiming = mView.findViewById(R.id.paiming);
        paiming.setVisibility(View.INVISIBLE);

        webView = mView.findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/bobing/index.html");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setBackgroundColor(0);
        webView.getBackground().setAlpha(0);

        Typeface typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/font1.otf");
        textView = mView.findViewById(R.id.textView2);
        textView.setTypeface(typeface);

        textView2 = mView.findViewById(R.id.textView5);
        textView2.setTypeface(typeface);

        textView1 = mView.findViewById(R.id.left);
        textView1.setTypeface(typeface);

        line = mView.findViewById(R.id.line);
        line.setVisibility(View.INVISIBLE);
        line.setTypeface(typeface);

        nickname = ((MainActivity) getActivity()).getNickname();

        paiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).playBtn();
                NavController navController = Navigation.findNavController(view);
                navController.navigate(R.id.action_blankFragment4_to_blankFragment5);
            }
        });


        return mView;
    }

    /**
     * 重力感应监听
     */
    private SensorEventListener sensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent event) {
            // 传感器信息改变时执行该方法
            float[] values = event.values;
            float x = values[0]; // x轴方向的重力加速度，向右为正
            float y = values[1]; // y轴方向的重力加速度，向前为正
            float z = values[2]; // z轴方向的重力加速度，向上为正
            Log.i(TAG, "x轴方向的重力加速度" + x +  "；y轴方向的重力加速度" + y +  "；z轴方向的重力加速度" + z);
            // 一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态。
            int medumValue = 19;// 三星 i9250怎么晃都不会超过20，没办法，只设置19了
            if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {
                vibrator.vibrate(200);
                Message msg = new Message();
                msg.what = SENSOR_SHAKE;
                handler.sendMessage(msg);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    @Override
    public void onResume() {
        super.onResume();

        if (sensorManager != null) {// 注册监听器
            sensorManager.registerListener(sensorEventListener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
            // 第一个参数是Listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (sensorManager != null) {// 取消监听器
            sensorManager.unregisterListener(sensorEventListener);
        }
    }

    /**
     * 动作执行
     */
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == SENSOR_SHAKE) {
                if (new Date().getTime() - pret >= 500) {
                    ((MainActivity) getActivity()).playDice();
                    Log.i(TAG, "检测到摇晃，执行操作！");
                    int a = Math.abs(new Random().nextInt()) % 6 + 1;
                    int b = Math.abs(new Random().nextInt()) % 6 + 1;
                    int c = Math.abs(new Random().nextInt()) % 6 + 1;
                    int d = Math.abs(new Random().nextInt()) % 6 + 1;
                    int e = Math.abs(new Random().nextInt()) % 6 + 1;
                    int f = Math.abs(new Random().nextInt()) % 6 + 1;


                    webView.loadUrl("javascript:begin(" + a + ", " + b + ", " + c + ", " + d + ", " + e + ", " + f + ")");

                    now--;
                    textView1.setText("剩余次数：" + now + "次");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                sleep(2000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            cnt += getScore(a, b, c, d, e, f);
                            textView.post(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText("获得德育：" + cnt + "分");
                                }
                            });
                            try {
                                sleep(2000);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                            line.post(new Runnable() {
                                @Override
                                public void run() {
                                    line.setVisibility(View.INVISIBLE);
                                }
                            });
//
                        }
                    }).start();

                    if (now <= 0) {
                        sensorManager.unregisterListener(sensorEventListener);

                        paiming.setVisibility(View.VISIBLE);

                        OkHttpClient client = new OkHttpClient.Builder().build();
//                            Request request = new Request.Builder().url("http://10.0.2.2:8080/Bobing_war_exploded/submit-rank?nickname=demo&score=2000&time=0.66666").build();
                        Request request = new Request.Builder().url("https://www.doubleq666.cn/bobing/submit-rank?nickname=" + nickname + "&score=" + cnt).build();

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Response response = client.newCall(request).execute();
                                    Log.d("OkHttp", response.body().string());
                                } catch (IOException e) {
                                    Log.d("miaomiao", "failed");
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                }
                pret = new Date().getTime();


            }
//            switch (msg.what) {
//                case SENSOR_SHAKE:
//                    Toast.makeText(getActivity().MainActivity.this, "检测到摇晃，执行操作！", Toast.LENGTH_SHORT).show();
//                    Log.i(TAG, "检测到摇晃，执行操作！");
//                    break;
//            }
        }

    };
}