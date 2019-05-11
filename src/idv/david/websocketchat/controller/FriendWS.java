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
		sessionsMap.put(userName, userSession);
		/* Sends all the connected users to the new user */
		Set<String> userNames = sessionsMap.keySet();
		State stateMessage = new State("open", userName, userNames);
		String stateMessageJson = gson.toJson(stateMessage);
		Collection<Session> sessions = sessionsMap.values();
//		for (Session session : sessions) {
//			if (session.isOpen()) {
//				session.getAsyncRemote().sendText(stateMessageJson); 
//			}
//		}

		String text = String.format("Session ID = %s, connected; userName = %s%nusers: %s", userSession.getId(),
				userName, userNames);
		System.out.println(text);
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		System.out.println("gson------------------------------");
		String sender = chatMessage.getSender();	
		String receiver = chatMessage.getReceiver();
		
		System.out.println("reciever="+receiver);
		System.out.println("sender="+sender);
		
//		if ("chat".equals(chatMessage.getType())) {
//			List<String> chatData = JedisHandleMessage.getHistoryMsg(sender, receiver);
//			String chatMsg = gson.toJson(chatData);
//			ChatMessage cmHistory = new ChatMessage("chat", sender, receiver, chatMsg);
//			if (userSession != null && userSession.isOpen()) {
//				userSession.getAsyncRemote().sendText(gson.toJson(cmHistory));
//				return;
//			}
//		}
		
		if ("chat".equals(chatMessage.getType())) {
			System.out.println("chat有近來這邊");
			List<String> chatData = JedisHandleMessage.getHistoryMsg(sender, receiver);
			if (userSession != null && userSession.isOpen()) {
				for(int i=0;i<chatData.size();i++) {
					String chatMsg = chatData.get(i);
					synchronized(userSession) {
						try {
							userSession.getBasicRemote().sendText(chatMsg);
							System.out.println("msg="+chatMsg);
						} catch (IOException e) {
							
							e.printStackTrace();
						}
					}
				}
				System.out.println("passHistory1----------------------------");
				return;
			}
		}
		
		System.out.println("passHistory2----------------------------");
		userSession.getAsyncRemote().sendText(message);
		System.out.println("passHistory3----------------------------");
		Session receiverSession = sessionsMap.get(receiver);
	System.out.println("receiverSession="+receiver);
		if (receiverSession != null && receiverSession.isOpen()) {
			System.out.println("come here");
		receiverSession.getAsyncRemote().sendText(message);
			System.out.println("sendmsg----------------------------");
			JedisHandleMessage.saveChatMessage(sender, receiver, message);
			System.out.println("saving Msg----------------------------");
		}
		System.out.println("Message received: " + message);
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
