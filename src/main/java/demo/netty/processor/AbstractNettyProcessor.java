package demo.netty.processor;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * 处理机抽象类
 * @author tony
 *
 */
public class AbstractNettyProcessor extends ChannelInitializer<SocketChannel> {
	protected ChannelHandlerAdapter adapter;//通信适配器

	@Override
	protected void initChannel(SocketChannel channel) throws Exception {
		channel.pipeline().addLast(adapter);
	}

	public ChannelHandlerAdapter getAdapter() {
		return adapter;
	}

	public void setAdapter(ChannelHandlerAdapter adapter) {
		this.adapter = adapter;
	}

	public AbstractNettyProcessor(ChannelHandlerAdapter adapter) {
		super();
		this.adapter = adapter;
	}

	public AbstractNettyProcessor() {
		super();
	}

}
