package demo.netty.decoder.messagepack;

import java.util.List;

import demo.netty.decoder.AbstractDecoderAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerMsgpackAdapter extends AbstractDecoderAdapter {
	private int count;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		List<DemoBean> list = (List<DemoBean>)msg;
		System.out.println("server recive mess: " + list + " count: " + (++count));
	}

}
