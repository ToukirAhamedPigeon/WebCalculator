package com.example.touki.webview;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

public class WebAppInterface {

	String num1="";
	String num2="";
	Double result;
	String res;
	boolean opSet=false;
	String op;
	MainActivity wa=null;
    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        wa=(MainActivity)c;
    }
	@android.webkit.JavascriptInterface
    public void addNum(String num) {
    	
        if(opSet){
        	num2+=num;
        }
        else
        	num1+=num;
    	//Toast.makeText(wa, num, Toast.LENGTH_LONG).show();
        
    }
	@android.webkit.JavascriptInterface
    public void addOperator(String opr) {
    	op = opr;
    	opSet = true;
    	
    }
	@android.webkit.JavascriptInterface
    public String getResult(){
    	if(op.equals("+"))
    		result=Double.valueOf(num1)+Double.valueOf(num2);
    	else if(op.equals("-"))
    		result=Double.valueOf(num1)-Double.valueOf(num2);
    	else if(op.equals("*"))
    		result=Double.valueOf(num1)*Double.valueOf(num2);
    	else if(op.equals("/"))
    		result=Double.valueOf(num1)/Double.valueOf(num2);
    	
    	res=result.toString();
    	num1="";
    	num2="";
    	opSet=false;
    	return res;
    }
    
}

