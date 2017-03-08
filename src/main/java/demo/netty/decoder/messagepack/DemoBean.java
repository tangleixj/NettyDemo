package demo.netty.decoder.messagepack;

import org.msgpack.annotation.Message;

/**
 * 利用MessagePack进行序列化和反序列化的JavaBean
 * 当使用MessagePack进行序列化和反序列化时，需要用@Message进行修饰
 * @author tony
 *
 */
@Message
public class DemoBean {
	private String name;
	private String sex;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public DemoBean(String name, String sex, int age) {
		super();
		this.name = name;
		this.sex = sex;
		this.age = age;
	}

	public DemoBean() {
		super();
	}

	@Override
	public String toString() {
		return "DemoBean [name=" + name + ", sex=" + sex + ", age=" + age + "]";
	}

}
