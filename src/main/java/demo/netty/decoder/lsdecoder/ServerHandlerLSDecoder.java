package demo.netty.decoder.lsdecoder;

import java.util.Date;

import demo.netty.constants.NettyConstants;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandlerLSDecoder extends ChannelHandlerAdapter {
	private int count;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String req = (String) msg;
		System.out.println("server recive count : " + (++count) + " order : " + req);
		String res = NettyConstants.ORDER_QUERY_TIME.equals(req) ? new Date(System.currentTimeMillis()).toString()
				: NettyConstants.ORDER_BAD;
		ctx.write(Unpooled.copiedBuffer((res + "\n").getBytes()));
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
