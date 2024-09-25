package com.reviewfinder.board.dao;

public class BoardDTO {

	private int BoardNum;
	private String Username;
	private String BoardTitle;
	private String BoardContents;
	private String BoardDate;
	private String Reply;
	private String Category;
	
	
	public int getBoardNum() {
		return BoardNum;
	}
	public void setBoardNum(int boardNum) {
		BoardNum = boardNum;
	}
	public String getUsername() {
		return Username;
	}
	public void setUsername(String username) {
		Username = username;
	}
	public String getBoardTitle() {
		return BoardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		BoardTitle = boardTitle;
	}
	public String getBoardContents() {
		return BoardContents;
	}
	public void setBoardContents(String boardContents) {
		BoardContents = boardContents;
	}
	public String getBoardDate() {
		return BoardDate;
	}
	public void setBoardDate(String boardDate) {
		BoardDate = boardDate;
	}
	public String getReply() {
		return Reply;
	}
	public void setReply(String reply) {
		Reply = reply;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	
	
	
}
