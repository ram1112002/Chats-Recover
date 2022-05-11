package com.example.chatsrecover;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;



import java.util.ArrayList;
import java.util.Locale;

public class MyAccessibilityService extends AccessibilityService {

    ArrayList<String> arrayListWhatsapp = new ArrayList<>();
    ArrayList<String> arrayListInsta = new ArrayList<>();
    String p;
    String nameApp;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        p = event.getPackageName().toString();


        int eventType = event.getEventType();

        SharedPreferences.Editor editor = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();
        SharedPreferences.Editor editor2 = getSharedPreferences("MyPrefs", MODE_PRIVATE).edit();

        switch (eventType) {

            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:

                AccessibilityNodeInfo rootNode = getRootInActiveWindow();
                ArrayList<AccessibilityNodeInfo> textViewNodes = new ArrayList<AccessibilityNodeInfo>();

                printeverythingWhatsapp(rootNode,0);
                printeverythingInstagram(rootNode,0);



                for(AccessibilityNodeInfo mNode : textViewNodes){
                    if(mNode.getText()==null){
                        return;
                    }
                    String tv1Text = mNode.getText().toString();


                    //do whatever you want with the text content...

                }
                break;

        }
        editor.putString("Name", String.valueOf(arrayListWhatsapp));
        editor2.putString("name",String.valueOf(arrayListInsta));
        editor2.apply();
        editor.apply();

    }

    public void printeverythingWhatsapp(AccessibilityNodeInfo node, int depth) {
        if(p.equals("com.whatsapp")) {

            String print = "";
            String text = "";
            PackageManager pm = this.getPackageManager();


            if (node == null) {

                return;

            }


            for (int i = 0; i < depth; i++) print += "\t";
            String name = node.getViewIdResourceName();
            print += "name:" + name;
            print += " ";
            if (node.getText() != null) {
                text = (String) node.getText().toString();
            }
            print += "text:" + text;

            Log.i("TESTREQ", print);

            arrayListWhatsapp.add(print + "\n");

            for (int j = 0; j < node.getChildCount(); j++) {


                printeverythingWhatsapp(node.getChild(j), depth + 1);

            }

        }
    }
    public void printeverythingInstagram(AccessibilityNodeInfo node, int depth) {
        if(p.equals("com.instagram.android")) {

            String print = "";
            String text = "";
            PackageManager pm = this.getPackageManager();


            if (node == null) {

                return;

            }


            for (int i = 0; i < depth; i++) print += "\t";
            String name = node.getViewIdResourceName();
            print += "name:" + name;
            print += " ";
            if (node.getText() != null) {
                text = (String) node.getText().toString();
            }
            print += "text:" + text;

            Log.i("TESTREQ", print);

            arrayListInsta.add(print + "\n");

            for (int j = 0; j < node.getChildCount(); j++) {


                printeverythingWhatsapp(node.getChild(j), depth + 1);

            }

        }
    }


    @Override
    public void onInterrupt() {

    }
}
