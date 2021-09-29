
let commentPage =0;
let principalId = $('#principalId').val();

//댓글 작성
function addComment(articleId) {

    let commentInput = $('.comment-input');
    let commentList = $('.comment-list-container');

    let data = {
        articleId:articleId,
        content: commentInput.val()
    }

    if (data.content === "") {
        alert("댓글을 작성해주세요");
        return;
    }

    $.ajax({
        type:"post",
        url: `/api/comment`,
        data:JSON.stringify(data),
        contentType:"application/json; charset=utf-8",
        dataType:"json"
    }).done(res=>{

        let comment = res.data;
        console.log(res.data.id);
        let content = `
			  <div class="comment-item" id="${comment.id}">
			    <hr class="line">
                <div class="profile">
                    <div class="imgDiv">
                        <img class="img" src="/profile/${comment.user.profileImageUrl}" >
                    </div>
                    <div class="usernameDate">
                        <span><b>${comment.user.username}</b></span>
                        <span>${comment.createdDate}</span>
                    </div>`;
        if(principalId == comment.user.id){
         content += `<span onclick="deleteComment(${comment.id})">삭제</span>`
        }
        content += `</div>
                <p class="c-content">${comment.content}</p>
            </div>`;

        commentList.prepend(content);

    }).fail(error=>{
        console.log("오류",error.responseJSON.data.content);
        //alert(error.responseJSON.data.content);
    });

    commentInput.val("");
}

function getCommentItem(comment) {

    let item = `
        <div class="comment-item" id="${comment.id}">
            <div class="profile">
                <div class="imgDiv">
                    <img class="img" src="/profile/${comment.user.profileImageUrl}">
                </div>
                <div class="usernameDate">
                    <span onclick="location.href='/blog/${comment.user.id}'"><b>${comment.user.username}</b></span>
                    <span>${comment.createdDate}</span>
                </div>`;

    if(principalId == comment.user.id){
      item += `<span onclick="deleteComment(${comment.id})">삭제</span>`;
    }
      item+=`</div>
            <p class="c-content">${comment.content}</p>
            <hr class="line">
        </div>`;

    return item;
}

//댓글 더 보기
function getComment(articleId) {
    commentPage++;

    $.ajax({
        type:"get",
        url:'/api/comment/'+articleId+'?page='+commentPage,
        contentType:"application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json"
    }).done(res=>{

        res.data.forEach((comment)=>{
            let commentItem = getCommentItem(comment);
            $('.comment-list-container').append(commentItem);
        })
    }).fail(error=>{
        alert("getComment Ajax Error")
    });
}

//댓글 삭제
function deleteComment(commentId) {

    $.ajax({
        type:"delete",
        url:'/api/comment/'+commentId,
        dataType:"json"
    }).done(res=>{
        $(`#${commentId}`).remove();

    }).fail(error=>{
        alert("deleteComment Ajax Error")
    });
}

