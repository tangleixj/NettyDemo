package demo.netty.processor;

import demo.netty.decoder.AbstractDecoderAdapter;
import demo.netty.decoder.messagepack.MsgpackDecoder;
import demo.netty.decoder.messagepack.MsgpackEndoer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class MsgpackProcessor extends AbstractNettyProcessor {
	private int maxFrameLength = 65535;
	private int lengthFieldOffset = 0;
	private int lengthFieldLength = 2;

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		// TODO Auto-generated method stub
		channel.pipeline().addLast("frameDecoder",
				new LengthFieldBasedFrameDecoder(maxFrameLength, lengthFieldOffset, lengthFieldLength));// 
		channel.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
		channel.pipeline().addLast("frameEncoder", new LengthFieldPrepender(lengthFieldLength));// 在ByteBuf之前添加lengthFieldLength个字节来作为报文头
		channel.pipeline().addLast("msgpack encoder", new MsgpackEndoer());
		channel.pipeline().addLast(adapter);
	}

	public int getMaxFrameLength() {
		return maxFrameLength;
	}

	public void setMaxFrameLength(int maxFrameLength) {
		this.maxFrameLength = maxFrameLength;
	}

	public int getLengthFieldOffset() {
		return lengthFieldOffset;
	}

	public void setLengthFieldOffset(int lengthFieldOffset) {
		this.lengthFieldOffset = lengthFieldOffset;
	}

	public int getLengthFieldLength() {
		return lengthFieldLength;
	}

	public void setLengthFieldLength(int lengthFieldLength) {
		this.lengthFieldLength = lengthFieldLength;
	}

	public MsgpackProcessor(AbstractDecoderAdapter adapter, int maxFrameLength, int lengthFieldOffset,
			int lengthFieldLength) {
		super(adapter);
		this.maxFrameLength = maxFrameLength;
		this.lengthFieldOffset = lengthFieldOffset;
		this.lengthFieldLength = lengthFieldLength;
	}

	public MsgpackProcessor(AbstractDecoderAdapter adapter) {
		super(adapter);
	}

	public MsgpackProcessor() {
		super();
	}

}
