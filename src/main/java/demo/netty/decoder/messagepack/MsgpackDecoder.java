package demo.netty.decoder.messagepack;

import java.util.List;

import org.msgpack.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buff, List<Object> list) throws Exception {
		// TODO Auto-generated method stub
		byte[] raw = new byte[buff.readableBytes()];
		buff.readBytes(raw);
		MessagePack tool =new MessagePack();
		list.add(tool.read(raw));
	}

}
