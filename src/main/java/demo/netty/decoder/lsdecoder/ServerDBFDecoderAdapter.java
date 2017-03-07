package demo.netty.decoder.lsdecoder;

import java.util.Date;

import demo.netty.constants.NettyConstants;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerDBFDecoderAdapter extends AbstractDecoderAdapter {
	private int count;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String req = (String) msg;
		System.out.println("server recive req : " + req + " count: " + (++count));
		String currentTime = NettyConstants.ORDER_QUERY_TIME.equals(req)
				? new Date(System.currentTimeMillis()).toString() : NettyConstants.ORDER_BAD;
		ctx.write(Unpooled.copiedBuffer((currentTime + NettyConstants.SPLIT_STR).getBytes()));
	}
}
