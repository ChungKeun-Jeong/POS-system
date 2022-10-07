package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

public class Login {
    private String name;
    private String position;
    private String message;
    private String loginResult = "";
    private MemberDao memberDao;

    @Autowired
    public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
   
    // 로그인
    public String inputLogin(String id, String password) {           
        message =  memberDao.checkLogin(id, password);
        if (message.equals("id error")) {
            loginResult = "false";
            return loginResult; 
        }
        else if (message.equals("password error")) {
            loginResult = "false";
            return loginResult;
        }
        else {
            name = memberDao.selectById(id).getId();
            position = memberDao.selectById(id).getPosition();
            System.out.println(position + " : " + name + " 접속");

            if (position.equals("매니저")) {
                loginResult = "매니저";
                return loginResult;
            } 
            else if (position.equals("직원")) {
                loginResult = "직원";
                return loginResult;
            }

        }
        return null; 
    } 
    
    // 회원가입
    public String joinMember(String id, String password, String name, String position) {
        if (id.equals(memberDao.checkId(id)));
        else {
            return "id error";
        }

        if (position.equals("매니저") || position.equals("직원"));
        else {
            return "position error";
        } 
        
        Member member = new Member(id, password, name, position);
        memberDao.insert(member);
        return "complete";
    }
}
