package top.content360.util;


import top.content360.conf.Constants;
import top.content360.menu.Button;
import top.content360.menu.ClickButton;
import top.content360.menu.Menu;
import top.content360.menu.ViewButton;

public class WeixinUtil {

	/**
	 * 初始化微信菜单
	 * @return
	 */
	public static Menu initMenu(){
		Menu menu = new Menu();
		
		ClickButton button1 = new ClickButton();
		button1.setName("翻日记");
		button1.setType("click");
		button1.setKey("11111");
		
		ViewButton button2 = new ViewButton();
		button2.setName("写日记");
		button2.setType("view");
		button2.setUrl("http://www.baidu.com");
		
		Button button3 = new Button();
		button3.setName("关于");
		button1.setKey("3333");
		
		ClickButton button3_1 = new ClickButton();
		button3_1.setName("联系我们");
		button3_1.setKey("3111");
		button3_1.setType("scancode_push");
		
		ClickButton button3_2 = new ClickButton();
		button3_2.setName("关于我们");
		button3_2.setKey("3111");
		button3_2.setType("scancode_push");
		
		button3.setSub_button(new Button[]{button3_1, button3_2});
		
		menu.setButton(new Button[]{button1, button2, button3});
		return menu;
	}
	
	public static int createMenu(String token, String menu){
		String url = Constants.CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		
		return 0;
	}
}
