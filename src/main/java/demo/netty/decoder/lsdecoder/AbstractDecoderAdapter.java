package demo.netty.decoder.lsdecoder;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public abstract class AbstractDecoderAdapter extends ChannelHandlerAdapter {
	@Override
	public abstract void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception;

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
