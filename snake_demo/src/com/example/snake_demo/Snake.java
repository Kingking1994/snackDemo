package com.example.snake_demo;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Snake extends Activity {

	private Button btn;
	private static int btnClickTimes;
	
    private SnakeView mSnakeView;     
    private static String ICICLE_KEY = "snake-view";
    
    /**
     *第一次创建时调用onCreate方法
     * 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //layout相当于很多view的container,设置游戏的视图布局
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//设置无标题，就是隐藏标题栏
        setContentView(R.layout.snake_layout);
        
        btnClickTimes = 0;
        btn = (Button)findViewById(R.id.btn);
        btn.setText("music on");
        btn.setOnClickListener(new btnListener());

        //在layout中找到自己的视图.这一个是自己写的视图,非android自带的,同样需要加进布局文件中
        mSnakeView = (SnakeView) findViewById(R.id.snake);       
        mSnakeView.setTextView((TextView) findViewById(R.id.text));

        //主要是为了设置暂停.暂停后需要回复到之前的状态. bundle-绑定,一个map类型的对象
        if (savedInstanceState == null) {
            // 相当于一个新的游戏的开始
            mSnakeView.setMode(SnakeView.READY);
        } else {
            //暂停后的恢复
            Bundle map = savedInstanceState.getBundle(ICICLE_KEY);
            if (map != null) {
                mSnakeView.restoreState(map);
            } else {
                mSnakeView.setMode(SnakeView.PAUSE);
            }
        }
    }

   class btnListener implements OnClickListener{

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		btnClickTimes++;
		if (btnClickTimes%2 ==1) {
			mSnakeView.setSound(false);
			btn.setText("music off");
		}else {
			mSnakeView.setSound(true);
			btn.setText("music on");
		}
	}
	   
   }
    
    
    @Override
    protected void onPause() {
        super.onPause();
        // Pause the game along with the activity
        mSnakeView.setMode(SnakeView.PAUSE);
    }

    
    @Override
    public void onSaveInstanceState(Bundle outState) {
        //保存游戏状态
        outState.putBundle(ICICLE_KEY, mSnakeView.saveState());
    }
}