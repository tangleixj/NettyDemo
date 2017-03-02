package demo.netty.handler;

import java.util.Date;

import demo.netty.constants.NettyConstants;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf buff = (ByteBuf) msg;
		byte[] req = new byte[buff.readableBytes()];
		buff.readBytes(req);
		String order = new String(req, NettyConstants.ENCODING);
		System.out.println("server recive order : " + order);

		String currentTime = NettyConstants.ORDER_QUERY_TIME.equals(order)
				? new Date(System.currentTimeMillis()).toString() : NettyConstants.ORDER_BAD;
		ctx.write(Unpooled.copiedBuffer(currentTime.getBytes()));
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}
}
