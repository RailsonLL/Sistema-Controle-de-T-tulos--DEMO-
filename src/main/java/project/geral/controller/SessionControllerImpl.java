package project.geral.controller;

import java.util.HashMap;

import javax.faces.bean.ApplicationScoped;
import javax.servlet.http.HttpSession;

@ApplicationScoped
public class SessionControllerImpl implements SessionController {

	private static final long serialVersionUID = 1L;

	private HashMap<String, HttpSession> hashMap = new HashMap<String, HttpSession>();
	
	@Override
	public void addSession(String keyLognUser, HttpSession httpSession) {
		hashMap.put(keyLognUser, httpSession);

	}

	@Override
	public void invalidateSession(String keyLoginUser) {

		HttpSession session = hashMap.get(keyLoginUser);
		
		if (session != null) {
			try {
				session.invalidate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		hashMap.remove(keyLoginUser);
	}

}
