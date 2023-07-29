import $axiosInst from '../axios';

export default class commentService {  
    /**
     * 댓글 작성하기
     */
    writeComment(createCommentReq) {
        return $axiosInst
            .post('/comment/create', createCommentReq)
    }
}