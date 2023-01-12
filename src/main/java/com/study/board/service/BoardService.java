package com.study.board.service;

import com.study.board.entity.Board;
import com.study.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    // 글 작성 처리
    public void write(Board board, MultipartFile file) throws Exception{
        // 경로지정
        String projectPath = System.getProperty("user.dir") +"\\src\\main\\resources\\static\\files";
        // 파일에 붙일 랜덤의 이름 생성
        UUID uuid = UUID.randomUUID();
        // 랜덤 이름과 _ 를 붙이고 들어온 파일의 이름을 붙여준다 -> 저장 될 파일이름을 생성해줌
        String filename = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath,filename);

        file.transferTo(saveFile);

        board.setFilename(filename);
        board.setFilepath("/files/" + filename);




        boardRepository.save(board);
    }
    // 게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable){

        return boardRepository.findAll(pageable);
    }

    // 특정 게시글 불러오기

    public Board boardView(Integer id) {

        return boardRepository.findById(id).get();
    }

    // 특정 게시글 삭제
    public void boardDelete(Integer id){

    String[] numbers2 = {"one","two","three"};
    for (String number: numbers2){
        
    }



        boardRepository.deleteById(id);
    }

    public Page<Board> boardSearchList(String searhKeyword, Pageable pageable){

        return boardRepository.findBytitleContaining(searhKeyword, pageable);
    };
}
