package com.example.academy.controller;


import com.example.academy.domain.BoardType;
import com.example.academy.dto.boardType.BoardTypeDTO;
import com.example.academy.dto.boardType.BoardUpdateTypeDTO;
import com.example.academy.service.BoardTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/api/boardTypes")
public class BoardTypeController {

    private final BoardTypeService boardTypeService;

    public BoardTypeController(BoardTypeService boardTypeService) {
        this.boardTypeService = boardTypeService;
    }

    @Operation(summary = "게시판 타입 리스트 반환", security = {@SecurityRequirement(name = "bearerAuth")})
    @GetMapping("")
    public ResponseEntity<List<BoardType>> getAllBoardTypes() {
        List<BoardType> boardTypes = boardTypeService.getAllBoardTypes();
        return ResponseEntity.ok().body(boardTypes);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "게시판 타입 추가", security = {@SecurityRequirement(name = "bearerAuth")})
    @PostMapping("")
    public ResponseEntity<BoardType> save(@RequestBody BoardTypeDTO boardTypeDTO) {
        BoardType boardTypes = boardTypeService.save(boardTypeDTO);
        return ResponseEntity.ok().body(boardTypes);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "게시판 타입 수정", security = {@SecurityRequirement(name = "bearerAuth")})
    @PutMapping("")
    public ResponseEntity<BoardType> update(@RequestBody BoardUpdateTypeDTO boardTypeDTO) {
        BoardType boardTypes = boardTypeService.update(boardTypeDTO);
        return ResponseEntity.ok().body(boardTypes);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(summary = "게시판 타입 제거", security = {@SecurityRequirement(name = "bearerAuth")})
    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable(value = "id") Long id) {
        boardTypeService.delete(id);
        return HttpStatus.OK;
    }

}
