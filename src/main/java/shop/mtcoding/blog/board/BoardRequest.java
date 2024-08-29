package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO {
        private String title;
        private String content;

        public Board toEntity(){
            Board board = new Board();
            board.setTitle(title);
            board.setContent(content);
            return board;
        }
    }

    @Data
    public static class UpdateDTO {
        private String title;
        private String content;
    }
}
