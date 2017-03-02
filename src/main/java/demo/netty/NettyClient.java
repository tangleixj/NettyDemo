package demo.netty;

import demo.netty.handler.ClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
	private String host = "127.0.0.1";
	private int port = 8080;
	private boolean tcp_modely = true;

	public void connect() {
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			Bootstrap boot = new Bootstrap();
			boot.group(workGroup);
			boot.channel(NioSocketChannel.class);
			boot.option(ChannelOption.TCP_NODELAY, tcp_modely);
			boot.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel channel) throws Exception {
					// TODO Auto-generated method stub
					channel.pipeline().addLast(new ClientHandler());
				}
			});

			ChannelFuture future = boot.connect(host, port).sync();
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workGroup.shutdownGracefully();
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isTcp_modely() {
		return tcp_modely;
	}

	public void setTcp_modely(boolean tcp_modely) {
		this.tcp_modely = tcp_modely;
	}

	public NettyClient(String host, int port, boolean tcp_modely) {
		super();
		this.host = host;
		this.port = port;
		this.tcp_modely = tcp_modely;
	}

	public NettyClient() {
		super();
	}
	public static void main(String[] args) {
		new NettyClient().connect();
	}
}
