import $axiosInst from '../axios';

export default class likeService {  
    /**
     * 좋아요/싫어요 누르기
     */
    createLike(createLikeReq) {
        return $axiosInst
            .post('/like/create', createLikeReq)
    }
}