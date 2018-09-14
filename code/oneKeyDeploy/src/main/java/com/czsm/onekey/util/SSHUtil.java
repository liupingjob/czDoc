package com.czsm.onekey.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

/**
 *
 * @author 刘平 Mac 20180911
 *
 */
public class SSHUtil {
	private final static String user = "root";
	private final static String pass = "admin123";
	private final static String host = "192.168.1.110";

	public static void sshCommand(String temp) {

		try {
			Connection conn = new Connection(host);
			conn.connect();
			boolean isAuthenticated = conn.authenticateWithPassword(user, pass);
			if (isAuthenticated == false) {
				throw new IOException("Authentication failed");
			}
			Session sess = conn.openSession();
			sess.requestPTY("bash");
			sess.startShell();
			InputStream stdout = new StreamGobbler(sess.getStdout());
			InputStream stderr = new StreamGobbler(sess.getStderr());
			@SuppressWarnings("resource")
			BufferedReader stdoutReader = new BufferedReader(new InputStreamReader(stdout));
			@SuppressWarnings("resource")
			BufferedReader stderrReader = new BufferedReader(new InputStreamReader(stderr));
			@SuppressWarnings("unused")
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(sess.getStdin());
			while (!temp.equals("exit")) {
				System.out.print("[root@vmone ~]#");
				out.println(temp);
				out.flush();
				String line = null;
				while ((line = stdoutReader.readLine()) != null) {
					if (line.length() == 0) {// line等于null从来不会发生，导致程序卡在这里
						continue;
					} else {
						System.out.println(line);
					}
				}
				System.out.println("Here is the output from stderr:");
				while (true) {
					line = stderrReader.readLine();
					if (line == null)
						break;
					System.out.println(line);
				}
			}
			System.out.println("ExitCode: " + sess.getExitStatus());
			sess.close();
			conn.close();
			System.out.println("close connection");
		} catch (IOException e) {
			e.printStackTrace(System.err);

		}
		System.exit(2);
	}
}
