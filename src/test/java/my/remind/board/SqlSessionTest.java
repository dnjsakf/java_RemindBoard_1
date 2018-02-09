package my.remind.board;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SqlSessionTest {
	
	@Inject
	SqlSession sqlSession;
	
	@Test
	public void test() throws Exception {
		System.out.println("[SqlSession] " + sqlSession);
	}
	
	@Test
	public void getTimeTest() throws Exception {
		System.out.println("[GetTime]" + sqlSession.selectOne("my.remind.board.mapper.ContentsMapper.getTime") );
	}
	
}
