package top.content360.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.content360.enums.MsgType;
import top.content360.po.TextMessage;
import top.content360.util.CheckUtil;
import top.content360.util.Log;
import top.content360.util.MessageUtil;

/**
 * Servlet implementation class WeiXinServlet
 */
@WebServlet(name = "authorizationServlet", description = "the servlet for weixin first time authorization", urlPatterns = { "/weixin/authorization" })
public class AuthorizationServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AuthorizationServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
		
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter();
		
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			out.println(echostr);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		
		PrintWriter writer = response.getWriter();
		Map<String, String> messageMap = MessageUtil.xml2Map(request);
		String toUserName = messageMap.get("ToUserName");
		String fromUserName = messageMap.get("FromUserName");
		String msgType = messageMap.get("MsgType");
		String content = messageMap.get("Content");
		
		Log.log("msgType is : " + msgType);
		Log.log("the message content is : " + content);
		
		String message = null;
		if(MsgType.text.name().equals(msgType)){
			TextMessage textMessage = new TextMessage();
			textMessage.setFromUserName(toUserName);
			textMessage.setToUserName(fromUserName);
			textMessage.setMsgType(MsgType.text.name());
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setContent("the message from you is " + content);
			
			message = MessageUtil.textMessage2Xml(textMessage);
			
			writer.println(message);
			
			writer.close();
		}else if(MsgType.event.name().equals(msgType)){
			String event = messageMap.get("Event");
			switch (event) {
			case "subscribe":
				Log.log("fromUserName 关注了本号");
				break;
			case "unsubscribe":
				Log.log("fromUserName 取消关注本号");
				break;
				
			}
		}
	}

}
