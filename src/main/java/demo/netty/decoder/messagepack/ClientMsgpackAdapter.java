package demo.netty.decoder.messagepack;

import java.util.ArrayList;
import java.util.List;

import demo.netty.decoder.AbstractDecoderAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ClientMsgpackAdapter extends AbstractDecoderAdapter {
	private int count;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("client recive res : " + msg + " count: " + (++count));
	}

	private List<DemoBean> buildBeans() {
		List<DemoBean> list = new ArrayList<DemoBean>();
		for (int i = 0; i < 20; i++) {
			list.add(new DemoBean("demo---->" + i, "male", i));
		}
		return list;
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		List<DemoBean> list = buildBeans();
//		for (DemoBean bean : list) {
//			ctx.write(bean);
//		}
//		ctx.flush();
		ctx.writeAndFlush(list);
	}

}
