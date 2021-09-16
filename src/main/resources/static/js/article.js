let commentPage =0;

function updateId(id){
    location.href = '/update?id='+ id;
}

function deleteId(id){
    location.href = '/delete?id='+ id;
}

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
			  <div class="comment-item" id="comment-${comment.id}">
                <div class="profile">
                    <div class="imgDiv">
                        <img class="img" src="/thumbnail/munzi2.jpg" >
                    </div>
                    <div class="usernameDate">
                        <span><b>${comment.user.username}</b></span>
                        <span>${comment.createdDate}</span>
                    </div>
                    <a href="#">삭제</a>
                </div>
                <p>${comment.content}</p>
                <hr style="border:1px solid gainsboro;">
            </div>
				`;
        commentList.prepend(content);

    }).fail(error=>{
        console.log("오류",error.responseJSON.data.content);
        //alert(error.responseJSON.data.content);
    });

    commentInput.val(""); //오류가 나도 비워 주려고 이곳에 놓음.
}

function getCommentItem(comment) {

    let item = `
        <div class="comment-item">
            <div class="profile">
                <div class="imgDiv">
                    <img class="img" src="/thumbnail/munzi2.jpg">
                </div>
                <div class="usernameDate">
                    <span><b>${comment.user.username}</b></span>
                    <span>${comment.createdDate}</span>
                </div>
                <a href="#">삭제</a>
            </div>
            <p>${comment.content}</p>
            <hr style="border:1px solid gainsboro;">
        </div>`

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



