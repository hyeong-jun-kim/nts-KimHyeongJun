<!--게시글 수정 화면-->
<template>
    <div id="board-deatil">
        <warning-alert ref="warningAleartRef"></warning-alert> <!--컴포넌트 메서드 호출을 위해 ref 선언-->
        <div class="user-container">
            <tr>
                <th>
                    <div class="password" @submit.stop.prevent>
                        <label for="text-password">비밀번호</label>
                        <b-form-input v-model="password" :state="pwdValidation" id="text-password" type="password"></b-form-input>
                        <b-form-invalid-feedback :state="pwdValidation">
                            비밀번호를 입력해주세요.
                        </b-form-invalid-feedback>
                    </div>
                </th>
            </tr>
        </div>
        <div class="board-contents">
            <b-form-input v-model="title"></b-form-input>
        </div>
        <div class="board-contents">
            <b-form-textarea v-model="content" id="textarea-rows" cols="3" rows="10"></b-form-textarea>
        </div>
        <div class="common-buttons">
            <b-button size="lg" variant="success" @click="modifyPost">수정</b-button>
        </div>
    </div>
</template>
<script>
export default {
    inject: ['postService'], // inject를 통한 postService 주입
    data() {
        return {
            postId: this.$route.params.postId,
            writer: '',
            password: '',
            title: '',
            content: ''
        }
    },
    mounted() {
        this.getPost()
    },
    computed: {
        pwdValidation() {
            return this.password.length >= 4
        }
    },
    methods: {
        async getPost() { // 게시글 상세정보 받아오기
            await this.postService.getPost(this.postId)
                .then(response => {
                    const result = response.data.result;

                    // 게시글 정보 채우기
                    this.title = result.title
                    this.content = result.content
                })
                .catch(error => {
                    console.log(error);
                });
        },
        async modifyPost() { // 게시글 수정
            if (!this.validateWritePost())
                return;

            const map = new Map()
            map.set("password", this.password)
            map.set("title", this.title)
            map.set("content", this.content)

            await this.postService.modifyPost(this.postId, Object.fromEntries(map))
                .then(response => {
                    if (response.data.code == 1006) {
                        alert("정상적으로 수정되었습니다.")
                        this.$router.push('/') // 게시글 목록으로 이동 
                    } else if (response.data.code == 2007) {
                        alert("비밀번호가 틀립니다.")
                    }
                }).catch(error => {
                    console.log(error)
                })
        },
        validateWritePost() {
            const warningAlert = this.$refs.warningAleartRef;
            // 검증 : 비밀번호 길이 >= 4
            if (this.password.length < 4) {
                warningAlert.showAlert("비밀번호를 4글자 이상 입력해주세요.");
                return false;
            }

            // 글 수정시 검증 메서드
            // 검증 : 제목 (1 <= x <= 30)
            if (this.title.length < 1 || this.title.length > 30) {
                warningAlert.showAlert("제목의 길이는 1이상 30이하만 가능합니다.");
                return false;
            }

            // 검증 : 본문 (1 <= x <= 500)
            if (this.content.length < 1 || this.content.length > 30) {
                warningAlert.showAlert("본문의 길이는 1이상 500이하만 가능합니다.");
                return false;
            }

            return true;
        }
    }
};
</script>