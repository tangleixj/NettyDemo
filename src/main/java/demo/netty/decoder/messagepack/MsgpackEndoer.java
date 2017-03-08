package demo.netty.decoder.messagepack;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * messagepack 编码器
 * 
 * @author tony
 *
 */
public class MsgpackEndoer extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object obj, ByteBuf buff) throws Exception {
		MessagePack tool = new MessagePack();
		//Serizlize
		byte[] raw = tool.write(obj);//messagepack 将对象编码成byte数组
		buff.writeBytes(raw);//bytebuf将字节数组读入缓存区
	}
}
