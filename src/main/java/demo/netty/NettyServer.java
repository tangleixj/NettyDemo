package demo.netty;

import demo.netty.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {
	private int port = 8080;
	private int backlog = 1024;

	public void start() {
		EventLoopGroup parentGroup = new NioEventLoopGroup();
		EventLoopGroup childGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap boot = new ServerBootstrap();
			boot.group(parentGroup, childGroup);
			boot.channel(NioServerSocketChannel.class);
			boot.option(ChannelOption.SO_BACKLOG, backlog);
			boot.childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel channel) throws Exception {
					// TODO Auto-generated method stub
					channel.pipeline().addLast(new ServerHandler());
				}
			});

			ChannelFuture future = boot.bind(port).sync();
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			parentGroup.shutdownGracefully();
			childGroup.shutdownGracefully();
		}
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getBacklog() {
		return backlog;
	}

	public void setBacklog(int backlog) {
		this.backlog = backlog;
	}

	public NettyServer(int port, int backlog) {
		super();
		this.port = port;
		this.backlog = backlog;
	}

	public NettyServer() {
		super();
	}
	public static void main(String[] args) {
		new NettyServer().start();
	}

}
