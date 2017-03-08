package demo.netty.processor;

import demo.netty.constants.NettyConstants;
import demo.netty.decoder.AbstractDecoderAdapter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * 基于DelimiterBasedFrameDecoder编码器的处理机
 * 
 * @author tony
 *
 */
public class DBFDecoderProcessor extends AbstractNettyProcessor {
	private int maxFrameLength = 2048;

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		channel.pipeline().addLast(new DelimiterBasedFrameDecoder(maxFrameLength,
				Unpooled.copiedBuffer(NettyConstants.SPLIT_STR.getBytes())));
		channel.pipeline().addLast(new StringDecoder());
		channel.pipeline().addLast(adapter);
	}

	public int getMaxFrameLength() {
		return maxFrameLength;
	}

	public void setMaxFrameLength(int maxFrameLength) {
		this.maxFrameLength = maxFrameLength;
	}

	public DBFDecoderProcessor(AbstractDecoderAdapter adapter) {
		super(adapter);
	}

	public DBFDecoderProcessor() {
		super();
	}

}
