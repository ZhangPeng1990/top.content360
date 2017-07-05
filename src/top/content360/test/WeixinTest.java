package top.content360.test;

import top.content360.po.AccessToken;
import top.content360.util.WeixinUtil;

public class WeixinTest {

	public static void main(String[] args) {
		AccessToken token = WeixinUtil.getAccessToken();
		
		token.getExpires_in();
	}
}
