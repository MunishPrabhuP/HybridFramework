package com.newera.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Utilities {
	public static String readTextFile(File file) throws IOException {
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		StringBuffer sb = new StringBuffer("");
		String data;

		try {
			while ((data = br.readLine()) != null) {
				sb.append(data);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		br.close();
		fr.close();
		return sb.toString();
	}
}
