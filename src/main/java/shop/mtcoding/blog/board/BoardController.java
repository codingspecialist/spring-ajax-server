package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog.core.ApiUtil;

import java.util.List;

@CrossOrigin
@RestController
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @PutMapping("/board/{id}")
    public ApiUtil<?> update(@PathVariable("id") int id, @RequestBody BoardRequest.UpdateDTO updateDTO) {
        Board board = boardRepository.updateById(updateDTO, id);
        return new ApiUtil<>(board);
    }

    @DeleteMapping("/board/{id}")
    public ApiUtil<?> delete(@PathVariable("id") int id) {
        boardRepository.deleteById(id);
        return new ApiUtil<>(null);
    }

    @PostMapping("/board")
    public ApiUtil<?> save(@RequestBody BoardRequest.SaveDTO saveDTO) { // 스프링 기본전략 = x-www-form-urlencoded 파싱
        Board board = boardRepository.save(saveDTO.toEntity());
        return new ApiUtil<>(board);
    }


    // get, post
    @GetMapping("/")
    public ApiUtil<?> list() {
        List<Board> boardList = boardRepository.findAll();
        return new ApiUtil<>(boardList);
    }

    @GetMapping("/board/{id}")
    public ApiUtil<?> detail(@PathVariable("id") Integer id, HttpServletRequest request) {
        Board board = boardRepository.findById(id);
        return new ApiUtil<>(board);
    }

    @GetMapping("/test")
    public ApiUtil<?> test() throws InterruptedException {
        Thread.sleep(10000);
        return new ApiUtil<>("test");
    }
}
