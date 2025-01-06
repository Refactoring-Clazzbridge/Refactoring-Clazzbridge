package com.example.academy.service;

import com.example.academy.domain.BoardType;
import com.example.academy.dto.boardType.BoardTypeDTO;
import com.example.academy.dto.boardType.BoardUpdateTypeDTO;
import com.example.academy.exception.common.NotFoundException;
import com.example.academy.repository.mysql.BoardTypeRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BoardTypeService {

    private final BoardTypeRepository boardTypeRepository;

    public BoardTypeService(BoardTypeRepository boardTypeRepository) {
        this.boardTypeRepository = boardTypeRepository;
    }

    public List<BoardType> getAllBoardTypes() {
        return boardTypeRepository.findAll();
    }

    @Transactional
    public BoardType save(BoardTypeDTO boardTypeDTO) {
        BoardType boardType = new BoardType(boardTypeDTO.getType());
        boardTypeRepository.save(boardType);
        return boardType;
    }

    @Transactional
    public BoardType update(BoardUpdateTypeDTO boardTypeDTO) {

        BoardType boardType = boardTypeRepository.findById(boardTypeDTO.getId())
            .orElseThrow(() -> new NotFoundException("해당 카테고리가 없습니다."));

        boardType.setType(boardTypeDTO.getType());

        return boardType;
    }

    @Transactional
    public void delete(Long id) {
        BoardType boardType = boardTypeRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("해당 카테고리가 없습니다."));

        boardTypeRepository.delete(boardType);
    }
}
