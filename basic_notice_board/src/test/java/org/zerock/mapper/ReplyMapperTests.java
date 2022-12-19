package org.zerock.mapper;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//test for controller
@WebAppConfiguration

@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class ReplyMapperTests {
	
	//테스트 전에 해당 번호의 게시물이 존재하는지 반드시 확인할 것
	private Long[] bnoArr = {1L, 4L, 5L, 6L, 7L};
	
	@Setter(onMethod_ = {@Autowired})
	private ReplyMapper mapper;
	
//	@Test
//	public void testMapper() {
//		log.info("=========================================");
//		log.info(mapper);
//		log.info("=========================================");
//	}
	
//	@Test
//	public void testCreate() {
//		IntStream.rangeClosed(1, 10).forEach(i -> {
//			
//			log.info("=========================================");
//			ReplyVO vo = new ReplyVO();
//			
//			vo.setBno(bnoArr[i % 5]);
//			vo.setReply("댓글 테스트 : " + i);
//			vo.setReplyer("replyer : " + i);
//			
//			log.info("=========================================");
//			log.info(vo);
//			
//			mapper.insert(vo);
//			
//			log.info("=========================================");
//		});
//	}
	
//	@Test
//	public void testRead() {
//		Long targetRno = 5L;
//		
//		ReplyVO vo = mapper.read(targetRno);
//		log.info("=========================================");
//		log.info(vo);
//		log.info("=========================================");
//	}
	
	
//	@Test
//	public void testDelete() {
//		Long targetRno = 2L;
//		
//		log.info("=========================================");
//		mapper.delete(targetRno);
//		log.info("=========================================");
//	}
	
//	@Test
//	public void testUpdate() {
//		Long targetRno = 3L;
//		
//		ReplyVO  vo = mapper.read(targetRno);
//		
//		vo.setReply("Update Reply");
//		
//		log.info("=========================================");
//		int count = mapper.update(vo);
//		log.info("UPDATE COUNT : " + count);
//		log.info("=========================================");
//	}
	
//	@Test
//	public void testList() {
//		log.info("=========================================");
//		Criteria cri = new Criteria();
//		log.info(cri);
//		log.info(bnoArr[0]);
//		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
//		replies.forEach(reply -> log.info(reply));
//		log.info("=========================================");
//	}
	
	@Test
	public void testList2() {
		log.info("=========================================");
		Criteria cri = new Criteria(1, 10);
		log.info(cri);
		log.info(bnoArr[0]);
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 1L);
		replies.forEach(reply -> log.info(reply));
		log.info("=========================================");
	}
	
}
