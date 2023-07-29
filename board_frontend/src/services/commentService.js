import $axiosInst from '../axios';

export default class commentService {  
    /**
     * 댓글 작성하기
     */
    writeComment(createCommentReq) {
        return $axiosInst
            .post('/comment/create', createCommentReq)
    }
    /**
     * 댓글 비밀번호 검증하기
     */
    validatePassword(validateCommentReq){
        return $axiosInst
            .post('/comment/validate', validateCommentReq)
    }
    /**
     * 댓글 수정하기
     */
    modifyComment(modifyCommentReq){
        return $axiosInst
            .patch('/comment/modify', modifyCommentReq)
    }
    /**
     * 댓글 삭제하기
     */
    deleteComment(deleteCommentReq){
        return $axiosInst
            .post('/comment/delete', deleteCommentReq)
    }
}