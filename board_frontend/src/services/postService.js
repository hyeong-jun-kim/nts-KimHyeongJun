import $axiosInst from '../axios';

export default class postService {  
    /**
     * 게시글 작성하기
     */
    writePost(postWriteReq) {
        return $axiosInst
            .post('/post/create', postWriteReq)
            // .then( response => {
            //     console.log(response)
            // }).catch( error => {
            //     console.log(error);
            // });
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
     * 게시글 상세조회
     */
    getPost(page){
        return $axiosInst
            .get('/post/list', {
                params: {
                    "page": page
                }
            });
    }
}