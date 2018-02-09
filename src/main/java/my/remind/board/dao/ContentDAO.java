package my.remind.board.dao;

import java.util.ArrayList;

import my.remind.board.vo.ContentVO;

public interface ContentDAO {
	
	// Tester
	public String getTime() throws Exception;
	
	// Pagination
	public int checkNextPage(int contentId);
	public int checkPrevPage(int contentId);
	public int getContentsCount();
	
	// Contents
	public ArrayList<ContentVO> getContents(int pageNumber);
	public ContentVO getContent(int contentId);
	
	/**
	 * TODO: write, update, delete
	 */
	public int insertContent(ContentVO content);
	public void updateContent(ContentVO content);
	public void deleteContent(int contentId);
}
