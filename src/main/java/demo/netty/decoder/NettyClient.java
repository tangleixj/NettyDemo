package demo.netty.decoder;

import demo.netty.constants.NettyConstants;
import demo.netty.decoder.lsdecoder.AbstractDecoderAdapter;
import demo.netty.decoder.lsdecoder.ClientDBFDecoderAdapter;
import demo.netty.decoder.lsdecoder.ClientLSDecoderAdapter;
import demo.netty.processor.AbstractNettyProcessor;
import demo.netty.processor.DBFDecoderProcessor;
import demo.netty.processor.LSDecoderProcessor;
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

/**
 * Netty客户端
 * 
 * 采用注入处理机的方式使得客户端更具有通用性
 * 
 * @author tony
 *
 */
public class NettyClient {
	private String host = "127.0.0.1";
	private int prot = 8080;
	private boolean TCP_MODELY = true;
	private String order = NettyConstants.ORDER_QUERY_TIME;
	private AbstractNettyProcessor processor;// 处理机

	public void connect() {
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			Bootstrap boot = new Bootstrap();
			boot.group(workGroup);
			boot.channel(NioSocketChannel.class);
			boot.option(ChannelOption.TCP_NODELAY, TCP_MODELY);
			boot.handler(processor);

			ChannelFuture future = boot.connect(host, prot).sync();// 发起异步连接操作
			future.channel().closeFuture().sync();// 等待链路关闭
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

	public AbstractNettyProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(AbstractNettyProcessor processor) {
		this.processor = processor;
	}

	public NettyClient(String host, int prot, boolean tCP_MODELY, String order, AbstractNettyProcessor processor) {
		super();
		this.host = host;
		this.prot = prot;
		TCP_MODELY = tCP_MODELY;
		this.order = order;
		this.processor = processor;
	}

	public NettyClient() {
		super();
	}

	public static void main(String[] args) {
//		AbstractDecoderAdapter adapter = new ClientLSDecoderAdapter();
		AbstractDecoderAdapter adapter = new ClientDBFDecoderAdapter();
//		AbstractNettyProcessor processor = new LSDecoderProcessor(adapter);
		AbstractNettyProcessor processor = new DBFDecoderProcessor(adapter);
		NettyClient client = new NettyClient();
		client.setProcessor(processor);
		client.connect();
	}
}
