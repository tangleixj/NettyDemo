package demo.netty.processor;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * 基于LineBasedFrameDecoder和StringDecoder的处理机
 * 
 * @author tony
 *
 */
public class LSDecoderProcessor extends AbstractNettyProcessor {
	private int maxLength = 1024;

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		// TODO Auto-generated method stub
		channel.pipeline().addLast(new LineBasedFrameDecoder(maxLength));// 启用LineBasedFrameDecoder编码器
		channel.pipeline().addLast(new StringDecoder());// 启用StringDecoder编码器
		channel.pipeline().addLast(adapter);
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public LSDecoderProcessor(ChannelHandlerAdapter adapter, int maxLength) {
		super(adapter);
		this.maxLength = maxLength;
	}

	public LSDecoderProcessor(ChannelHandlerAdapter adapter) {
		super(adapter);
	}

	public LSDecoderProcessor() {
		super();
	}

}
