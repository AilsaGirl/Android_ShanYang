package com.liaocheng.suteng.myapplication.ui.sever;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;


import com.liaocheng.suteng.myapplication.R;
import com.liaocheng.suteng.myapplication.ui.home.jiedan.JieDanActivity;
import com.liaocheng.suteng.myapplication.util.Util;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JIGUANG-Example";

	@SuppressLint("NewApi")
	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Bundle bundle = intent.getExtras();
			Logger.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));
//			Util.showNotifictionIcons(context,"哈哈哈哈");
//			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//			try {
//				JSONObject jsonObject = new JSONObject(extras);
////            int id = jsonObject.getInt("id");
//				String orderType = jsonObject.optString("orderType");
//				if (!TextUtils.isEmpty(orderType)){
//					Util.playSound(context,orderType);
//				}
//
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//
//			String contents = bundle.getString(JPushInterface.EXTRA_MESSAGE);

			if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
				String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
				String content = bundle.getString(JPushInterface.EXTRA_MESSAGE);
				String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);

				Logger.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
				//send the Registration Id to your server...

			}
			else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
				processCustomMessage(context, bundle);

			}
			else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知");
				int notifactionId = bundle.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
				Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
//				Util.playSound(context, "1");
//				Util.showNotifictionIcons(context, "哈哈哈哈哈哈","1");
				String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
				try {
					JSONObject jsonObject = new JSONObject(extras);
//            int id = jsonObject.getInt("id");
					if (null != jsonObject && jsonObject.length() > 0) {
						String sound = jsonObject.getString("sound");
						if ("newOrder.mp3".equals(sound)) {
//                        notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" +R.raw.yangjiao));
							Log.d("dddww6", "------------*****");
							Util.playSound(context, "1");
//							mMediaPlayer = MediaPlayer.create(context, R.raw.yangjiao);
						} else if ("robbedOrder.mp3".equals(sound)) {
//                        notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" +R.raw.yipeiqiang));
//							mMediaPlayer = MediaPlayer.create(context, R.raw.yipeiqiang);
							Util.playSound(context, "2");
						} else if ("overOrder.mp3".equals(sound)) {
//                        notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" +R.raw.dingdanwancheng));
//							mMediaPlayer = MediaPlayer.create(context, R.raw.dingdanwancheng);
							Util.playSound(context, "3");
						} else if ("revokeOrder.mp3".equals(sound)) {
//                        notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" +R.raw.chexiaodingdan));
//							mMediaPlayer = MediaPlayer.create(context, R.raw.chexiaodingdan);
							Util.playSound(context, "4");
						} else if ("sendUodoOrder.mp3".equals(sound)) {
//							mMediaPlayer = MediaPlayer.create(context, R.raw.shangjiachexiao);
							Util.playSound(context, "5");
						} else if ("newTransferOrder.mp3".equals(sound)) {
//							mMediaPlayer = MediaPlayer.create(context, R.raw.zhuandandingdan);
							Util.playSound(context, "6");
						} else if ("newSpecifyOrder.mp3".equals(sound)) {
//							mMediaPlayer = MediaPlayer.create(context, R.raw.zhiding);
							Util.playSound(context, "7");
						}
//						String orderType = jsonObject.optString("orderType");
//						if (!TextUtils.isEmpty(orderType)) {
//							Util.playSound(context, orderType);
//						}
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}


			} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 用户点击打开了通知");

				//打开自定义的Activity
				Intent i = new Intent(context, JieDanActivity.class);
//				i.putExtras(bundle);
				//i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
				context.startActivity(i);

			} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
				//在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

			} else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
				boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
				Logger.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
			} else {
				Logger.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
			}
		} catch (Exception e){

		}

	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			}else if(key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)){
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
					Logger.i(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it =  json.keys();

					while (it.hasNext()) {
						String myKey = it.next();
						sb.append("\nkey:" + key + ", value: [" +
								myKey + " - " +json.optString(myKey) + "]");
					}
				} catch (JSONException e) {
					Logger.e(TAG, "Get message extra JSON error!");
				}

			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.get(key));
			}
		}
		return sb.toString();
	}
	
	//send msg to MainActivity
	private void processCustomMessage(Context context, Bundle bundle) {
//		if (MainActivity.isForeground) {
//			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//			if (!ExampleUtil.isEmpty(extras)) {
//				try {
//					JSONObject extraJson = new JSONObject(extras);
//					if (extraJson.length() > 0) {
//						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//					}
//				} catch (JSONException e) {
//
//				}
//
//			}
//			LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
//		}
	}
}
