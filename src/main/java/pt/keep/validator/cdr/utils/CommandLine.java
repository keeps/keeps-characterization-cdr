/* 
 * Copyright 2009 Harvard University Library
 * 
 * This file is part of FITS (File Information Tool Set).
 * 
 * FITS is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * FITS is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with FITS.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.keep.validator.cdr.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 *  A static class for command line invocation.
 */
public abstract class CommandLine {
	public static String exec(List<String> cmd, String directory) throws IOException {
		String output = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			//Runtime rt = Runtime.getRuntime();
			//Process proc = rt.exec(cmd.toString());
			
			ProcessBuilder builder = new ProcessBuilder(cmd);
			if(directory != null) {
				builder.directory(new File(directory));
			}
			Process proc = builder.start();
			/*
			StringBuffer sb = new StringBuffer();
		    BufferedReader bro = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
		    String line;
		    while ((line = bro.readLine()) != null) {
		    	sb.append(line);
		    	System.out.println(line);
		    }

		    // The process should be done now, but wait to be sure.
		    proc.waitFor();
			*/
			
			StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(),bos);
			StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(),bos);
			errorGobbler.start();
			outputGobbler.start();			
			proc.waitFor();
		    errorGobbler.join();
		    outputGobbler.join();
		    //output = sb.toString();
		    bos.flush();
			output = new String(bos.toByteArray());			
		}
		catch (Exception e) {
			throw new IOException("Error calling external command line routine",e);
		} 
		finally {
			try {
				bos.close();
			} catch (IOException e) {
				throw new IOException("Error closing external command line output stream",e);
			}
		}
		return output;
	}
/*
	public static void exec(String cmd, OutputStream output) throws Exception {
		//Runtime rt = Runtime.getRuntime();
		//Process proc = rt.exec(cmd.toString());
		ProcessBuilder builder = new ProcessBuilder(cmd);
		Process proc = builder.start();
		StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(),"ERROR", output);
		StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(),"OUTPUT", output);
		errorGobbler.start();
		outputGobbler.start();
		proc.waitFor();
	}
	*/
}