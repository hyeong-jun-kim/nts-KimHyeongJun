import $axiosInst from '../axios';

export default class postService {  
    /**
     * 게시글 작성하기
     */
    writePost(postWriteReq) {
        return $axiosInst
            .post('/post/create', postWriteReq)
            .then( response => {
                console.log(response)
            }).catch( error => {
                console.log(error);
            });
    }
}