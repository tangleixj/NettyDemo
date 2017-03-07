package demo.netty.handler;

import demo.netty.constants.NettyConstants;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends ChannelHandlerAdapter {
	private String order = NettyConstants.ORDER_QUERY_TIME;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 100; i++) {
			System.out.println("client send time : " + i);
			ctx.writeAndFlush(Unpooled.copiedBuffer(order.getBytes()));
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buff = (ByteBuf) msg;
		byte[] res = new byte[buff.readableBytes()];
		buff.readBytes(res);
		String resMess = new String(res, NettyConstants.ENCODING);
		System.out.println("client recive res mess : " + resMess);
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

	public ClientHandler(String order) {
		super();
		this.order = order;
	}

	public ClientHandler() {
		super();
	}

}
