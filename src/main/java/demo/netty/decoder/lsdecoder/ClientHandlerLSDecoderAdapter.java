package demo.netty.decoder.lsdecoder;

import demo.netty.constants.NettyConstants;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 客户端通信适配器
 * 
 * @author tony
 *
 */
public class ClientHandlerLSDecoderAdapter extends ChannelHandlerAdapter {
	private String order = NettyConstants.ORDER_QUERY_TIME + "\n";

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for (int i = 0; i < 100; i++) {
			System.out.println("client send time : " + i);
			ctx.writeAndFlush(Unpooled.copiedBuffer(order.getBytes()));
		}
	}

	/**
	 * LinedBasedFrameDecoder会针对数据中的回车换行符进行拆包  -->解决粘包/拆包问题
	 * StringDecoder会直接讲字节流数据转换成字符串 -->方便读操作
	 */
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

	public ClientHandlerLSDecoderAdapter(String order) {
		super();
		this.order = order;
	}

	public ClientHandlerLSDecoderAdapter() {
		super();
	}

}
