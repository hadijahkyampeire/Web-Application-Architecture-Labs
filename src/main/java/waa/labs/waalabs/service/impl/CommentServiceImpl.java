package waa.labs.waalabs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.labs.waalabs.repo.CommentRepo;

@Service
public class CommentServiceImpl {
    @Autowired
    CommentRepo commentRepo;



}
