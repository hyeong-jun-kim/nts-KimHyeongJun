import $axiosInst from '../axios';

export default class postService {  
    /**
     * 게시글 작성하기
     */
    writePost(postWriteReq) {
        return $axiosInst
            .post('/post/create', postWriteReq)
    }
    /**
     * 게시글 목록 불러오기
     */
    getPostPagingList(page){
        return $axiosInst
            .get('/post/list', {
                params: {
                    "page": page
                }
            });
    }
    /**
     * 게시물 검색
     */
    searchPosts(page, type, name){
        return $axiosInst
            .get('/post/list/search', {
                params: {
                    "type": type,
                    "name": name,
                    "page": page
                }
            });
    }
    /**
     * 게시글 상세조회
     */
    getPost(postId){
        return $axiosInst
            .get('/post/'+postId);
    }
    /**
     * 게시글 비밀번호 검증
     */
    validatePassword(validatePostReq){
        return $axiosInst
            .post('/post/validate', validatePostReq);
    }
    /**
     * 게시글 수정
     */
    modifyPost(postId, modifyPostReq){
        return $axiosInst
            .patch('/post/modify/'+postId, modifyPostReq);
    }
    /**
     * 게시글 삭제
     */
    deletePost(deletePostReq){
        return $axiosInst
            .post('/post/delete', deletePostReq);
    }
}