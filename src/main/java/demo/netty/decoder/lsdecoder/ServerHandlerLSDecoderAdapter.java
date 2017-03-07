package demo.netty.decoder.lsdecoder;

import java.util.Date;

import demo.netty.constants.NettyConstants;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 服务端通信适配器
 * 
 * @author tony
 *
 */
public class ServerHandlerLSDecoderAdapter extends AbstractDecoderAdapter {
	private int count;

	/**
	 * LinedBasedFrameDecoder会针对数据中的回车换行符进行拆包  -->解决粘包/拆包问题
	 * StringDecoder会直接讲字节流数据转换成字符串 -->方便读操作
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String req = (String) msg;
		System.out.println("server recive count : " + (++count) + " order : " + req);
		String res = NettyConstants.ORDER_QUERY_TIME.equals(req) ? new Date(System.currentTimeMillis()).toString()
				: NettyConstants.ORDER_BAD;
		ctx.write(Unpooled.copiedBuffer((res + "\n").getBytes()));
	}
}
