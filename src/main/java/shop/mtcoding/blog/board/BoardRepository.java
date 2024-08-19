package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository // @Repository를 붙이면 스프링이 new를 해서 IoC(컬렉션 List자료형 같은거) 에 저장한다.
public class BoardRepository {

    @Autowired // IoC에 있는 객체를 찾아온다.
    private EntityManager em;

    public Board findById(int id) {
        Query query = em.createNativeQuery("select * from board_tb where id = ?", Board.class);
        query.setParameter(1, id);
        try {
            Board board = (Board) query.getSingleResult();
            return board;
        } catch (Exception e) {
            // 익세션을 내가 잡은것 까지 배움 - 처리 방법은 v2에서 배우기
            throw new RuntimeException("게시글 id를 찾을 수 없습니다");
        }
    }

    public List<Board> findAll() {
        Query query = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        List<Board> boardList = query.getResultList();
        return boardList;
    }

    // insert 하기
    @Transactional
    public void save(String title, String content) {
        Query query = em.createNativeQuery("insert into board_tb(title, content, created_at) values(?,?,now())");
        query.setParameter(1, title); // position = ?의 순서
        query.setParameter(2, content);

        query.executeUpdate();
    }

}
