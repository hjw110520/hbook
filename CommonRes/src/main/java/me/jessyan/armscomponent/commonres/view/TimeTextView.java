package me.jessyan.armscomponent.commonres.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Calendar;

/**
 * 实时显示时间的TextView
 * Created by hjw on 2017/9/28 0028.
 */

public class TimeTextView extends AppCompatTextView {
    private  TimeHandler mTimeHandler=new TimeHandler();

    public TimeTextView(Context context) {
        super(context);
        init(context);
    }

    public TimeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public TimeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    //初始化方法
    private void init(Context context) {
        try {
            //初始化textview显示时间
            updateClock();
            //更新进程开始
            new TimeThread().start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //更新子线程
    private class TimeThread extends Thread {
        @Override
        public void run() {
            mTimeHandler.startScheduleUpdate();
        }
    }

    public void stopClock(){
        if(null != mTimeHandler){
            mTimeHandler.stopScheduleUpdate();
        }
    }


    //重要的更新Handler
    private class   TimeHandler  extends Handler {
        private boolean mStopped;
        private void post(){
            sendMessageDelayed(obtainMessage(0),1000*(60- Calendar.getInstance().get(Calendar.SECOND)));
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (!mStopped){
                updateClock();
                post();
            }
        }
        //开始更新
        public void startScheduleUpdate(){
            mStopped = false;
            post();
        }
        //停止更新
        public void stopScheduleUpdate(){
            mStopped = true;
            removeMessages(0);
        }
    }

    private void updateClock() {
        //更新时间
        Calendar calendar= Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute=calendar.get(Calendar.MINUTE);
        StringBuilder sb = new StringBuilder();
        if(hour<10){
            sb.append("0");
        }
        sb.append(hour);
        sb.append(":");
        if(minute<10){
            sb.append("0");
        }
        sb.append(minute);
        setText(sb.toString());
    }


}
