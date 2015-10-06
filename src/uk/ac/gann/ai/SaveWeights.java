package uk.ac.gann.ai;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SaveWeights {

	public boolean saveWeightsToTxt(String name, double[] array)
			throws IOException {
		// Read the file first and then write it
		boolean flag = false;
		String filenameTemp = "D:/" + name + ".txt";
		String filein = "\r\n";

		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		FileOutputStream fos = null;
		PrintWriter pw = null;

		// Create file
		File filename = new File(filenameTemp);
		if (!filename.exists()) {
			filename.createNewFile();
		}

		try {
			// file path
			File file = new File(filenameTemp);
			// read file into inputStream
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			StringBuffer buf = new StringBuffer();

			for (int i = 0; i < array.length; i++) {
				filein = array[i] + "\r\n";
				buf.append(filein);
			}

			fos = new FileOutputStream(file);
			pw = new PrintWriter(fos);
			pw.write(buf.toString().toCharArray());
			pw.flush();
			flag = true;
		} catch (IOException e1) {
			throw e1;
		} finally {
			if (pw != null) {
				pw.close();
			}
			if (fos != null) {
				fos.close();
			}
			if (br != null) {
				br.close();
			}
			if (isr != null) {
				isr.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
		return flag;
	}
}
