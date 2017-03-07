package demo.netty.decoder;

import demo.netty.constants.NettyConstants;
import demo.netty.decoder.lsdecoder.ClientHandlerLSDecoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class NettyClient {
	private String host = "127.0.0.1";
	private int prot = 8080;
	private boolean TCP_MODELY = true;
	private String order = NettyConstants.ORDER_QUERY_TIME;
	private ChannelHandlerAdapter handler;

	public void connect() {
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			Bootstrap boot = new Bootstrap();
			boot.group(workGroup);
			boot.channel(NioSocketChannel.class);
			boot.option(ChannelOption.TCP_NODELAY, TCP_MODELY);
			boot.handler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel channel) throws Exception {
					// TODO Auto-generated method stub
					channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
					channel.pipeline().addLast(new StringDecoder());
					channel.pipeline().addLast(handler);
				}
			});

			ChannelFuture future = boot.connect(host, prot).sync();
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			// TODO: handle exception
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

	public int getProt() {
		return prot;
	}

	public void setProt(int prot) {
		this.prot = prot;
	}

	public boolean isTCP_MODELY() {
		return TCP_MODELY;
	}

	public void setTCP_MODELY(boolean tCP_MODELY) {
		TCP_MODELY = tCP_MODELY;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public ChannelHandlerAdapter getHandler() {
		return handler;
	}

	public void setHandler(ChannelHandlerAdapter handler) {
		this.handler = handler;
	}

	public NettyClient(String host, int prot, boolean tCP_MODELY, String order, ChannelHandlerAdapter handler) {
		super();
		this.host = host;
		this.prot = prot;
		TCP_MODELY = tCP_MODELY;
		this.order = order;
		this.handler = handler;
	}

	public NettyClient() {
		super();
	}
	public static void main(String[] args) {
		ChannelHandlerAdapter adapter = new ClientHandlerLSDecoder();
		NettyClient client = new NettyClient();
		client.setHandler(adapter);
		client.connect();
	}
}
