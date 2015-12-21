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
     *��һ�δ���ʱ����onCreate����
     * 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //layout�൱�ںܶ�view��container,������Ϸ����ͼ����
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//�����ޱ��⣬�������ر�����
        setContentView(R.layout.snake_layout);
        
        btnClickTimes = 0;
        btn = (Button)findViewById(R.id.btn);
        btn.setText("music on");
        btn.setOnClickListener(new btnListener());

        //��layout���ҵ��Լ�����ͼ.��һ�����Լ�д����ͼ,��android�Դ���,ͬ����Ҫ�ӽ������ļ���
        mSnakeView = (SnakeView) findViewById(R.id.snake);       
        mSnakeView.setTextView((TextView) findViewById(R.id.text));

        //��Ҫ��Ϊ��������ͣ.��ͣ����Ҫ�ظ���֮ǰ��״̬. bundle-��,һ��map���͵Ķ���
        if (savedInstanceState == null) {
            // �൱��һ���µ���Ϸ�Ŀ�ʼ
            mSnakeView.setMode(SnakeView.READY);
        } else {
            //��ͣ��Ļָ�
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
        //������Ϸ״̬
        outState.putBundle(ICICLE_KEY, mSnakeView.saveState());
    }
}