import java.net.InetAddress;
import java.net.UnknownHostException;

import org.communicast.net.Node;

import gnu.getopt.Getopt;

/**
 * 程序入口
 * 
 * @author huangkaixuan
 *
 */

public class Main {
	public final static String PROGRAM_NAME = "FlyMessager";
	
	public static void main(String[] args) {
		final boolean RUN_AS_SERVER;
		
		int nodes_to_connect = 3;// 连接节点数
		if (args.length > 0) {
			RUN_AS_SERVER = true;
			// 指定监听端口,地址运行服务器模式
			Getopt g = new Getopt(PROGRAM_NAME, args, "p:a::n::");
			int c;
			int bind_port = 0;

			InetAddress bind_addr = null;
			try {
				bind_addr = InetAddress.getByName("0.0.0.0");
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}

			boolean arg_invalid = false;
			while ((c = g.getopt()) != -1) {
				switch (c) {
				case 'p':
					// 监听端口
					bind_port = Integer.parseInt(g.getOptarg());
					if (bind_port < 0 || bind_port > 65535) {
						arg_invalid = true;
						System.err.println("Invalid port to listen");
					}

					break;
				case 'a':
					try {
						bind_addr = InetAddress.getByName(g.getOptarg());
					} catch (UnknownHostException e) {
						arg_invalid = true;
						System.err.println("Invalid address to bind");
					}
					break;

				case 'n':
					nodes_to_connect = Integer.parseInt(g.getOptarg());
					if (nodes_to_connect <= 0) {
						arg_invalid = true;
						System.err.println("Invalid connection num");
					}
					break;
				}
			}

			if (arg_invalid) {
				return;// 参数非法
			}

		} else {
			RUN_AS_SERVER = false;
		}
		
		
	}
	
	
	
}
