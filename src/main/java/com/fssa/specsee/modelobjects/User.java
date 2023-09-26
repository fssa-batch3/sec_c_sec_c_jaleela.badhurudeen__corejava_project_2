package com.fssa.specsee.modelobjects;

public class User {


    private int userId;
    private String userName;
	private String emailId;
    private String password;
    private String phoneNumber;
    private String userAddress;
   
    
    public User() {
    	
    }
    public User(int userId,String userName,String emailId, String password,String phoneNumber,String userAddress) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.emailId = emailId;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.userAddress = userAddress;
	}
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String useraddress) {
		userAddress = useraddress;
	}

    @Override
	public String toString() {
		return "User [userId=" + userId + ", emailId=" + emailId + ", userName=" + userName + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", userAddress=" + userAddress + "]";
	}
}