package demo.netty.processor;

import demo.netty.decoder.AbstractDecoderAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * 基于FixedLengthFrameDecoder编码器的处理机
 * 
 * @author tony
 *
 */
public class FLFDecoderProcessor extends AbstractNettyProcessor {
	private int frameLength = 10;

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		// TODO Auto-generated method stub
		channel.pipeline().addLast(new FixedLengthFrameDecoder(frameLength));
		channel.pipeline().addLast(new StringDecoder());
		channel.pipeline().addLast(adapter);
	}

	public int getFrameLength() {
		return frameLength;
	}

	public void setFrameLength(int frameLength) {
		this.frameLength = frameLength;
	}

	public FLFDecoderProcessor(AbstractDecoderAdapter adapter, int frameLength) {
		super(adapter);
		this.frameLength = frameLength;
	}

	public FLFDecoderProcessor(AbstractDecoderAdapter adapter) {
		super(adapter);
	}

	public FLFDecoderProcessor() {
		super();
	}

}
