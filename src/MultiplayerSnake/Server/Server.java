package MultiplayerSnake.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server{

	private List<ClientServer> clientServerList = new ArrayList<>();
	private int numOfClient;
	private int index = 0;
	
	public Server() {
		this.clientServerList = new ArrayList<ClientServer>();
	}
	
	public void initServer() {
		try {
			ServerSocket serverSocket = new ServerSocket(7777);
			System.out.println("启动服务器.....");
			System.out.println("等待客户端进入.......");
			while(true) {
				Socket socket = serverSocket.accept();
				ClientServer cs = new ClientServer(socket , index);
				index ++;
				clientServerList.add(cs);
				numOfClient = clientServerList.size();
				if(numOfClient == 1 || numOfClient == 2) {
					System.out.println("玩家" + numOfClient + "连接测试成功♪");
				}
				
				Thread t = new Thread(cs);
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Server server = new Server();
		server.initServer();
	}
	
	class ClientServer implements Runnable{
		Socket socket;
		BufferedReader bin;
		PrintWriter pout;
		int index;
		
		public ClientServer(Socket socket , int index) {
			this.socket = socket;
			this.index = index;
		}

		@Override
		public void run() {
				try {
					bin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					pout = new PrintWriter(socket.getOutputStream() , true);
					pout.println(numOfClient);
					bin.close();
					pout.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
