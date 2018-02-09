package my.remind.board;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import my.remind.board.dao.ContentDAO;
import my.remind.board.vo.ContentVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ContentMapperTest {
	
	@Inject
	private ContentDAO dao;
	
	@Test
	public void test() throws Exception {
		System.out.println("[ContentDAO] " + dao);
	}
	
	@Test
	public void getContentTest() throws Exception {
		System.out.println("[content] " + dao.getContent(1) );
	}
	
	@Test
	public void getContentsTest() throws Exception {
		System.out.println("[contents]" + dao.getContents(1) );
	}
	
	@Test
	public void checkNextPage() {
		System.out.println("[check-prev-page] " + dao.checkNextPage(2));
	}
	@Test
	public void checkPrevPage() {
		System.out.println("[check-next-page] " + dao.checkPrevPage(1));
	}
	@Test
	public void getContentsCount() {
		System.out.println("[content-count] " + dao.getContentsCount());
	}
	
	@Test
	public void updateContent() {
		ContentVO content = dao.getContent(1);
		content.setcWriter("ÇãÁ¤¿î");
		dao.updateContent(content);
		System.out.println("[content-update]");
	}
	
	@Test
	public void deleteContent() {
		dao.deleteContent(2);
		System.out.println("[content-delete]");
	}
}
