
    
package idv.david.websocketchat.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import idv.david.websocketchat.jedis.JedisHandleMessage;
import idv.david.websocketchat.model.ChatMessage;
import idv.david.websocketchat.model.State;

@ServerEndpoint("/FriendWS/{userName}")
public class FriendWS {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>();
	Gson gson = new Gson();
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		/* save the new user in the map */
		System.out.println("請進");
		userSession.setMaxTextMessageBufferSize(200000);
		sessionsMap.put(userName, userSession);
		System.out.println("sessionsMap="+sessionsMap);
		/* Sends all the connected users to the new user */
		Set<String> userNames = sessionsMap.keySet();
		System.out.println(userNames);
		State stateMessage = new State("open", userName, userNames);
		String stateMessageJson = gson.toJson(stateMessage);
		Collection<Session> sessions = sessionsMap.values();
		for (Session session : sessions) {
			System.out.println(sessions);
			if(session.isOpen()) {
				session.getAsyncRemote().sendText(stateMessageJson);
				System.out.println(stateMessageJson);
			}
		}

		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),userName, userNames);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
		System.out.println("有觸發");
		
          if ("history".equals(chatMessage.getType())) {
			System.out.println("拿紀錄");
			List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);// get the old info from redis
			System.out.println(historyData);

			if (userSession != null && userSession.isOpen()) {
				userSession.getAsyncRemote().sendText(historyData.toString());
System.out.println("有回傳");
				return;
			}
		}
		
	
if ("chat".equals(chatMessage.getType())) {
	System.out.println("有進來chat");
	JSONArray array = new JSONArray();
	System.out.println(array);
	JedisHandleMessage.saveChatMessage(sender, receiver, message);
  System.out.println("已存取");
	// send to session which receiver belongs to
	Session receiverSession = sessionsMap.get(receiver);
	Session senderSession = sessionsMap.get(sender);
	array.put(new JSONObject(message));
	if (senderSession != null && senderSession.isOpen()) {
		
		senderSession.getAsyncRemote().sendText(array.toString());

	}

	if (receiverSession != null && receiverSession.isOpen()) {
		

		receiverSession.getAsyncRemote().sendText(array.toString());

	}
}

// save in redis no need to save history
}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				System.out.println("到此一遊2");
				break;
			}
		}

		if (userNameClose != null) {
			State stateMessage = new State("close", userNameClose, userNames);
			String stateMessageJson = gson.toJson(stateMessage);
			Collection<Session> sessions = sessionsMap.values();
			for (Session session : sessions) {
				session.getAsyncRemote().sendText(stateMessageJson);
			}
		}

		String text = String.format("session ID = %s, disconnected; close code = %d%nusers: %s", userSession.getId(),
				reason.getCloseCode().getCode(), userNames);
		System.out.println(text);
	}
}

