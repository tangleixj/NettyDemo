package demo.netty.decoder.flfdecoder;

import demo.netty.decoder.AbstractDecoderAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 基于FixedLengthFrameDecoder编码器的服务端通信适配器
 * @author tony
 *
 */
public class ServerFLFDecoderAdapter extends AbstractDecoderAdapter {

	/**
	 * 无论接受了多少的数据，FixedLengthFrameDecoder都会按照所配置的长度进行拆包-->TCP粘包/拆包问题
	 * StringDecoder会将字节数据转换成字符串 
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("server recive msg : " + msg);
	}

}
