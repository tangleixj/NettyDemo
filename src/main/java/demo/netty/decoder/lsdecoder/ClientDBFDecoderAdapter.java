package demo.netty.decoder.lsdecoder;

import demo.netty.constants.NettyConstants;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

public class ClientDBFDecoderAdapter extends AbstractDecoderAdapter {
	private String order = NettyConstants.ORDER_QUERY_TIME + NettyConstants.SPLIT_STR;
	private int count;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String res = (String) msg;
		System.out.println("client recive res : " + res + " count: " + (++count));
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 100; i++) {
			System.out.println("client send time: " + i);
			ctx.writeAndFlush(Unpooled.copiedBuffer(order.getBytes()));
		}
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public ClientDBFDecoderAdapter(String order) {
		super();
		this.order = order;
	}

	public ClientDBFDecoderAdapter() {
		super();
	}

}
