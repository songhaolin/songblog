package com.songblog.service.impl;

import com.songblog.entity.CommentReply;
import com.songblog.mapper.CommentReplyMapper;
import com.songblog.service.ICommentReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论回复表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2022-04-02
 */
@Service
public class CommentReplyServiceImpl extends ServiceImpl<CommentReplyMapper, CommentReply> implements ICommentReplyService {

}
