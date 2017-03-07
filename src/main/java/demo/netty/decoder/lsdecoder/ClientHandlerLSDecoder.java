package demo.netty.decoder.lsdecoder;

import demo.netty.constants.NettyConstants;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 
 * @author tony
 *
 */
public class ClientHandlerLSDecoder extends ChannelHandlerAdapter {
	private String order = NettyConstants.ORDER_QUERY_TIME + "\n";

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 100; i++) {
			System.out.println("client send time : " + i);
			ctx.writeAndFlush(Unpooled.copiedBuffer(order.getBytes()));
		}
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String res = (String) msg;
		System.out.println("client recive res mess: " + res);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public ClientHandlerLSDecoder(String order) {
		super();
		this.order = order;
	}

	public ClientHandlerLSDecoder() {
		super();
	}

}
