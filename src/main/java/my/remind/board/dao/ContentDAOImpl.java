package my.remind.board.dao;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import my.remind.board.vo.ContentVO;

@Repository
public class ContentDAOImpl implements ContentDAO {
	private static final String NAMESPACE = "my.remind.board.mapper.ContentsMapper.";
	
	// Page Variable
	private int viewPageCount = 10;
	private int paegNumber = 1;


	@Inject
	private SqlSession sqlSession;
	
	@Override
	public String getTime() throws Exception {
		return sqlSession.selectOne(NAMESPACE+"getTime");
	}

	@Override
	public int checkNextPage(int contentId) {
		return sqlSession.selectOne(NAMESPACE+"checkNextPage", contentId);
	}

	@Override
	public int checkPrevPage(int contentId) {
		return sqlSession.selectOne(NAMESPACE+"checkPrevPage", contentId);
	}

	@Override
	public int getContentsCount() {
		return sqlSession.selectOne(NAMESPACE+"getContentsCount");
	}

	@Override
	public ArrayList<ContentVO> getContents(int pageNumber) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put( "start", ( pageNumber - 1 ) * this.viewPageCount );
		map.put( "count", this.viewPageCount );
		return (ArrayList) sqlSession.selectList(NAMESPACE+"getContents", map);
	}

	@Override
	public ContentVO getContent(int contentId) {
		return (ContentVO) sqlSession.selectOne(NAMESPACE+"getContent", contentId);
	}
	

	@Override
	public int insertContent(ContentVO content) {
		int lastContentId = sqlSession.selectOne(NAMESPACE+"getLastContentId");
		lastContentId += 1;

		String replacedContent = content.getcContent().replace("\r\n","<br/>");
		content.setcNo( lastContentId );
		content.setcContent( replacedContent );

		System.out.println("[inserting content ]" + content);
		
		int result = sqlSession.insert(NAMESPACE+"insertContent", content);
		if( result > 0 ) {
			return lastContentId;
		}
		return -1;
	}

	@Override
	public void updateContent(ContentVO content) {
		// .. µüÈ÷ ¹¹ ¼³Á¤ÇØÁÙ°Ô ¾ø³×..
		System.out.println("[update] " + content );
		sqlSession.update(NAMESPACE+"updateContent", content);
	}

	@Override
	public void deleteContent(int contentId) {
		System.out.println("[delete] "+contentId);
		sqlSession.update(NAMESPACE+"deleteContent", contentId);
	}
	
	
	/**
	 * Getters and Setters
	 */
	public int getViewPageCount() {
		return viewPageCount;
	}

	public int getPaegNumber() {
		return paegNumber;
	}

	public void setViewPageCount(int viewPageCount) {
		this.viewPageCount = viewPageCount;
	}

	public void setPaegNumber(int paegNumber) {
		this.paegNumber = paegNumber;
	}
	/********************************/
	/********************************/	
}
