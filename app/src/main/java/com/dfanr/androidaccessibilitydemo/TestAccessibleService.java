package com.dfanr.androidaccessibilitydemo;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;


public class TestAccessibleService extends AccessibilityService {
    private static final String TAG = GlobalConstant.LOG_TAG;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "onAccessibilityEvent");
        if(event.getEventType()==AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED){
            Log.d(TAG, "窗口内容变化");
            ComponentName componentName = new ComponentName(event.getPackageName().toString(), event.getClassName().toString());
            ActivityInfo activityInfo = tryGetActivity(componentName);
            boolean isActivity = activityInfo != null;
            if(isActivity){
                AccessibilityNodeInfo nodeInfo = event.getSource();
                if(nodeInfo!=null){
                    Log.d(TAG, "当前运行的包名："+nodeInfo.getPackageName());
                    Toast.makeText(this, nodeInfo.getPackageName(), Toast.LENGTH_SHORT).show();
                }
            }
        }
//        try{
//            AccessibilityNodeInfo rootNodeInfo = getRootInActiveWindow();
//            if(rootNodeInfo==null){
//                Log.e(TAG, "rootNodeInfo is null");
//                return;
//            }
//            List<AccessibilityNodeInfo> targetNodeInfoList = new ArrayList<>();
//            String[] targetTextArray = new String[]{
//                    "跳过",
//            };
//            for(String targetText:targetTextArray){
//                List<AccessibilityNodeInfo> nodeInfoList = rootNodeInfo.findAccessibilityNodeInfosByText("跳过");
//                if(nodeInfoList!=null && nodeInfoList.size()>0){
//                    targetNodeInfoList.addAll(nodeInfoList);
//                    for(AccessibilityNodeInfo nodeInfo:nodeInfoList){
//                        Log.d(TAG, "node:"+nodeInfo);
//                    }
//                }
//            }
//            Log.d(TAG, "找到"+targetNodeInfoList.size()+"个包含'跳过'的按钮");
//            for(AccessibilityNodeInfo nodeInfo:targetNodeInfoList){
//                Log.d(TAG, "模拟了一次点击事件");
//                nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
//            }
//        }catch (Exception e){
//            Log.e(TAG, "Exception:"+e.getMessage());
//            e.printStackTrace();
//        }
    }

    @Override
    public void onInterrupt() {
        Log.e(TAG, "onInterrupt");
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "onServiceConnected");
        AccessibilityServiceInfo config = new AccessibilityServiceInfo();
        //配置监听的事件类型为界面变化|点击事件
        config.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED | AccessibilityEvent.TYPE_VIEW_CLICKED;
        config.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        config.flags = AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS;
        setServiceInfo(config);
    }

    private ActivityInfo tryGetActivity(ComponentName componentName) {
        try {
            return getPackageManager().getActivityInfo(componentName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

}
