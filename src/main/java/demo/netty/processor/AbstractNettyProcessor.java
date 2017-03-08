package demo.netty.processor;

import demo.netty.decoder.AbstractDecoderAdapter;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * 处理机抽象类
 * 
 * @author tony
 *
 */
public abstract class AbstractNettyProcessor extends ChannelInitializer<SocketChannel> {
	protected AbstractDecoderAdapter adapter;// 通信适配器

	@Override
	protected abstract void initChannel(SocketChannel channel) throws Exception;

	public AbstractDecoderAdapter getAdapter() {
		return adapter;
	}

	public void setAdapter(AbstractDecoderAdapter adapter) {
		this.adapter = adapter;
	}

	public AbstractNettyProcessor(AbstractDecoderAdapter adapter) {
		super();
		this.adapter = adapter;
	}

	public AbstractNettyProcessor() {
		super();
	}

}
