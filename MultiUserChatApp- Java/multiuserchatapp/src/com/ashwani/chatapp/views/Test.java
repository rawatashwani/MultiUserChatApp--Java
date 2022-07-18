package com.brainmentors.chatapp.views;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Test {
public static void main(String[] args) throws UnknownHostException {
//	InetAddress ad = InetAddress.getLocalHost();
//	System.out.println(ad);
	InetAddress add [] = InetAddress.getAllByName("Amits-Mac.local");
	for(InetAddress a : add) {
		System.out.println(a);
	}
	
}

}
