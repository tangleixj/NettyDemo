package demo.netty.decoder;

import demo.netty.decoder.flfdecoder.ServerFLFDecoderAdapter;
import demo.netty.decoder.messagepack.ServerMsgpackAdapter;
import demo.netty.processor.AbstractNettyProcessor;
import demo.netty.processor.FLFDecoderProcessor;
import demo.netty.processor.MsgpackProcessor;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty服务端
 * 采用注入处理机的方式使得客户端更具有通用性
 * @author tony
 *
 */
public class NettyServer {
	private int port = 8080;
	private int backlog = 1024;
	private AbstractNettyProcessor processor;//处理机

	public void start() {
		EventLoopGroup parentGroup = new NioEventLoopGroup();
		EventLoopGroup childGroup = new NioEventLoopGroup();

		try {
			ServerBootstrap boot = new ServerBootstrap();
			boot.group(parentGroup, childGroup);
			boot.channel(NioServerSocketChannel.class);
			boot.option(ChannelOption.SO_BACKLOG, backlog);
			boot.childHandler(processor);

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

	public AbstractNettyProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(AbstractNettyProcessor processor) {
		this.processor = processor;
	}

	public static void main(String[] args) {
//		AbstractDecoderAdapter adapter = new ServerLSDecoderAdapter();
//		AbstractDecoderAdapter adapter = new ServerDBFDecoderAdapter();
//		AbstractDecoderAdapter adapter = new ServerFLFDecoderAdapter();
		AbstractDecoderAdapter adapter = new ServerMsgpackAdapter();
//		AbstractNettyProcessor processor = new LSDecoderProcessor(adapter);
//		AbstractNettyProcessor processor = new DBFDecoderProcessor(adapter);
//		AbstractNettyProcessor processor = new FLFDecoderProcessor(adapter);
		AbstractNettyProcessor processor = new MsgpackProcessor(adapter);
		NettyServer server = new NettyServer();
		server.setProcessor(processor);
		server.start();
	}
}
