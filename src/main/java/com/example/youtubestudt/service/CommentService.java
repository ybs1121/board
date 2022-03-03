package com.example.youtubestudt.service;

import com.example.youtubestudt.dto.CommentDto;
import com.example.youtubestudt.entity.Article;
import com.example.youtubestudt.entity.Comment;
import com.example.youtubestudt.repository.ArticleRepository;
import com.example.youtubestudt.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;


    public List<CommentDto> comments(Long articleId) {
        //조회 : 댓글 목록 조회
        List<Comment> comments =commentRepository.findByArticleId(articleId);

        //변환 : 엔티티 -> DTO
        List<CommentDto> dtos = new ArrayList<CommentDto>();

        for(Comment temp: comments){
            CommentDto dto = CommentDto.createCommentDto(temp);
            dtos.add(dto);
        }

        //반환 return dtos;

        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment)).collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        //게시글 조회를 예외 발생
        Article article = articleRepository.findById(articleId).orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패"));
        //댓글 엔티티 생성
        Comment comment = Comment.createComment(dto,article);
        //댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);
        //DTO로 변환하여 반환

        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("대상 댓글이 없습니다."));

        // 댓글 수정
        target.patch(dto);
        // 댓글 db 저장
        Comment updated = commentRepository.save(target);


        // 반환

        return CommentDto.createCommentDto(updated);
   }

    @Transactional
    public CommentDto delete(Long id) {
        //댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("삭제 대상 댓글이 없습니다."));
        // DB 삭제
        commentRepository.delete(target);
        // 반환

        return CommentDto.createCommentDto(target);

    }
}
