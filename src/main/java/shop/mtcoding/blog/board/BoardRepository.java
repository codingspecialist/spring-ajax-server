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

    @Transactional
    public Board updateById(BoardRequest.UpdateDTO updateDTO, int id) {
        Board board = findById(id);
        board.setTitle(updateDTO.getTitle());
        board.setContent(updateDTO.getContent());
        return board;
    } // 더티 체킹

    @Transactional
    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    public Board findById(int id) {
        return em.find(Board.class, id);
    }

    public List<Board> findAll() {
        Query query = em.createNativeQuery("select * from board_tb order by id desc", Board.class);
        List<Board> boardList = query.getResultList();
        return boardList;
    }

    // insert 하기
    @Transactional
    public Board save(Board board) {
        em.persist(board);
        return board;
    }

}
